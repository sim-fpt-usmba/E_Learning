package ma.ac.usmba.fpt.e_learning;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Iterator;

import ma.ac.usmba.fpt.e_learning.Controller.SemestreController;
import ma.ac.usmba.fpt.e_learning.Model.Module;
import ma.ac.usmba.fpt.e_learning.Model.Seance;
import ma.ac.usmba.fpt.e_learning.Model.Semestre;

public class ProfModuleActivity extends AppCompatActivity {
    ArrayList<Button> buttonModules;
    ArrayList<Button> buttonSeances;
    LinearLayout myLayout;
    ArrayList<Semestre> semestres;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prof_module);

        semestres = SemestreController.setModules();
        buttonModules = new ArrayList<Button>();
        buttonSeances = new ArrayList<Button>();
        addBTN_Modules();
    }

    private void addBTN_Modules(){
        myLayout =(LinearLayout)findViewById(R.id.moduleLayout1);
        Iterator<Semestre> iter_sem;
        Iterator<Module> iter_mod;
        Iterator<Seance> iter_sean;
        iter_sem=semestres.iterator();
        int i=0,k=0;
        while (iter_sem.hasNext()) {
            Semestre s=iter_sem.next();
            iter_mod=s.getListof_module().iterator();
            while(iter_mod.hasNext()) {
                Module m=iter_mod.next();
                buttonModules.add(new Button(this));
                buttonModules.get(i).setHeight(150);
                buttonModules.get(i).setPadding(60,0,60,0);
                buttonModules.get(i).setBackgroundColor(Color.LTGRAY);
                buttonModules.get(i).setTextColor(Color.BLACK);
                buttonModules.get(i).setText(m.getNom_module());
                buttonModules.get(i).setTag(i);
                myLayout.addView(buttonModules.get(i));
                myLayout.addView(new TextView(this));

                i++;
                iter_sean= m.getListof_seances().iterator();
                while(iter_sean.hasNext()) {
                    Seance se=iter_sean.next();
                    buttonSeances.add(new Button(this));
                    buttonSeances.get(k).setHeight(150);
                    buttonSeances.get(k).setPadding(200,0,200,0);
                    buttonSeances.get(k).setBackgroundColor(Color.DKGRAY);
                    buttonSeances.get(k).setTextColor(Color.WHITE);
                    buttonSeances.get(k).setText(se.getNom_seance());
                    buttonSeances.get(k).setTag(k);
                    myLayout.addView(buttonSeances.get(k));
                    myLayout.addView(new TextView(this));
                    k++;
                }
            }
        }
    }

}
