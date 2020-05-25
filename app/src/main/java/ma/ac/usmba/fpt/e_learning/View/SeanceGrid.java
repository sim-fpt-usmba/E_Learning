package ma.ac.usmba.fpt.e_learning.View;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;

import java.util.ArrayList;

import ma.ac.usmba.fpt.e_learning.EtudiantSeanceActivity;
import ma.ac.usmba.fpt.e_learning.Model.Seance;
import ma.ac.usmba.fpt.e_learning.ProfFiliereActivity;
import ma.ac.usmba.fpt.e_learning.ProfSeanceActivity;
import ma.ac.usmba.fpt.e_learning.R;


public class SeanceGrid extends GridLayout {
    ArrayList<Button> seanceBtnList;

    public SeanceGrid(final Context context, ArrayList<Seance> seances) {
        super(context);

        this.setUseDefaultMargins(true);

        seanceBtnList = new ArrayList<>();

        this.setRowCount(seances.size() / 3);
        this.setColumnCount(3);
        for (Seance m : seances) {
            Button tempBtn = new Button(context);
            tempBtn.setText(m.getTitre());
            tempBtn.setPadding(10, 10, 10, 10);
            tempBtn.setTextColor(getResources().getColor(R.color.whitecolor));
            tempBtn.setBackgroundColor(getResources().getColor(R.color.dark_gray));
            tempBtn.setHeight((int) (tempBtn.getHeight() * 1.2));
            tempBtn.setWidth(tempBtn.getWidth());
            tempBtn.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent previous = ((Activity) context).getIntent();
                    String from = previous.getStringExtra("from");
                    //TODO: check if prof or student
                    Boolean isProf;
                    //TODO: to replace with test if is prof session or student
                    if (from.equals(ProfFiliereActivity.class.getName())) {
                        isProf = true;
                    } else {
                        isProf = false;
                    }
                    Toast.makeText(context, from + "", Toast.LENGTH_SHORT).show();

                    if (isProf) {
                        Intent intent = new Intent(context, ProfSeanceActivity.class);
                        context.startActivity(intent);
                    } else {
                        Intent intent = new Intent(context, EtudiantSeanceActivity.class);
                        context.startActivity(intent);
                    }


                }
            });
            this.addView(tempBtn);
        }
    }

    public SeanceGrid(Context context) {
        super(context);
    }

    public SeanceGrid(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SeanceGrid(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private int dpToPx(int dp, Context context) {
        return (int) (dp * context.getResources().getDisplayMetrics().density);
    }
}
