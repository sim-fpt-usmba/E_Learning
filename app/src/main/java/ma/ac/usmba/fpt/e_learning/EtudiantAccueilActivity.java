package ma.ac.usmba.fpt.e_learning;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Consumer;

import ma.ac.usmba.fpt.e_learning.Controller.EtudiantAPI;
import ma.ac.usmba.fpt.e_learning.Model.Etudiant;
import ma.ac.usmba.fpt.e_learning.Model.Seance;

public class EtudiantAccueilActivity extends AppCompatActivity {
    private SharedPreferences sessionData;
    private EtudiantAPI API;
    public Etudiant etudiant;
    public Seance seance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etudiant_accueil);
        API = new EtudiantAPI();

        sessionData = getSharedPreferences("user_details",MODE_PRIVATE);

        if( sessionData.contains("Etudiant") ){
            etudiant = API.Object_from_Json(
                    sessionData.getString("Etudiant", null )
            );

            /*
             oubtou_log : check if has a lesson now
                          you can use this, for show or hide " Assister Seance "
            */
            API.course_now(
                    etudiant.build_access_token(),
                    "",
                    new Consumer<Seance>() {
                        @Override
                        public void accept(Seance s) {
                            seance = s;
                            Toast.makeText(EtudiantAccueilActivity.this, seance.getTitre(), Toast.LENGTH_SHORT).show();
                        }
                    },
                    new Consumer<String>() {
                        @Override
                        public void accept(String error) {
                            // oubtou_log : no seance started now
                            Toast.makeText(EtudiantAccueilActivity.this, error, Toast.LENGTH_SHORT).show();
                        }
                    }
            );
        }else{
            // user not exit you need to redirect user to login form
        }

    }

    public void onClickAssister(View view) {
        Intent intent = new Intent(EtudiantAccueilActivity.this, EtudiantSeanceActivity.class);

        // oubtou_log : here you need to use this.seance if it's not empty
        intent.putExtra("seance_id", seance.getId());

        startActivity(intent);
    }

    public void onClickResources(View view) {
        Intent intent = new Intent(EtudiantAccueilActivity.this, EtudiantSemestreActivity.class);
        startActivity(intent);
    }

    public void goback(View view) {
        finish();
    }
}
