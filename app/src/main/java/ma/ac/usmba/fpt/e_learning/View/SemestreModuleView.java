package ma.ac.usmba.fpt.e_learning.View;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;

import ma.ac.usmba.fpt.e_learning.EtudiantModuleActivity;
import ma.ac.usmba.fpt.e_learning.Model.Semestre;
import ma.ac.usmba.fpt.e_learning.R;

public class SemestreModuleView extends ConstraintLayout {
    TextView semestreTxt;
    ModuleGrid moduleGrid;
    static int TAG = 0;
    public SemestreModuleView(final Context context, Semestre semestre) {
        super(context);

        LayoutParams layoutParams = new LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT);
        this.setLayoutParams(layoutParams);

        semestreTxt = new TextView(context);
        semestreTxt.setId(ViewCompat.generateViewId());
        semestreTxt.setText(semestre.getName());
        semestreTxt.setTextSize(25);
        semestreTxt.setWidth(dpToPx(297,context));
        semestreTxt.setHeight(dpToPx(56, context));
        semestreTxt.setGravity(Gravity.CENTER);
        semestreTxt.setTextColor(getResources().getColor(R.color.whitecolor));
        semestreTxt.setBackground(ContextCompat.getDrawable(context, R.drawable.rectangle_119_1));

        this.addView(semestreTxt);
        moduleGrid = new ModuleGrid(context, semestre.getModules());
        moduleGrid.setId(ViewCompat.generateViewId());
        this.addView(moduleGrid);


        ConstraintSet set = new ConstraintSet();
        set.clone(this);
        set.connect(semestreTxt.getId(), ConstraintSet.RIGHT,
                ConstraintSet.PARENT_ID, ConstraintSet.RIGHT);
        set.connect(semestreTxt.getId(), ConstraintSet.LEFT,
                ConstraintSet.PARENT_ID, ConstraintSet.LEFT);
        set.connect(moduleGrid.getId(), ConstraintSet.TOP,
                semestreTxt.getId(), ConstraintSet.BOTTOM);
        set.connect(moduleGrid.getId(), ConstraintSet.RIGHT,
                ConstraintSet.PARENT_ID, ConstraintSet.RIGHT);
        set.connect(moduleGrid.getId(), ConstraintSet.LEFT,
                ConstraintSet.PARENT_ID, ConstraintSet.LEFT);


        set.applyTo(this);
    }

    public SemestreModuleView(Context context) {
        super(context);
    }

    public SemestreModuleView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SemestreModuleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private int dpToPx(int dp,Context context) {
        return (int)(dp * context.getResources().getDisplayMetrics().density);
    }
}
