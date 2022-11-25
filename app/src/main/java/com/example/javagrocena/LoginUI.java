package com.example.javagrocena;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginUI extends AppCompatActivity {

    Button signIn, signUp;
    EditText EMail, Password ;

    FirebaseAuth auth;

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_login_ui);

        signIn = findViewById(R.id.button_login);
        signUp = findViewById(R.id.button_signUp);
        EMail = findViewById(R.id.text_eMail);
        Password = findViewById(R.id.text_password);
        progressBar = findViewById(R.id.progress_circular);
        progressBar.setVisibility(View.GONE);

        auth = FirebaseAuth.getInstance();

        signUp.setOnClickListener(view -> startActivity(new Intent(LoginUI.this,RegistrationActivity.class)));

        signIn.setOnClickListener(view ->
        {
            loginUser();
            progressBar.setVisibility(View.VISIBLE);
        });

    }

    private void loginUser()
    {
        String userEmail = EMail.getText().toString();
        String userPass = Password.getText().toString();

        if(TextUtils.isEmpty(userEmail))
        {
            Toast.makeText(this, "E-Mail is Empty!", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(userPass))
        {
            Toast.makeText(this, "Password is Empty!", Toast.LENGTH_SHORT).show();
        }

        auth.signInWithEmailAndPassword(userEmail,userPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful())
                {
                    progressBar.setVisibility(View.GONE);
                    Intent intent = new Intent(LoginUI.this,MainActivity.class);
                    LoginUI.this.startActivity(intent);
                    Toast.makeText(LoginUI.this, "Logged In", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(LoginUI.this, "Error"+task.getException(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}