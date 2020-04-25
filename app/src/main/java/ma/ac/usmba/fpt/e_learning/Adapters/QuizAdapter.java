package ma.ac.usmba.fpt.e_learning.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

import ma.ac.usmba.fpt.e_learning.Model.QuestionAnswer;
import ma.ac.usmba.fpt.e_learning.Model.Quiz;
import ma.ac.usmba.fpt.e_learning.R;

public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.ViewHolder>{
    ArrayList<QuestionAnswer> quiz_array;
    Context context;

    public QuizAdapter(Context context,ArrayList<QuestionAnswer> quiz_array) {
        this.quiz_array = quiz_array;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        QuestionAnswer q = quiz_array.get(position);
        String content = q.getQuestion() + "\t\t" + q.getAnswers().size() + " Choix";
        holder.textView.setText(content);
        holder.delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quiz_array.remove(position);
                notifyItemRemoved(position);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return quiz_array.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        Button delete_button;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.txt_view_question);
            delete_button = itemView.findViewById(R.id.delete_button);
        }


    }
}
