package com.example.javagrocena;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationActivity extends AppCompatActivity {

    Button signUp;
    TextView backToSignIn;
    EditText name, eMail,password, confirmPassword;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        auth = FirebaseAuth.getInstance();
        signUp = findViewById(R.id.button_register);
        backToSignIn = findViewById(R.id.goBackText);
        name = findViewById(R.id.name);
        eMail = findViewById(R.id.text_eMail);
        password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.confirmPassword);

        backToSignIn.setOnClickListener(view -> startActivity(new Intent(RegistrationActivity.this,LoginUI.class)));
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isValidated())
                {
                    createUser();
                }
            }
        });

    }

    private boolean isValidated()
    {
        boolean tmp = true;
        String Pass = password.getText().toString();
        String CPass = confirmPassword.getText().toString();
        String EMail = eMail.getText().toString();

        if(!Patterns.EMAIL_ADDRESS.matcher(EMail).matches())
        {
            Toast.makeText(RegistrationActivity.this,"Invalid Email",Toast.LENGTH_SHORT).show();
            tmp = false;
        }
        if(!Pass.equals(CPass))
        {
            Toast.makeText(RegistrationActivity.this,"Passwords don't match!",Toast.LENGTH_SHORT).show();
            tmp = false;
        }
        return tmp;

    }
    private void createUser() {
        String Name = name.getText().toString();
        String EMail = eMail.getText().toString();
        String Pass = password.getText().toString();

        if(TextUtils.isEmpty(Name))
        {
            Toast.makeText(this,"Enter a Name!",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(EMail))
        {
            Toast.makeText(this,"Enter an E-Mail!",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(Pass))
        {
            Toast.makeText(this,"Enter a Password!",Toast.LENGTH_SHORT).show();
            return;
        }
        if(Pass.length() < 6)
        {
            Toast.makeText(this,"Password must have at least 6 characters",Toast.LENGTH_SHORT).show();
            return;
        }

        auth.createUserWithEmailAndPassword(EMail,Pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(RegistrationActivity.this,"Registration Successful",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(RegistrationActivity.this,"Error:"+task.getException(),Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}