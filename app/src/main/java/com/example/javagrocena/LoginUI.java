package com.example.javagrocena;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginUI extends AppCompatActivity {

    Button signIn, signUp;
    EditText MobNo, Password ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_ui);

        signIn = findViewById(R.id.button_login);
        signUp = findViewById(R.id.button_signUp);
        MobNo = findViewById(R.id.text_mobNum);
        Password = findViewById(R.id.text_password);

        signUp.setOnClickListener(view -> startActivity(new Intent(LoginUI.this,RegistrationActivity.class)));

        signIn.setOnClickListener(view -> {

        });

    }
}