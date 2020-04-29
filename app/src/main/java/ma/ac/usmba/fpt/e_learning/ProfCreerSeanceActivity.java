package ma.ac.usmba.fpt.e_learning;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

import ma.ac.usmba.fpt.e_learning.Adapters.FilesAdapter;
import ma.ac.usmba.fpt.e_learning.Controller.ModuleController;
import ma.ac.usmba.fpt.e_learning.Adapters.QuizAdapter;
import ma.ac.usmba.fpt.e_learning.Model.Module;
import ma.ac.usmba.fpt.e_learning.Model.QuestionAnswer;
import ma.ac.usmba.fpt.e_learning.Utils.FileUtils;

public class ProfCreerSeanceActivity extends AppCompatActivity {
    final String QUIZ = "QuestionAnswer";
    ArrayList<QuestionAnswer> quizzes = new ArrayList<>();
    ArrayList<String> paths = new ArrayList<>();
    Button button_valider;
    RecyclerView recyclerView,file_names_recycler;
    QuizAdapter quizAdapter;
    FilesAdapter filesAdapter;
    Button attach_file;
    TextView creer_quiz,cour_date,cour_time;
    DatePickerDialog datePickerDialog;
    DatePickerDialog.OnDateSetListener onDateSetListener;
    ImageView calendar,time;

