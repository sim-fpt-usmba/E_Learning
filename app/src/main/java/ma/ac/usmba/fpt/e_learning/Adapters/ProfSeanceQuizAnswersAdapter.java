package ma.ac.usmba.fpt.e_learning.Adapters;

import android.content.Context;
import android.graphics.Color;
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
import java.util.Map;

import ma.ac.usmba.fpt.e_learning.Model.QuestionAnswer;
import ma.ac.usmba.fpt.e_learning.R;

public class ProfSeanceQuizAnswersAdapter extends RecyclerView.Adapter<ProfSeanceQuizAnswersAdapter.MyViewHolder> {

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
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.prof_seance_answer_item,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public int getItemCount() {
        return answers.size();
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        QuestionAnswer curentAnswer = answers.get(position);
        holder.setData(curentAnswer,position);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView questionTxt;
        LinearLayout answersLayout, container;
        int position;
        QuestionAnswer answer;
        ArrayList<TextView> textViews;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            questionTxt = (TextView) itemView.findViewById(R.id.question);
            answersLayout = (LinearLayout) itemView.findViewById(R.id.answer_layout);
            container = (LinearLayout) itemView.findViewById(R.id.container);
            textViews = new ArrayList<>();

            LinearLayout.LayoutParams params =(LinearLayout.LayoutParams) answersLayout.getLayoutParams();
            for (int i = 0; i < 5; i++) {
                LinearLayout layout = new LinearLayout(context);
                TextView answerTxt = new TextView(context);
                layout.setLayoutParams(params);
                answerTxt.setText("answer " + i);
                layout.addView(answerTxt);
                textViews.add(answerTxt);
                container.addView(layout);
            }
        }

        public void setData(QuestionAnswer answer, int position) {
            this.answer = answer;
            this.position = position;
            questionTxt.setText(answer.getQuestion());
            for (TextView txt : textViews) {
                txt.setText("answer");

            }

        }
    }

    private int dpToPx(int dp,Context context) {
        return (int)(dp * context.getResources().getDisplayMetrics().density);
    }
}
