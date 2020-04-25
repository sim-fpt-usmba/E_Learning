package ma.ac.usmba.fpt.e_learning;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ProfSeanceQuizFrag extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_prof_seance_quiz, container, false);

        TextView name = view.findViewById(R.id.name);
        name.setText("Ali Elaissaoui");
        TextView score = view.findViewById(R.id.score_text);
        score.setText("5/6");

        return view;
    }
}
