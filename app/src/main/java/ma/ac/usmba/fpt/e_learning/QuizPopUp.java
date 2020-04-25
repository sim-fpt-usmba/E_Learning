package ma.ac.usmba.fpt.e_learning;

import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.view.ViewCompat;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

import ma.ac.usmba.fpt.e_learning.Model.QuestionAnswer;
import ma.ac.usmba.fpt.e_learning.Model.Quiz;
public class QuizPopUp extends Activity {
    RadioGroup radioGroup;
    EditText edit_text_reponse;
    EditText editText_question;
    HashMap<String, Boolean> answers;
    final String QUIZ = "Quiz";
    Button button_ajouter, button_valider;
    final ColorStateList colorStateList = new ColorStateList(new int[][]{
            new int[]{-android.R.attr.state_selected}, //disabled
            new int[]{android.R.attr.state_enabled} //enabled
    }, new int[] {
                    R.color.red,
                    R.color.colorAccent
            });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_pop_up);
        //Customize the height and width for the activity to look like a popup window.
        /*DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;
        getWindow().setLayout((int) (width * 0.9), (int) (height * 0.95));*/
        //=====================================================================================

        button_ajouter = (Button) findViewById(R.id.button_ajouter);
        radioGroup = (RadioGroup) findViewById(R.id.radio_group_reponses);
        answers = new HashMap<>();
        button_ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAnswer();
                edit_text_reponse.setText("");
            }
        });
        //Add quiz
        button_valider = (Button) findViewById(R.id.button_valider);
        button_valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add_quiz();
            }
        });
    }

    //Add answers to the group radio dynamically.
    public void addAnswer() {
        edit_text_reponse = (EditText) findViewById(R.id.edit_txt_reponse);
        if(!edit_text_reponse.getText().toString().equals("")){
            radioGroup = (RadioGroup) findViewById(R.id.radio_group_reponses);
            final RadioButton radioButton = new RadioButton(this);
            radioButton.setId(ViewCompat.generateViewId());
            radioButton.setText(edit_text_reponse.getText().toString());
            radioButton.setTextColor(getResources().getColor(R.color.dark));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                radioButton.setButtonTintList(colorStateList);
            }
            // radioButton.setBackground(R.drawable.);
            radioGroup.addView(radioButton);
        }else{
            edit_text_reponse.setError("Champ vide");
            Toast.makeText(this, "Ajouter une réponse svp", Toast.LENGTH_SHORT).show();
            edit_text_reponse.setFocusable(true);
        }
    }

    //Add quiz
    public void add_quiz() {
        editText_question = (EditText) findViewById(R.id.edit_txt_question);
        if(editText_question.getText().toString().equals("")){
            editText_question.setError("Champ vide");
            Toast.makeText(this, "Tu doit ajouter une question", Toast.LENGTH_SHORT).show();
        }else if(radioGroup.getChildCount() < 2){
            Toast.makeText(this, "Ajouter plusieurs réponses svp", Toast.LENGTH_SHORT).show();
        }
        else if(radioGroup.getCheckedRadioButtonId() == -1){
            Toast.makeText(this, "Selectionner une réponse vrai spv", Toast.LENGTH_SHORT).show();
        }
        else{
            String Question = editText_question.getText().toString();
            for (int i = 0; i < radioGroup.getChildCount(); i++) {
                RadioButton rd = (RadioButton) radioGroup.getChildAt(i);
                if (rd.getId() == radioGroup.getCheckedRadioButtonId())
                    answers.put(rd.getText().toString(), true);
                else answers.put(rd.getText().toString(), false);
            }
            QuestionAnswer quiz = new QuestionAnswer();
            quiz.setQuestion(Question);
            quiz.setAnswers(answers);
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<Quiz>>() {
            }.getType();
            ArrayList<QuestionAnswer> quiz_array = gson.fromJson(getIntent().getStringExtra(QUIZ), type);
            quiz_array.add(quiz);
            Intent intent = new Intent(QuizPopUp.this, ProfCreerSeanceActivity.class);
            ArrayList<String> paths = getIntent().getStringArrayListExtra("paths");
            intent.putStringArrayListExtra("paths", paths);
            intent.putExtra(QUIZ, quiz_array);
            String module = getIntent().getStringExtra("modules");
            intent.putExtra("modules",module);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
            finish();
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }
}
