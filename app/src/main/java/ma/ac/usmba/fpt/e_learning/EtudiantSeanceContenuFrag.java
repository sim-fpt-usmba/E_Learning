package ma.ac.usmba.fpt.e_learning;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ma.ac.usmba.fpt.e_learning.Controller.EtudiantQuizAdapter;
import ma.ac.usmba.fpt.e_learning.Controller.QuizController;
import ma.ac.usmba.fpt.e_learning.Model.Quiz;

public class EtudiantSeanceContenuFrag extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_etudiant_seance_contenu, container, false);
        return view;
    }

}
