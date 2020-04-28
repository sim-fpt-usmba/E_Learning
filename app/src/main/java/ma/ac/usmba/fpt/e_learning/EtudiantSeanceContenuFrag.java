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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import ma.ac.usmba.fpt.e_learning.Adapters.ProfContenuAudiosAdapter;
import ma.ac.usmba.fpt.e_learning.Adapters.ProfContenuFilesAdapter;
import ma.ac.usmba.fpt.e_learning.Controller.ContenuController;
import ma.ac.usmba.fpt.e_learning.Model.AudioModel;
import ma.ac.usmba.fpt.e_learning.Model.ContenuModel;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ma.ac.usmba.fpt.e_learning.Controller.EtudiantQuizAdapter;
import ma.ac.usmba.fpt.e_learning.Controller.QuizController;
import ma.ac.usmba.fpt.e_learning.Model.Quiz;

public class EtudiantSeanceContenuFrag extends Fragment {
    private TextView date_textview,description_textview;
    private RecyclerView files,audios;
    private RecyclerView.Adapter files_adapter,audios_adapter;
    private ArrayList<String> file_path;
    private ArrayList<AudioModel> audio_path;
    private ContenuModel contenu = ContenuController.getContenu();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_etudiant_seance_contenu, container, false);

        date_textview = view.findViewById(R.id.date_textview);
        description_textview = view.findViewById(R.id.description_textview);
        files = view.findViewById(R.id.files_recycler);
        audios = view.findViewById(R.id.audios_recycler);

        //TODO: Adding files paths to the recyclerView
        file_path = contenu.getFiles();
        RecyclerView.LayoutManager file_layoutManager = new LinearLayoutManager(getContext());
        files.setLayoutManager(file_layoutManager);
        files_adapter = new ProfContenuFilesAdapter(getContext(),file_path);
        files.setAdapter(files_adapter);


        //TODO: Adding audios to the recyclerView
        audio_path = contenu.getAudios();
        RecyclerView.LayoutManager audio_layoutManager = new LinearLayoutManager(getContext());
        audios.setLayoutManager(audio_layoutManager);
        audios_adapter = new ProfContenuAudiosAdapter(getContext(), audio_path);
        audios.setAdapter(audios_adapter);

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
