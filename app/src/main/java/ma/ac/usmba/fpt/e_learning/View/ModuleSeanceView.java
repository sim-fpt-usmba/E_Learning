package ma.ac.usmba.fpt.e_learning.View;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;

import ma.ac.usmba.fpt.e_learning.Model.Module;
import ma.ac.usmba.fpt.e_learning.R;

public class ModuleSeanceView extends ConstraintLayout {
    TextView moduleTxt;
    SeanceGrid seanceGrid;
    static int TAG = 0;

    public ModuleSeanceView(final Context context, final Module module) {
        super(context);

        LayoutParams layoutParams = new LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT);
        this.setLayoutParams(layoutParams);

        moduleTxt = new TextView(context);
        moduleTxt.setId(ViewCompat.generateViewId());
        moduleTxt.setText(module.getName());
        moduleTxt.setTextSize(25);
        moduleTxt.setBackground(ContextCompat.getDrawable(context, R.drawable.rectangle_119_1));
        moduleTxt.setGravity(Gravity.CENTER);
        moduleTxt.setTextColor(getResources().getColor(R.color.whitecolor));
        moduleTxt.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (seanceGrid == null) {
                    addAllViews(context,module);
                }else if (seanceGrid.getChildCount() == 0) {
                    addAllViews(context,module);
                } else {
                    seanceGrid.removeAllViews();
                }
            }
        });
        this.addView(moduleTxt);
        ConstraintSet set = new ConstraintSet();
        set.clone(this);
        set.connect(moduleTxt.getId(), ConstraintSet.RIGHT,
                ConstraintSet.PARENT_ID, ConstraintSet.RIGHT);
        set.connect(moduleTxt.getId(), ConstraintSet.LEFT,
                ConstraintSet.PARENT_ID, ConstraintSet.LEFT);
        set.applyTo(this);

    }

    public void addAllViews(Context context, Module module) {
        seanceGrid = new SeanceGrid(context, module.getSeances());
        seanceGrid.setId(ViewCompat.generateViewId());
        this.addView(seanceGrid);


        ConstraintSet set = new ConstraintSet();
        set.clone(this);

        set.connect(seanceGrid.getId(), ConstraintSet.TOP,
                moduleTxt.getId(), ConstraintSet.BOTTOM);
        set.connect(seanceGrid.getId(), ConstraintSet.RIGHT,
                ConstraintSet.PARENT_ID, ConstraintSet.RIGHT);
        set.connect(seanceGrid.getId(), ConstraintSet.LEFT,
                ConstraintSet.PARENT_ID, ConstraintSet.LEFT);


        set.applyTo(this);
    }

    public ModuleSeanceView(Context context) {
        super(context);
    }

    public ModuleSeanceView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ModuleSeanceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private int dpToPx(int dp, Context context) {
        return (int) (dp * context.getResources().getDisplayMetrics().density);
    }
}
