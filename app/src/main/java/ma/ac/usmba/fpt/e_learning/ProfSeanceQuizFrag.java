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

import java.util.ArrayList;

import ma.ac.usmba.fpt.e_learning.Adapters.ProfSeanceQuizAdapter;
import ma.ac.usmba.fpt.e_learning.Adapters.ProfSeanceQuizAnswersAdapter;
import ma.ac.usmba.fpt.e_learning.Controller.QuizController;
import ma.ac.usmba.fpt.e_learning.Model.QuestionAnswer;
import ma.ac.usmba.fpt.e_learning.Model.Quiz;

public class ProfSeanceQuizFrag extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_prof_seance_quiz, container, false);

        ArrayList<Quiz> quizzes = QuizController.getEtudiantAnswer();

        RecyclerView answersRecycler = (RecyclerView) view.findViewById(R.id.quiz_recycler);
        ProfSeanceQuizAdapter adapter = new ProfSeanceQuizAdapter(getContext(), quizzes);
        answersRecycler.setAdapter(adapter);

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        answersRecycler.setLayoutManager(manager);

        return view;
    }
}
