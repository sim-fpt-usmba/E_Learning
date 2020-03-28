package ma.ac.usmba.fpt.e_learning.View;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;

import java.util.ArrayList;

import ma.ac.usmba.fpt.e_learning.EtudiantModuleActivity;
import ma.ac.usmba.fpt.e_learning.Model.Module;
import ma.ac.usmba.fpt.e_learning.R;


public class ModuleGrid extends GridLayout {
    ArrayList<Button>  moduleBtnList;
    public ModuleGrid(final Context context, ArrayList<Module> modules) {
        super(context);

        this.setUseDefaultMargins(true);

        moduleBtnList = new ArrayList<>();

        this.setRowCount(modules.size()/3);
        this.setColumnCount(3);
        for (Module m: modules) {
            Button tempBtn = new Button(context);
            tempBtn.setText(m.getName());
            tempBtn.setPadding(10,10,10,10);
            tempBtn.setTextColor(getResources().getColor(R.color.whitecolor));
            tempBtn.setBackgroundColor(getResources().getColor(R.color.dark_gray));
            tempBtn.setHeight((int)(tempBtn.getHeight() * 1.2));
            tempBtn.setWidth(tempBtn.getWidth());
            tempBtn.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, EtudiantModuleActivity.class);
                    context.startActivity(intent);
                }
            });
            this.addView(tempBtn);
        }
    }

    public ModuleGrid(Context context) {
        super(context);
    }

    public ModuleGrid(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ModuleGrid(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    private int dpToPx(int dp,Context context) {
        return (int)(dp * context.getResources().getDisplayMetrics().density);
    }
}
