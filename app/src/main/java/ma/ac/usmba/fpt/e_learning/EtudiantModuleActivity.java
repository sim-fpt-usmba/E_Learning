package ma.ac.usmba.fpt.e_learning;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.view.ViewCompat;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

import ma.ac.usmba.fpt.e_learning.Controller.ModuleController;
import ma.ac.usmba.fpt.e_learning.Controller.SemestreController;
import ma.ac.usmba.fpt.e_learning.Model.Module;
import ma.ac.usmba.fpt.e_learning.View.ModuleSeanceView;
import ma.ac.usmba.fpt.e_learning.View.SemestreModuleView;

public class EtudiantModuleActivity extends AppCompatActivity {
    ConstraintLayout constraintLayout;
    ArrayList<Module> modules;

    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etudiant_module);

        modules = ModuleController.getModule();

        constraintLayout = findViewById(R.id.module_container);

        for (int i = 0; i < modules.size(); i++) {
            ModuleSeanceView moduleSeanceView = new ModuleSeanceView(this, modules.get(i));
            moduleSeanceView.setId(ViewCompat.generateViewId());
            constraintLayout.addView(moduleSeanceView);
        }

        ConstraintSet set = new ConstraintSet();
        set.clone(constraintLayout);
        for (int i = 0; i < constraintLayout.getChildCount(); i++) {
            //first
            if (i == 0) {
                set.connect(constraintLayout.getChildAt(i).getId(), ConstraintSet.TOP,
                        ConstraintSet.PARENT_ID, ConstraintSet.TOP);
                if (i + 1 < modules.size()) {
                    set.connect(constraintLayout.getChildAt(i).getId(), ConstraintSet.BOTTOM,
                            constraintLayout.getChildAt(i + 1).getId(), ConstraintSet.TOP);
                }
            }
            //last
            if (i == modules.size() - 1) {
                if (i!=0) {
                    set.connect(constraintLayout.getChildAt(i).getId(), ConstraintSet.TOP,
                            constraintLayout.getChildAt(i - 1).getId(), ConstraintSet.BOTTOM,50);
                } else {
                    set.connect(constraintLayout.getChildAt(i).getId(), ConstraintSet.BOTTOM,
                            ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM);
                }
            }
            //middle
            if (i != 0 && i != modules.size() - 1) {
                set.connect(constraintLayout.getChildAt(i).getId(), ConstraintSet.TOP,
                        constraintLayout.getChildAt(i - 1).getId(), ConstraintSet.BOTTOM,50);
                set.connect(constraintLayout.getChildAt(i).getId(), ConstraintSet.BOTTOM,
                        constraintLayout.getChildAt(i + 1).getId(), ConstraintSet.TOP);
            }
        }
        set.applyTo(constraintLayout);

    }

}
