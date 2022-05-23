package com.example.ttuapp1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.service.autofill.ImageTransformation;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Reset extends AppCompatActivity {
FirebaseAuth firebaseAuth;
    EditText email;
    Button reset;
    TextView tologin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);
        email = findViewById(R.id.editTextEmailreset);
        reset = findViewById(R.id.buttonReset);
        tologin = findViewById(R.id.textViewToLog);
firebaseAuth = FirebaseAuth.getInstance();
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetpassword();
            }
        });
        tologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Reset.this,Login.class);
                Reset.this.startActivity(intent);
                finish();
            }
        });
    }
    private void resetpassword() {
        String emailreset = email.getText().toString();

        if (emailreset.isEmpty()) {
            Toast.makeText(Reset.this, "enter an email.address", Toast.LENGTH_SHORT).show();
            return;
        } else {
            firebaseAuth.sendPasswordResetEmail(emailreset).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(Reset.this, "reset link sent to your email", Toast.LENGTH_LONG).show();
                        Intent intent =new Intent(Reset.this,Login.class);
                        Reset.this.startActivity(intent);
                        finish();
                    }
                    else{
                        Toast.makeText(Reset.this, "failed to send a reset email", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }
}
