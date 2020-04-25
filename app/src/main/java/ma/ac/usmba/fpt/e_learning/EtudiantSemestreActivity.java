package ma.ac.usmba.fpt.e_learning;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.view.ViewCompat;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import java.util.ArrayList;

import ma.ac.usmba.fpt.e_learning.Controller.SemestreController;
import ma.ac.usmba.fpt.e_learning.Model.Semestre;
import ma.ac.usmba.fpt.e_learning.View.ModuleGrid;
import ma.ac.usmba.fpt.e_learning.View.SemestreModuleView;

public class EtudiantSemestreActivity extends AppCompatActivity {
    ConstraintLayout constraintLayout;
    ArrayList<Semestre> semestres;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etudiant_semestre);

        semestres = SemestreController.getSemestres();

        constraintLayout = findViewById(R.id.semestre_container);

        for (int i = 0; i < semestres.size(); i++) {
            SemestreModuleView semestreModuleView = new SemestreModuleView(this, semestres.get(i));
            semestreModuleView.setId(ViewCompat.generateViewId());
            constraintLayout.addView(semestreModuleView);
        }

        ConstraintSet set = new ConstraintSet();
        set.clone(constraintLayout);
        for (int i = 0; i < constraintLayout.getChildCount(); i++) {
            //first
            if (i == 0) {
                set.connect(constraintLayout.getChildAt(i).getId(), ConstraintSet.TOP,
                        ConstraintSet.PARENT_ID, ConstraintSet.TOP);
                if (i + 1 < semestres.size()) {
                    set.connect(constraintLayout.getChildAt(i).getId(), ConstraintSet.BOTTOM,
                            constraintLayout.getChildAt(i + 1).getId(), ConstraintSet.TOP);
                }
            }
            //last
            if (i == semestres.size() - 1) {
                if (i!=0) {
                    set.connect(constraintLayout.getChildAt(i).getId(), ConstraintSet.TOP,
                            constraintLayout.getChildAt(i - 1).getId(), ConstraintSet.BOTTOM,50);
                } else {
                    set.connect(constraintLayout.getChildAt(i).getId(), ConstraintSet.BOTTOM,
                            ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM);
                }
            }
            //middle
            if (i != 0 && i != semestres.size() - 1) {
                set.connect(constraintLayout.getChildAt(i).getId(), ConstraintSet.TOP,
                        constraintLayout.getChildAt(i - 1).getId(), ConstraintSet.BOTTOM,50);
                set.connect(constraintLayout.getChildAt(i).getId(), ConstraintSet.BOTTOM,
                        constraintLayout.getChildAt(i + 1).getId(), ConstraintSet.TOP);
            }
        }
        set.applyTo(constraintLayout);
    }

    public void goback(View view) {
        finish();
    }
}
