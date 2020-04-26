package ma.ac.usmba.fpt.e_learning.Controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import java.util.ArrayList;
import java.util.Map;

import ma.ac.usmba.fpt.e_learning.EtudiantSeanceContenuFrag;
import ma.ac.usmba.fpt.e_learning.Model.QuestionAnswer;
import ma.ac.usmba.fpt.e_learning.Model.Quiz;
import ma.ac.usmba.fpt.e_learning.R;

public class EtudiantQuizAdapter extends RecyclerView.Adapter<EtudiantQuizAdapter.ViewHolder> {
    ArrayList<QuestionAnswer> arrayList;
    Context context;

    public EtudiantQuizAdapter(Context context, ArrayList<QuestionAnswer> arrayList) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.etudiant_quiz_recycler, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        QuestionAnswer q = arrayList.get(position);
        holder.question.setText(q.getQuestion());
        for (Map.Entry reponse : q.getAnswers().entrySet()) {
            RadioButton radioButton = new RadioButton(context);
            radioButton.setId(ViewCompat.generateViewId());
            radioButton.setText(reponse.getKey().toString());
            radioButton.setTextColor(context.getResources().getColor(R.color.dark));
            holder.reponses.addView(radioButton);
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView question;
        RadioGroup reponses;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            question = itemView.findViewById(R.id.etudiant_quiz_question);
            reponses = itemView.findViewById(R.id.etudiant_quiz_reponses);
        }
    }
}
