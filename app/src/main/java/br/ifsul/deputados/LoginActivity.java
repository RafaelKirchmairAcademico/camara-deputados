package br.ifsul.deputados;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import br.ifsul.deputados.utils.Authentication;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if(!Authentication.checkIfAlreadyLoggedInAndRedirectToContentIfTrue(this)) {
            TextInputEditText email = findViewById(R.id.login_user_email_input);

            TextInputEditText password = findViewById(R.id.login_user_password_input);

            Button loginButton = findViewById(R.id.login_user_button);
            loginButton.setOnClickListener(view -> {
                Authentication.login(this, email.getText().toString(), password.getText().toString());
            });

            Button registerButton = findViewById(R.id.not_registered_user_button);
            registerButton.setOnClickListener(view -> {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
                finish();
            });
        }

    }

}