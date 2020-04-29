package ma.ac.usmba.fpt.e_learning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toolbar;

public class ProfAccueilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prof_accueil);
        androidx.appcompat.widget.Toolbar toolbar= findViewById(R.id.constraintLayout4);
        setSupportActionBar(toolbar);
        TextView txt=findViewById(R.id.textView);
        txt.setText("Espace Enseignant");
        toolbar.setBackgroundColor(Color.parseColor("#E31731"));
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    public void onClickProfFiliere(View view) {
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
        finish();
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;

    }
}
