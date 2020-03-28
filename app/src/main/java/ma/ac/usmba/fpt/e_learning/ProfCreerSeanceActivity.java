package ma.ac.usmba.fpt.e_learning;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
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
import java.util.Map;

import ma.ac.usmba.fpt.e_learning.Controller.QuizAdapter;
import ma.ac.usmba.fpt.e_learning.Model.Quiz;
import ma.ac.usmba.fpt.e_learning.Utils.FileUtils;

public class ProfCreerSeanceActivity extends AppCompatActivity {
    final String QUIZ = "Quiz";
    ArrayList<Quiz> quizzes = new ArrayList<>();
    ArrayList<String> paths = new ArrayList<>();
    Button button_valider, creer_quiz;
    RecyclerView recyclerView;
    QuizAdapter quizAdapter;
    ImageView attach_file;
    TextView file_path;
    final String[] PERMISSIONS = {Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE};
    final int FILE_CHOOSER = 50;
    final String PATHS = "paths";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prof_creer_seance);
        //Necessary Declarations
        creer_quiz = findViewById(R.id.button_creer_quiz);
        button_valider = findViewById(R.id.button_valider);
        recyclerView = findViewById(R.id.recyclerView);
        attach_file = findViewById(R.id.img_view_attach_file);
        file_path = findViewById(R.id.txt_view_file_path);
        //Move to the QuizPopUp
        creer_quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfCreerSeanceActivity.this, QuizPopUp.class);
                Gson gson = new Gson();
                String array_quizzes = gson.toJson(quizzes);
                intent.putExtra(QUIZ, array_quizzes);
                intent.putStringArrayListExtra("paths", paths);
                startActivity(intent);
            }
        });
        //Populate the recycler view
        update_quizzes();
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
            }
        });

    }

    //Display the content of the quizzes
    public void show_quizzes() {
        if (!quizzes.isEmpty()) {
            for (Quiz quiz : quizzes) {
                System.out.println(quiz.getQuestion());
                for (Map.Entry<String, Boolean> answers : quiz.getReponses().entrySet())
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
        if (getIntent().getSerializableExtra(QUIZ) != null)
            quizzes = (ArrayList<Quiz>) getIntent().getSerializableExtra(QUIZ);
        if (getIntent().getStringArrayListExtra(PATHS) != null) {
            paths = getIntent().getStringArrayListExtra(PATHS);
        }
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
        StringBuilder path = new StringBuilder();
        if (requestCode == FILE_CHOOSER && resultCode == RESULT_OK) {
            //Check if the user select multiple files
            if (data.getClipData() != null) {
                int count = 0;
                while (count < data.getClipData().getItemCount()) {
                    Uri uri = data.getClipData().getItemAt(count).getUri();
                    paths.add(FileUtils.getPath(ProfCreerSeanceActivity.this, uri));
                    path.append(FileUtils.getFileName(paths.get(count))).append("  ");
                    count++;
                }
            } else if (data.getData() != null) {
                paths.add(FileUtils.getPath(ProfCreerSeanceActivity.this, data.getData()));
                path.append(FileUtils.getFileName(FileUtils.getPath(ProfCreerSeanceActivity.this, data.getData())));
            }
            file_path.setText(path.toString());
//                    Toast.makeText(this, "Selected File ///" + path +"///", Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "Selected File ---" + paths.size() + "---", Toast.LENGTH_SHORT).show();
        }
    }
}


