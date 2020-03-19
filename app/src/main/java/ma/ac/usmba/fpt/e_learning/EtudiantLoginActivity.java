package ma.ac.usmba.fpt.e_learning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class EtudiantLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etudiant_login);
    }

    public void goback(View view) {

    }

    public void onClickValider(View view) {
        Intent intent = new Intent(EtudiantLoginActivity.this, EtudiantAccueilActivity.class);
        startActivity(intent);
    }
}
