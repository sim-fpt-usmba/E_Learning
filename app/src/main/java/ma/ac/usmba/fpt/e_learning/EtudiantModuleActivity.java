package ma.ac.usmba.fpt.e_learning;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.util.Consumer;
import androidx.core.view.ViewCompat;

import java.util.ArrayList;
import java.util.HashMap;

import ma.ac.usmba.fpt.e_learning.Controller.EtudiantAPI;
import ma.ac.usmba.fpt.e_learning.Model.Etudiant;
import ma.ac.usmba.fpt.e_learning.Model.Module;
import ma.ac.usmba.fpt.e_learning.Model.Seance;
import ma.ac.usmba.fpt.e_learning.View.ModuleSeanceView;

public class EtudiantModuleActivity extends AppCompatActivity {
    private SharedPreferences sessionData;
    private EtudiantAPI API;
    public Etudiant etudiant;
    ConstraintLayout constraintLayout;
    ArrayList<Module> modules;

    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etudiant_module);

        // oubtou_log : we don't need to list of modules because we in a sepesific module
        // ModuleController.getModule();
        modules = new ArrayList<>();
        constraintLayout = findViewById(R.id.module_container);
        //--
        API = new EtudiantAPI();
        sessionData = getSharedPreferences("user_details",MODE_PRIVATE);

        // oubtou_log : you need to get module from intent preview
        final Module module = new Module();
        module.setId(1);
        module.setName("Module provisoir .....");

        if( sessionData.contains("Etudiant") ){
            etudiant = API.Object_from_Json(
                    sessionData.getString("Etudiant", null )
            );

            HashMap<String,String> filter = new HashMap<>();
            filter.put("filter[module_id][value]", module.getId()+"");

            API.seances(
                    etudiant.build_access_token(),
                    filter,
                    new Consumer<ArrayList<Seance>>() {
                        @Override
                        public void accept(ArrayList<Seance> seances) {
                            //semestres = s;
                            module.setSeances(seances);
                            modules.add(module);
                            load_grid();
                        }
                    },
                    new Consumer<String>() {
                        @Override
                        public void accept(String error) {
                            // oubtou_log : no seances
                            Log.d("oubtou___", error);
                            Toast.makeText(EtudiantModuleActivity.this, error, Toast.LENGTH_SHORT).show();
                        }
                    }
            );
        }else{
            // user not exit you need to redirect user to login form
        }
        //---
    }

    public void load_grid(){
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

    public void goback(View view) {
        finish();
    }
}
