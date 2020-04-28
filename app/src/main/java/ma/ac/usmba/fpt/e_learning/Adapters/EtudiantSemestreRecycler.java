package ma.ac.usmba.fpt.e_learning.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ma.ac.usmba.fpt.e_learning.Model.Semestre;
import ma.ac.usmba.fpt.e_learning.R;

public class EtudiantSemestreRecycler extends RecyclerView.Adapter<EtudiantSemestreRecycler.SemestreHolder> {
    ArrayList<Semestre> semestres;
    LayoutInflater inflater;
    Context context;

    public EtudiantSemestreRecycler(Context context, ArrayList<Semestre> semestres) {
        this.semestres = semestres;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public SemestreHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.etudiant_semestre_item, parent, false);
        SemestreHolder holder = new SemestreHolder(view);
        return holder;
    }

    @Override
    public int getItemCount() {
        return semestres.size();
    }

    @Override
    public void onBindViewHolder(@NonNull SemestreHolder holder, int position) {
        Semestre currentSemestre = semestres.get(position);
        holder.setData(currentSemestre, position);
    }

    class SemestreHolder extends RecyclerView.ViewHolder {
        TextView semestreTxt;
        RecyclerView moduleRecycler;
        public SemestreHolder(@NonNull View itemView) {
            super(itemView);
            semestreTxt = itemView.findViewById(R.id.semestre_txt);
            moduleRecycler = itemView.findViewById(R.id.module_recycler);

            LinearLayoutManager manager = new LinearLayoutManager(context);
            manager.setOrientation(RecyclerView.VERTICAL);
            moduleRecycler.setLayoutManager(manager);
        }

        public void setData(Semestre currentSemester, int position) {
            semestreTxt.setText(currentSemester.getName());
            //TODO: set the adapter for semestreRecycler
            EtudiantModuleAdapter adapter = new EtudiantModuleAdapter(context, currentSemester.getModules());
            moduleRecycler.setAdapter(adapter);
        }
    }
}

