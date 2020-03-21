package ma.ac.usmba.fpt.e_learning;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Pattern;

public class ProfLoginActivity extends AppCompatActivity {
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    //"(?=.*[0-9])" +         //at least 1 digit
                    //"(?=.*[a-z])" +         //at least 1 lower case letter
                    //"(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=\\S+$)"
                    );

    private EditText email;
    private EditText  password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prof_login);

        email= findViewById(R.id.email);
        password = findViewById(R.id.password);
    }
    public void goback(View view) {

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
     if ((!validateEmail()) ||(!validatePassword())){


        String input = "Email: " + email.getText().toString();
        input += "\n";

        input += "Password: " + password.getText().toString();

        Toast.makeText(this,input, Toast.LENGTH_SHORT).show();
    }
     else{
         Intent intent = new Intent(ProfLoginActivity.this, ProfAccueilActivity.class);
         startActivity(intent);
     }
    }
}
