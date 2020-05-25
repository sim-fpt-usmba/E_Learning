package ma.ac.usmba.fpt.e_learning;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Consumer;

import ma.ac.usmba.fpt.e_learning.Controller.EtudiantAPI;
import ma.ac.usmba.fpt.e_learning.Model.Etudiant;


public class EtudiantLoginActivity extends AppCompatActivity {
    private SharedPreferences sessionData;
    private EtudiantAPI API;
    public Etudiant etudiant;
    private EditText cne;
    private EditText cin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etudiant_login);
        API = new EtudiantAPI();

        sessionData = getSharedPreferences("user_details",MODE_PRIVATE);

        if( sessionData.contains("Etudiant") ){
            etudiant = API.Object_from_Json(
                    sessionData.getString("Etudiant", null )
            );
            openMain();
        }
        cne = findViewById(R.id.cne);
        cin = findViewById(R.id.cin);
    }

    public void goback(View view) {
        finish();
    }

    private boolean validatecne() {
        String cnee= cne.getText().toString().trim();

        if (cnee.isEmpty()) {
            cne.setError("remplir le vide");
            return false;
        } else if (cnee.matches("([A-Z]{1})"+"[0-9]{9}?")) {
            cne.setError("essayer d'ecrire: S*******");
            return false;
        } else {
            cne.setError(null);
            return true;
        }
    }
    private boolean validatecin() {
        String cinn= cin.getText().toString().trim();

        if (cinn.isEmpty()) {
            cin.setError("**********");
            return false;
        } else if (cinn.matches("([A-Z]{1,2})?"+"[0-9]*"))  {
            cin.setError("********");
            return false;
        } else {
            cin.setError(null);
            return true;
        }
    }


    public void onClickValider(View view) {
        if ((!validatecne())||(!validatecin())){
            String input;
            input = "cne: " + cne.getText().toString() + "\n";
            input += "cin: " + cin.getText().toString();
            showMessage(input);
        } else{
            API.login(
                    cne.getText().toString(),
                    cin.getText().toString(),
                    new Consumer<Etudiant>() {
                        @Override
                        public void accept(Etudiant etd) {
                            etudiant = etd;
                            SharedPreferences.Editor editor = sessionData.edit();
                            editor.putString("Etudiant", API.Object_to_Json(etudiant) );
                            editor.apply();
                            openMain();
                        }
                    },
                    new Consumer<String>() {
                        @Override
                        public void accept(String error) {
                            showMessage(error);
                        }
                    }
            );
        }
    }

    public void showMessage(String msg){
        Toast.makeText(EtudiantLoginActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    public void openMain(){
        Intent intent = new Intent(EtudiantLoginActivity.this, EtudiantAccueilActivity.class);
        startActivity(intent);
    }

}
