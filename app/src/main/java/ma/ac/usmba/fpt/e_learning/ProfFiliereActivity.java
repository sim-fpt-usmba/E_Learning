package ma.ac.usmba.fpt.e_learning;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Iterator;

import ma.ac.usmba.fpt.e_learning.Controller.ProfFiliereController;
import ma.ac.usmba.fpt.e_learning.Model.Filiere;

public class ProfFiliereActivity extends AppCompatActivity {
    ArrayList<Filiere> list_filiere = ProfFiliereController.set_Filiere();
    LinearLayout filier_layout;
    Button bouton;
    ArrayList<Button> button_filier_list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prof_filiere);
        filier_layout = (LinearLayout) findViewById(R.id.filiere_layout);
        filier_layout.setPadding(100,0,100,0);

        filier_layout.addView(new TextView(this));
        bouton=(Button) findViewById(R.id.button);
        bouton.setBackgroundColor(Color.LTGRAY);
        Iterator<Filiere> iter_filiere = list_filiere.iterator();
        int i = 0;
        while (iter_filiere.hasNext()) {
            Filiere f = iter_filiere.next();
            button_filier_list.add(new Button(this));
            button_filier_list.get(i).setHeight(150);
            button_filier_list.get(i).setWidth(500);
            button_filier_list.get(i).setBackgroundColor(Color.LTGRAY);
            button_filier_list.get(i).setTextColor(Color.BLACK);
            button_filier_list.get(i).setText(f.getNom_filere());
            button_filier_list.get(i).setTag(i);
            button_filier_list.get(i).setTextColor(Color.WHITE);
            button_filier_list.get(i).setBackgroundColor(Color.BLACK);
            button_filier_list.get(i).setPadding(100, 0, 100, 0);
            button_filier_list.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String from = getIntent().getStringExtra("from");
                    if (from.equals("Creer")) {
                        Intent intent = new Intent(ProfFiliereActivity.this, ProfCreerSeanceActivity.class);
                        startActivity(intent);
                    } else if (from.equals("Gerer")) {
                        Intent intent = new Intent(ProfFiliereActivity.this, ProfModuleActivity.class);
                        startActivity(intent);
                    }
                }
            });
            filier_layout.addView(button_filier_list.get(i));
            filier_layout.addView(new TextView(this));
            i++;
        }
    }


}
