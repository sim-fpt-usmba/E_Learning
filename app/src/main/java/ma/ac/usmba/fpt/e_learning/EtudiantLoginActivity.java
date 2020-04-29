package ma.ac.usmba.fpt.e_learning;

import android.content.Intent;
import android.content.SharedPreferences;
import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.lang.reflect.Type;

import ma.ac.usmba.fpt.e_learning.Model.Etudiant;

import ma.ac.usmba.fpt.e_learning.Utils.APIEndPoint;
import ma.ac.usmba.fpt.e_learning.Utils.NetworkUtils;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class EtudiantLoginActivity extends AppCompatActivity {
    private SharedPreferences sessionData;
    private NetworkUtils networkUtils;
    private Etudiant etudiant;
    private EditText cne;
    private EditText cin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etudiant_login);
        androidx.appcompat.widget.Toolbar toolbar= findViewById(R.id.constraintLayout4);
        setSupportActionBar(toolbar);
        TextView txt=findViewById(R.id.textView);
        txt.setText("Espace Etudiant");
        ImageView img=findViewById(R.id.GoBackIcon);
        img.setImageResource(R.drawable.trace_12);
        toolbar.setBackgroundColor(Color.parseColor("#3556A3"));
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        sessionData = getSharedPreferences("user_details",MODE_PRIVATE);

        if( sessionData.contains("ProfLogin") ){
            openMain();
        }

        networkUtils = new NetworkUtils();

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
            checkEtudientExists();
        }
        //TODO: remove this line after testing
        openMain();
    }

    public void showMessage(String msg){
        Toast.makeText(EtudiantLoginActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    public void openMain(){
        Intent intent = new Intent(EtudiantLoginActivity.this, EtudiantAccueilActivity.class);
        startActivity(intent);
    }

    public void  checkEtudientExists(){
        APIEndPoint apiEndPoint = networkUtils.getApiEndPoint();

        Call<ResponseBody> call = apiEndPoint.etudient_login(
                "application/json",
                cne.getText().toString(),
                cin.getText().toString()
        );
        call.enqueue(new Callback<ResponseBody>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    if (response.code() == 200) {
                        Document document = Jsoup.parse(response.body().string());
                        Element p = document.select("body").first();
                        Gson gson = new Gson();
                        Type collectionType = new TypeToken<Etudiant>() {
                        }.getType();

                        etudiant = gson.fromJson(p.text(), collectionType);

                        SharedPreferences.Editor editor = sessionData.edit();
                        editor.putString("ProfLogin", etudiant.toString());
                        editor.commit();

                        openMain();
                    } else {
                        showMessage( "Failed to login, please check your ID or password" );
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    showMessage( "Failed : " + e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                showMessage( "Failed : " + t.getMessage());
            }
        });
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;

    }
}

