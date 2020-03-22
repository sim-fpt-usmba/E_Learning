package ma.ac.usmba.fpt.e_learning;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.HashMap;
import java.util.Map;

public class ProfCreerSeanceActivity extends AppCompatActivity {
    private HashMap<String,Boolean> quiz = new HashMap<>();
    private final String HACHMAP_KEY = "quizzes";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prof_creer_seance);
        Button creer_quiz = (Button) findViewById(R.id.button_creer_quiz);
        creer_quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfCreerSeanceActivity.this,QuizPopUp.class);
                startActivity(intent);
            }
        });
        Button button_valider = (Button) findViewById(R.id.button_valider);
        button_valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!quiz.isEmpty()){
                    for(Map.Entry answer : quiz.entrySet())
                        System.out.println("Key : " + answer.getKey() + " Value : " + answer.getValue());
                }else{
                    System.out.println("QUIZ IS EMPTY");
                }
                System.out.println(quiz.size());
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        HashMap<String,Boolean> quiz_list = (HashMap<String, Boolean>) getIntent().getSerializableExtra("quiz_list");
        if(quiz_list != null) quiz.putAll(quiz_list);
    }

    @Override
    protected void onPause() {
        Bundle state = new Bundle();
        state.putSerializable(HACHMAP_KEY,quiz);
        super.onPause();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putSerializable(HACHMAP_KEY,quiz);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        quiz = (HashMap<String, Boolean>) savedInstanceState.getSerializable(HACHMAP_KEY);
    }
}
