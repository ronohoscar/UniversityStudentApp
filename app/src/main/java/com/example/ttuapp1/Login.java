package com.example.ttuapp1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
private EditText email,password;
private Button login;
private TextView toReg,resetpassword;
FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        firebaseAuth = FirebaseAuth.getInstance();
        email =findViewById(R.id.editTextEmailreset);
        password=findViewById(R.id.editTextPasswordLogin);
        resetpassword = findViewById(R.id.textViewresetpassword);
        login=findViewById(R.id.buttonReset);
        toReg=findViewById(R.id.textViewToLog);

        if(firebaseAuth.getCurrentUser()!=null){
            Intent intent = new Intent(Login.this, MainActivity.class);
            Login.this.startActivity(intent);
            finish();
        }
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        toReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Login.this,Register.class);
                startActivity(intent);
                finish();
            }
        });
        resetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Login.this,Reset.class);
                startActivity(intent);
                finish();
            }
        });
    }
    private void login() {
        String usernamelogin = email.getText().toString();
        String passwordlogin = password.getText().toString();

        if (usernamelogin.isEmpty()){
            Toast.makeText(Login.this, "enter an email.address", Toast.LENGTH_SHORT).show();
            return;
        } if (passwordlogin.isEmpty()) {
            Toast.makeText(Login.this, "Enter password", Toast.LENGTH_SHORT).show();
            return;
        }if (passwordlogin.length() < 8) {
            Toast.makeText(Login.this, "password too short", Toast.LENGTH_SHORT).show();
        }
            firebaseAuth.signInWithEmailAndPassword(usernamelogin, passwordlogin).addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful()) {
                        if (firebaseAuth.getCurrentUser().isEmailVerified()){
                            Intent intent = new Intent(Login.this, MainActivity.class);
                            Login.this.startActivity(intent);
                            finish();
                        }else {
                            Toast.makeText(Login.this, "Please Verify Your Email First!", Toast.LENGTH_SHORT).show();
                        }


                    } else {
                        Toast.makeText(Login.this, "Login Failed", Toast.LENGTH_SHORT).show();
                    }
                }

            });
        }
    }


