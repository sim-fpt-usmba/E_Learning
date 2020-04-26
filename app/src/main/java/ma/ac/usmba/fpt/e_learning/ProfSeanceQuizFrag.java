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

import ma.ac.usmba.fpt.e_learning.Adapters.ProfSeanceQuizAnswersAdapter;
import ma.ac.usmba.fpt.e_learning.Controller.QuizController;
import ma.ac.usmba.fpt.e_learning.Model.QuestionAnswer;
import ma.ac.usmba.fpt.e_learning.Model.Quiz;

public class ProfSeanceQuizFrag extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_prof_seance_quiz, container, false);

        TextView name = view.findViewById(R.id.name);
        name.setText("Ali Elaissaoui");
        TextView score = view.findViewById(R.id.score_text);
        score.setText("5/6");

        ArrayList<Quiz> quizzes = QuizController.getEtudiantAnswer();
        ArrayList<QuestionAnswer> answers = (ArrayList<QuestionAnswer>) quizzes.get(0).getQuestionAnswers();
        RecyclerView answersRecycler = (RecyclerView) view.findViewById(R.id.answers_recyclerview);
        ProfSeanceQuizAnswersAdapter answersAdapter = new ProfSeanceQuizAnswersAdapter(getContext(), answers);
        answersRecycler.setAdapter(answersAdapter);

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        answersRecycler.setLayoutManager(manager);

        return view;
    }
}
