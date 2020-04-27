package ma.ac.usmba.fpt.e_learning.Adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.zip.Inflater;

import ma.ac.usmba.fpt.e_learning.Model.Prof;
import ma.ac.usmba.fpt.e_learning.Model.QuestionAnswer;
import ma.ac.usmba.fpt.e_learning.Model.Quiz;
import ma.ac.usmba.fpt.e_learning.R;

public class ProfSeanceQuizAdapter extends RecyclerView.Adapter<ProfSeanceQuizAdapter.QuizHolder> {
    private ArrayList<Quiz> quizzes;
    private LayoutInflater inflater;
    private Context context;

    public ProfSeanceQuizAdapter(Context context, ArrayList<Quiz> quizzes) {
        this.quizzes = quizzes;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public QuizHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.prof_seance_quiz_item, parent, false);
        QuizHolder holder = new QuizHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull QuizHolder holder, int position) {
        Quiz currentQuiz = quizzes.get(position);
        holder.setData(currentQuiz,position);
    }

    @Override
    public int getItemCount() {
        return quizzes.size();
    }

    class QuizHolder extends RecyclerView.ViewHolder {
        int position;
        Quiz quiz;
        TextView nameTxt, scoreTxt;
        RecyclerView answersRecycler;

        public QuizHolder(@NonNull View itemView) {
            super(itemView);
            nameTxt = (TextView) itemView.findViewById(R.id.name);
            scoreTxt = (TextView) itemView.findViewById(R.id.score_text);
            answersRecycler = (RecyclerView) itemView.findViewById(R.id.answers_recyclerview);

            LinearLayoutManager manager = new LinearLayoutManager(context);
            manager.setOrientation(LinearLayoutManager.VERTICAL);
            answersRecycler.setLayoutManager(manager);
        }

        public void setData(Quiz currentQuiz, int position) {
            nameTxt.setText(currentQuiz.getUser().getName());
            scoreTxt.setText(currentQuiz.getScore());
            ProfSeanceQuizAnswersAdapter adapter = new ProfSeanceQuizAnswersAdapter(context, currentQuiz.getQuestionAnswers());
            answersRecycler.setAdapter(adapter);
        }
    }

}
