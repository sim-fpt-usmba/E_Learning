package ma.ac.usmba.fpt.e_learning;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class ProfModuleActivity extends AppCompatActivity {
    LinearLayout per1,per2,per3;
    ArrayList<LinearLayout> Lay = new ArrayList<LinearLayout>();
    ArrayList<Button> btn  = new ArrayList<Button>();
    String btnNames[]={"Espace professeurs", "Créer une séance","Créer des séances"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prof_module);

        per1=(LinearLayout)findViewById(R.id.parent1);
        per2=(LinearLayout)findViewById(R.id.parent2);
        per3=(LinearLayout)findViewById(R.id.parent3);
        Lay.add(per1); Lay.add(per2); Lay.add(per3);
        for(int i=0; i<3;i++){
            btn.add(new Button(this));
            btn.get(i).setHeight(200);
            btn.get(i).setWidth(800);
            btn.get(i).setText(btnNames[i]);
            btn.get(i).setTag(i);
            Lay.get(i).addView(btn.get(i));
            Lay.get(i).addView(new TextView(this));
        }
        btn.get(0).setEnabled(false);
        btn.get(1).setBackgroundColor(Color.DKGRAY);
        btn.get(1).setTextColor(Color.WHITE);
        btn.get(2).setBackgroundColor(Color.DKGRAY);
        btn.get(2).setTextColor(Color.WHITE);
    }

    private void init_espace(){

    }

}
