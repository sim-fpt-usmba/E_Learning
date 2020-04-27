package ma.ac.usmba.fpt.e_learning.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import ma.ac.usmba.fpt.e_learning.Model.QuestionAnswer;
import ma.ac.usmba.fpt.e_learning.R;

public class ProfSeanceQuizAnswersAdapter extends RecyclerView.Adapter<ProfSeanceQuizAnswersAdapter.QuestionAnswerHolder> {

    private ArrayList<QuestionAnswer> answers;
    private LayoutInflater inflater;
    private Context context;
    public ProfSeanceQuizAnswersAdapter(Context context, ArrayList<QuestionAnswer> answers) {
        this.context = context;
        this.answers = answers;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public QuestionAnswerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.prof_seance_answer_item,parent,false);
        QuestionAnswerHolder viewHolder = new QuestionAnswerHolder(view);
        return viewHolder;
    }

    @Override
    public int getItemCount() {
        return answers.size();
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionAnswerHolder holder, int position) {
        QuestionAnswer curentAnswer = answers.get(position);
        holder.setData(curentAnswer,position);
    }

    class QuestionAnswerHolder extends RecyclerView.ViewHolder {
        TextView questionTxt ,answerTxt;
        LinearLayout answersLayout, container;
        ImageView answerIcon;
        int position;
        QuestionAnswer answer;
        public QuestionAnswerHolder(@NonNull View itemView) {
            super(itemView);
            questionTxt = (TextView) itemView.findViewById(R.id.question);
            answersLayout = (LinearLayout) itemView.findViewById(R.id.answer_layout);
            container = (LinearLayout) itemView.findViewById(R.id.container);
            answerTxt = (TextView) itemView.findViewById(R.id.answer);
            answerIcon = (ImageView) itemView.findViewById(R.id.correct_icon);
        }

        public void setData(QuestionAnswer answer, int position) {
            this.answer = answer;
            this.position = position;
            questionTxt.setText(answer.getQuestion());
            if (answer.isCorrect()) {
                answerIcon.setImageResource(R.drawable.correct);
            } else {
                answerIcon.setImageResource(R.drawable.wrong);
            }
            answerTxt.setText(answer.getStudentAnswer());
        }
    }

    private int dpToPx(int dp,Context context) {
        return (int)(dp * context.getResources().getDisplayMetrics().density);
    }
}
