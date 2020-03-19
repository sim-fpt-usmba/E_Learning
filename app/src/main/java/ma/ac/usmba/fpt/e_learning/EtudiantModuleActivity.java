package ma.ac.usmba.fpt.e_learning;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

public class EtudiantModuleActivity extends AppCompatActivity {
    Button button;
    LinearLayout parent;

    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etudiant_module);

        parent = findViewById(R.id.seanceLayout);

        for (int i = 0; i < 20; i++) {
            createSeanceBtn(i);
        }
    }

    private void createSeanceBtn(int seanceNum) {
        String text = "Seance " + seanceNum;
        button = new Button(EtudiantModuleActivity.this);
        button.setText(text);
        button.setId(seanceNum);
        parent.addView(button);
    }
}
