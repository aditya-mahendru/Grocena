package com.example.javagrocena;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegistrationActivity extends AppCompatActivity {

    Button signUp;
    TextView backToSignIn;
    EditText name, mobNum,password, confirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        signUp = findViewById(R.id.button_register);
        backToSignIn = findViewById(R.id.goBackText);
        name = findViewById(R.id.name);
        mobNum = findViewById(R.id.text_mob_num);
        password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.confirmPassword);

        backToSignIn.setOnClickListener(view -> startActivity(new Intent(RegistrationActivity.this,LoginUI.class)));

    }
}