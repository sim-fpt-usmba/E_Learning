package ma.ac.usmba.fpt.e_learning;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EtudiantSeanceActivity extends AppCompatActivity {
    TextView txtView;
    Button btn_quiz,btn_disc,btn_contenu;
    String Quiz_txt,disc_txt,contenu_txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etudiant_seance);

        btn_quiz = (Button)findViewById(R.id.button5);
        btn_disc = (Button)findViewById(R.id.button4);
        btn_contenu = (Button)findViewById(R.id.button3);

        txtView =(TextView) findViewById(R.id.textView3);
        Quiz_txt ="QCM SERA SOUS FORME DES CHECKBOXS";
        disc_txt ="JE SUIS DISCUSSION";
        contenu_txt ="JE SUIS CONTENU";

        final int black = Color.rgb(0,0,0);
        final int gray = Color.rgb(170,170,170);
        btn_quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtView.setText(Quiz_txt);
                btn_quiz.setBackgroundColor(black);
                btn_contenu.setBackgroundColor(gray);
                btn_disc.setBackgroundColor(gray);
            }
        });
        btn_disc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtView.setText(disc_txt);
                btn_disc.setBackgroundColor(black);
                btn_contenu.setBackgroundColor(gray);
                btn_quiz.setBackgroundColor(gray);
            }
        });
        btn_contenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtView.setText(contenu_txt);
                btn_contenu.setBackgroundColor(black);
                btn_quiz.setBackgroundColor(gray);
                btn_disc.setBackgroundColor(gray);
            }
        });
    }
}
