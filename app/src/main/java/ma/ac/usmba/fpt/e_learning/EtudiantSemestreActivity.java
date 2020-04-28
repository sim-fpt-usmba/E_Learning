package ma.ac.usmba.fpt.e_learning;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import java.util.ArrayList;

import ma.ac.usmba.fpt.e_learning.Adapters.EtudiantSemestreRecycler;
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
