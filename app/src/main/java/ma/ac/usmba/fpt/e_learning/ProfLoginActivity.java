package ma.ac.usmba.fpt.e_learning;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.regex.Pattern;

import ma.ac.usmba.fpt.e_learning.Model.Prof;
import ma.ac.usmba.fpt.e_learning.Utils.APIEndPoint;
import ma.ac.usmba.fpt.e_learning.Utils.NetworkUtils;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfLoginActivity extends AppCompatActivity {
    private SharedPreferences SessionData;
    private NetworkUtils networkUtils;
    private Prof prof;
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    //"(?=.*[0-9])" +         //at least 1 digit
                    //"(?=.*[a-z])" +         //at least 1 lower case letter
                    //"(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=\\S+$)"
            );

    private EditText email;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prof_login);

        SessionData = getSharedPreferences("user_details", MODE_PRIVATE);

        if (SessionData.contains("EtudiantLogin")) {
            openMain();
        }

        networkUtils = new NetworkUtils();

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
    }

    public void goback(View view) {
        finish();
    }

    private boolean validateEmail() {
        String emailInput = email.getText().toString().trim();

        if (emailInput.isEmpty()) {
            email.setError("Field can't be empty");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            email.setError("Please enter a valid email address");
            return false;
        } else {
            email.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {
        String passwordInput = password.getText().toString().trim();

        if (passwordInput.isEmpty()) {
            password.setError("Field can't be empty");
            return false;
        } else if (passwordInput.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[-+!*$@%_])([-+!*$@%_\\w]{8,15})$")) {//PASSWORD_PATTERN.matcher(passwordInput).matches()
            password.setError("Password too weak");
            return false;
        } else {
            password.setError(null);
            return true;
        }
    }


    public void onClickValider(View view) {
        if ((!validateEmail()) || (!validatePassword())) {


            String input = "Email: " + email.getText().toString();
            input += "\n";

            input += "Password: " + password.getText().toString();

            Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
            showMessage(input);
        } else {
            checkEtudientExists();
        }
        //TODO: remove this line after testing
        openMain();
    }

    public void showMessage(String msg) {
        Toast.makeText(ProfLoginActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    public void openMain() {
        Intent intent = new Intent(ProfLoginActivity.this, ProfAccueilActivity.class);
        startActivity(intent);
    }


    public void checkEtudientExists() {
        APIEndPoint apiEndPoint = networkUtils.getApiEndPoint();

        Call<ResponseBody> call = apiEndPoint.prof_login(
                "application/json",
                email.getText().toString(),
                password.getText().toString()
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
                        Type collectionType = new TypeToken<Prof>() {
                        }.getType();

                        prof = gson.fromJson(p.text(), collectionType);

                        SharedPreferences.Editor editor = SessionData.edit();
                        editor.putString("EtudiantLogin", prof.toString());
                        editor.commit();

                        openMain();
                    } else {
                        showMessage("Failed to login, please check your ID or password");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    showMessage("Failed : " + e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                showMessage("Failed : " + t.getMessage());
            }
        });
    }
}
