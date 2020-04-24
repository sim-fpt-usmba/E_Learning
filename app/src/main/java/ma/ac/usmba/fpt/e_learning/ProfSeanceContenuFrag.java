package ma.ac.usmba.fpt.e_learning;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import java.text.SimpleDateFormat;
import java.util.Date;

import ma.ac.usmba.fpt.e_learning.Controller.ContenuController;
import ma.ac.usmba.fpt.e_learning.Model.ContenuModel;

public class ProfSeanceContenuFrag extends Fragment {
    private TextView date_textview;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_prof_seance_contenu, container, false);

        date_textview = view.findViewById(R.id.date_textview);

        return view;

    }

    @Override
    public void onStart() {
        super.onStart();
        ContenuModel contenu = ContenuController.getContenu();

        date_textview.setText(formatDate(contenu.getDate()));
    }

    private String formatDate(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("E dd.MM.yyyy hh:mm");
        return simpleDateFormat.format(date) ;
    }
}
