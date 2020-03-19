package ma.ac.usmba.fpt.e_learning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ProfLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prof_login);
    }
    public void goback(View view) {

    }

    public void onClickValider(View view) {
        Intent intent = new Intent(ProfLoginActivity.this, ProfAccueilActivity.class);
        startActivity(intent);
    }
}
