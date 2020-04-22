package ma.ac.usmba.fpt.e_learning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ProfAccueilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prof_accueil);
    }

    public void onClickProfModule(View view) {
        Intent intent = new Intent(ProfAccueilActivity.this, ProfFiliereActivity.class);
        intent.putExtra("from", "Gerer");
        startActivity(intent);
    }

    public void onClickProfCreer(View view) {
        Intent intent = new Intent(ProfAccueilActivity.this, ProfFiliereActivity.class);
        intent.putExtra("from", "Creer");
        startActivity(intent);
    }
    public void goback(View view) {

    }
}
