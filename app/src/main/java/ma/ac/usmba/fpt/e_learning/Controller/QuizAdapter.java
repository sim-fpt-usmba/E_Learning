package ma.ac.usmba.fpt.e_learning.Controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

import ma.ac.usmba.fpt.e_learning.Model.Quiz;
import ma.ac.usmba.fpt.e_learning.R;

public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.ViewHolder>{
    ArrayList<Quiz> quiz_array = new ArrayList<>();
    Context context;

    public QuizAdapter(Context context,ArrayList<Quiz> quiz_array) {
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
        Quiz q = quiz_array.get(position);
        holder.textView.setText(q.getQuestion());
        holder.textView1.setText("Nombre de choix : " + q.getReponses().size());
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quiz_array.remove(position);
                notifyItemRemoved(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return quiz_array.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        TextView textView1;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.txt_view_question);
            textView1 = itemView.findViewById(R.id.text_view_nbr_choix);
            imageView = itemView.findViewById(R.id.img_view_delete);
        }


    }
}
