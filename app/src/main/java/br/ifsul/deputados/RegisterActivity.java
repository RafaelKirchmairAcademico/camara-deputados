package br.ifsul.deputados;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import br.ifsul.deputados.utils.Authentication;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        TextInputEditText email = findViewById(R.id.register_user_email_input);

        TextInputEditText password = findViewById(R.id.register_user_password_input);

        Button register = findViewById(R.id.register_user_button);
        register.setOnClickListener(view -> {
                String stringEmail = email.getText().toString();
                String stringPassword = password.getText().toString();

                Authentication.register(this, stringEmail, stringPassword);
        });

        Button alreadyRegistered = findViewById(R.id.already_registered_user_button);
        alreadyRegistered.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            finish();
        });

    }
}