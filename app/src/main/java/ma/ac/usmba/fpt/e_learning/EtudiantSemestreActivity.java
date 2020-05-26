package ma.ac.usmba.fpt.e_learning;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.util.Consumer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ma.ac.usmba.fpt.e_learning.Adapters.EtudiantSemestreRecycler;
import ma.ac.usmba.fpt.e_learning.Controller.EtudiantAPI;
import ma.ac.usmba.fpt.e_learning.Model.Etudiant;
import ma.ac.usmba.fpt.e_learning.Model.Seance;
import ma.ac.usmba.fpt.e_learning.Model.Semestre;

public class EtudiantSemestreActivity extends AppCompatActivity {
    private SharedPreferences sessionData;
    private EtudiantAPI API;
    public Etudiant etudiant;
    public Seance seance;

    ConstraintLayout constraintLayout;
    ArrayList<Semestre> semestres;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etudiant_semestre);
        //--
        API = new EtudiantAPI();
        sessionData = getSharedPreferences("user_details",MODE_PRIVATE);

        if( sessionData.contains("Etudiant") ){
            etudiant = API.Object_from_Json(
                    sessionData.getString("Etudiant", null )
            );
            /*
             oubtou_log : get list of ressources
            */
            API.ressources(
                    etudiant.build_access_token(),
                    new Consumer<ArrayList<Semestre>>() {
                        @Override
                        public void accept(ArrayList<Semestre> s) {
                            semestres = s;
                            load_grid();
                        }
                    },
                    new Consumer<String>() {
                        @Override
                        public void accept(String error) {
                            // oubtou_log : no ressources
                            Toast.makeText(EtudiantSemestreActivity.this, error, Toast.LENGTH_SHORT).show();
                        }
                    }
            );
        }else{
            // user not exit you need to redirect user to login form
        }
        //---
    }

    public void load_grid(){
        //semestres = SemestreController.getSemestres();
        /*
            oubtou_log : onclick you need to send module id to next avtivity !!!
         */
        RecyclerView moduleRecycler = (RecyclerView) findViewById(R.id.semestre_recycler);
        EtudiantSemestreRecycler adapter = new EtudiantSemestreRecycler(this, semestres);
        moduleRecycler.setAdapter(adapter);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(RecyclerView.VERTICAL);
        moduleRecycler.setLayoutManager(manager);
    }

    public void goback(View view) {
        finish();
    }
}