    ArrayAdapter<String> adapter;
    final String[] PERMISSIONS = {Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE};
    final int FILE_CHOOSER = 50;
    final String PATHS = "paths";
    Spinner modules;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prof_creer_seance);
        androidx.appcompat.widget.Toolbar toolbar= findViewById(R.id.constraintLayout4);
        setSupportActionBar(toolbar);
        TextView txt=findViewById(R.id.textView);
        txt.setText("Espace Enseignant");
        toolbar.setBackgroundColor(Color.parseColor("#E31731"));
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        //Instantiations
        creer_quiz = findViewById(R.id.creer_quiz);
        button_valider = findViewById(R.id.button_valider);
        recyclerView = findViewById(R.id.recyclerView);
        attach_file = findViewById(R.id.img_view_attach_file);
        modules = findViewById(R.id.spinner_modules);
        file_names_recycler = findViewById(R.id.selected_files_recycler);
        calendar = findViewById(R.id.calendar_btn);
        time = findViewById(R.id.time_btn);
        cour_date = findViewById(R.id.cour_date);
        cour_time = findViewById(R.id.cour_time);
        //Populate the Modules dropDown
        ArrayList<String> array_modules = new ArrayList<>();
        array_modules.add("SELECTIONNER UN MODULE");
        //Filling the array modules with modules names.
        for(Module m : ModuleController.getModule())array_modules.add(m.getName());
        //Setting an adapter for the spinner.
        adapter = new ArrayAdapter<>(this,R.layout.dropdown,array_modules);
        modules.setAdapter(adapter);
        //TODO : ACTIONS WHEN THE USER SELECT AN ITEM OR NOT.
        modules.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //Move to the QuizPopUp
        creer_quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfCreerSeanceActivity.this, QuizPopUp.class);
                Gson gson = new Gson();
                String array_quizzes = gson.toJson(quizzes);
                intent.putExtra(QUIZ, array_quizzes);
                intent.putStringArrayListExtra("paths", paths);
                intent.putExtra("modules",modules.getSelectedItem().toString());
                startActivity(intent);
                overridePendingTransition(R.anim.slide_to_right,R.anim.slide_out_left);
                finish();
            }
        });
        update_quizzes();
        update_files();
        //Populate the recycler view
        quizAdapter = new QuizAdapter(this, quizzes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(quizAdapter);
        //Check the quizzes content
        button_valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_quizzes();
            }
        });
        //Display filechooser
        attach_file.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
            @Override
            public void onClick(View v) {
                if (hasPermissions(ProfCreerSeanceActivity.this, PERMISSIONS)) {
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("*/*");
                    intent.putExtra(PATHS, paths);
                    intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                    startActivityForResult(Intent.createChooser(intent, "Choisir un fichier"), FILE_CHOOSER);
                } else {
                    ActivityCompat.requestPermissions(ProfCreerSeanceActivity.this, PERMISSIONS, FILE_CHOOSER);
                }
                for(String path:paths) System.out.println(path);
            }
        });

        // Show a calendar
        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar1 = Calendar.getInstance();
                int year,month,day;
                year = calendar1.get(Calendar.YEAR);
                month = calendar1.get(Calendar.MONTH);
                day = calendar1.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(
                        ProfCreerSeanceActivity.this,onDateSetListener,year,month,day);
                datePickerDialog.setTitle("Date du cour");
                datePickerDialog.show();
            }
        });
        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month+1;
                String date = dayOfMonth+"/"+month+"/"+year;
                cour_date.setText(date);
            }
        };
        // Show time chooser
        time.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(ProfCreerSeanceActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                        String time = "";//hour +":" + minute;
                        if(hour < 10){
                            time += "0" + hour;
                        }else{
                            time += hour;
                        }
                        if(minute < 10){
                            time += ":0" + minute;
                        }else{
                            time += ":"+ minute;
                        }
                        cour_time.setText(time);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Début de cour");
                mTimePicker.show();

            }
        });
    }

    //Display the content of the quizzes
    public void show_quizzes() {
        if (!quizzes.isEmpty()) {
            for (QuestionAnswer quiz : quizzes) {
                System.out.println(quiz.getQuestion());
                for (Map.Entry<String, Boolean> answers : quiz.getAnswers().entrySet())
                    System.out.println("Key : " + answers.getKey() + " Value : " + answers.getValue());
            }
        } else {
            System.out.println("QUIZ IS EMPTY");
        }
        System.out.println(quizzes.size());
        System.out.println(quizAdapter.getItemCount());
    }

    //Update the quizzes arrayList
    public void update_quizzes() {
        if (getIntent().getSerializableExtra(QUIZ) != null){
            quizzes = (ArrayList<QuestionAnswer>) getIntent().getSerializableExtra(QUIZ);
        }
        String module = getIntent().getStringExtra("modules");
        int pos = adapter.getPosition(module);
        modules.setSelection(pos);
    }
    //Update the selected files
    public void update_files(){
        if (getIntent().getStringArrayListExtra(PATHS) != null) {
            paths = getIntent().getStringArrayListExtra(PATHS);
        }
        filesAdapter = new FilesAdapter(this,paths);
        file_names_recycler.setLayoutManager(new LinearLayoutManager(this));
        file_names_recycler.setAdapter(filesAdapter);
    }

    //Ask the user for permission to read and write from the externale storage.
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == FILE_CHOOSER) {
            // If request is cancelled, the result arrays are empty.
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(
                        ProfCreerSeanceActivity.this,
                        "Permission d'accées au stockage accepté",
                        Toast.LENGTH_SHORT
                ).show();
            } else {
                Toast.makeText(
                        ProfCreerSeanceActivity.this,
                        "Permission d'accées au stockage refusé",
                        Toast.LENGTH_SHORT
                ).show();
            }
        }
    }

    //Check if the user has given the application the access to Strorage.
    public boolean hasPermissions(Context context, String[] permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }
    //
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String path = "";
        if (requestCode == FILE_CHOOSER && resultCode == RESULT_OK) {
            //Check if the user select multiple files
            if (data.getClipData() != null) {
                int count = 0;
                while (count < data.getClipData().getItemCount()) {
                    Uri uri = data.getClipData().getItemAt(count).getUri();
                    paths.add(FileUtils.getPath(ProfCreerSeanceActivity.this, uri));
                    path += "  "+FileUtils.getFileName(paths.get(count));
                    count++;
                }
            } else if (data.getData() != null) {
                paths.add(FileUtils.getPath(ProfCreerSeanceActivity.this, data.getData()));
                path += "  "+FileUtils.getFileName(paths.get(paths.size()-1));
            }
            //setPaths();
            //Populate the file names recycler
            update_files();
            Toast.makeText(this, "Selected File ---" + paths.size() + "---", Toast.LENGTH_SHORT).show();
        }
    }

    public void goback(View view) {
        finish();
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;

    }
}


