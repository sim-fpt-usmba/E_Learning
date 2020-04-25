package ma.ac.usmba.fpt.e_learning;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import ma.ac.usmba.fpt.e_learning.Adapters.ProfContenuFilesAdapter;
import ma.ac.usmba.fpt.e_learning.Controller.ContenuController;
import ma.ac.usmba.fpt.e_learning.Model.ContenuModel;

public class ProfSeanceContenuFrag extends Fragment {
    private TextView date_textview,description_textview;
    private RecyclerView files;
    private ProfContenuFilesAdapter filesAdapter;
    private RecyclerView.Adapter adapter;
    private ArrayList<String> paths;
    private ContenuModel contenu = ContenuController.getContenu();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_prof_seance_contenu, container, false);

        date_textview = view.findViewById(R.id.date_textview);
        description_textview = view.findViewById(R.id.description_textview);
        files = view.findViewById(R.id.files_recycler);

        //TODO: Adding files paths to the recyclerView
        paths = contenu.getFiles();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        files.setLayoutManager(layoutManager);
        adapter = new ProfContenuFilesAdapter(getContext(),paths);
        files.setAdapter(adapter);

/*
        private void add_audios(ArrayList list, String audio_path) {
            list.add(new AudioModel(audio_path));
            this.audios = (RecyclerView) findViewById(R.id.audio_list);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
            this.audios.setLayoutManager(mLayoutManager);
            adapter = new AudioAdapter(this, list);
            this.audios.setAdapter(adapter);
        }*/
        return view;

    }

    @Override
    public void onStart() {
        super.onStart();
        date_textview.setText(formatDate(contenu.getDate()));
        description_textview.setText(contenu.getDescription());
    }




    private String formatDate(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy  '('HH:mm')'");
        return simpleDateFormat.format(date) ;
    }

}
