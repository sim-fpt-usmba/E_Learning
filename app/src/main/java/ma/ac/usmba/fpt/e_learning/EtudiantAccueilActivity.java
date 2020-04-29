package ma.ac.usmba.fpt.e_learning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class EtudiantAccueilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etudiant_accueil);
        androidx.appcompat.widget.Toolbar toolbar= findViewById(R.id.constraintLayout4);
        setSupportActionBar(toolbar);
        TextView txt=findViewById(R.id.textView);
        txt.setText("Espace Etudiant");
        ImageView img=findViewById(R.id.GoBackIcon);
        img.setImageResource(R.drawable.trace_52);
        toolbar.setBackgroundColor(Color.parseColor("#3556A3"));
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    public void onClickAssister(View view) {
        Intent intent = new Intent(EtudiantAccueilActivity.this, EtudiantSeanceActivity.class);
        startActivity(intent);
    }

    public void onClickResources(View view) {
        Intent intent = new Intent(EtudiantAccueilActivity.this, EtudiantSemestreActivity.class);
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
