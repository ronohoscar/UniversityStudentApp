package com.example.ttuapp1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.atomic.AtomicReference;

public class Register extends AppCompatActivity {
    String usernamereg, emailreg, agereg,reg_no,course, passwordreg, confirmpasswordreg;
    EditText username, email, age,reg,e_course, password, confirmpassword;
    Button btnregister;
    TextView tologin;
FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        firebaseAuth = FirebaseAuth.getInstance();
        username = findViewById(R.id.editTextUsernamereg);
        email = findViewById(R.id.editTextEmailreg);
        reg = findViewById(R.id.editTextReg_no);
        e_course = findViewById(R.id.editTextCourse);
        age = findViewById(R.id.editTextAgereg);
        password = findViewById(R.id.editTextPasswordreg);
        confirmpassword = findViewById(R.id.editTextPasswordconfirm);
        btnregister = findViewById(R.id.buttonRegister);
        tologin = findViewById(R.id.textViewToLogin);

        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reg();
            }
        });
        tologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this,Login.class);
                            Register.this.startActivity(intent);
                            finish();

            }
        });
    }

    private void reg(){
        usernamereg = username.getText().toString();
        emailreg = email.getText().toString();
        reg_no=reg.getText().toString();
        course=e_course.getText().toString();
        agereg = age.getText().toString();
        passwordreg = password.getText().toString();
        confirmpasswordreg = confirmpassword.getText().toString();

        if (usernamereg.isEmpty()){
            Toast.makeText(Register.this, "Enter a username ", Toast.LENGTH_SHORT).show();
            return;
        }
        if (emailreg.isEmpty()){
            Toast.makeText(Register.this, "An email is required", Toast.LENGTH_SHORT).show();
            return;
        }
        if (reg_no.isEmpty()){
            Toast.makeText(Register.this, "An Registration Number is required", Toast.LENGTH_SHORT).show();
            return;
        }
        if (course.isEmpty()){
            Toast.makeText(Register.this, "Course field is empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if (agereg.isEmpty()){
            Toast.makeText(Register.this, "Fill in your age ", Toast.LENGTH_SHORT).show();
            return;
        }
        if ( passwordreg.isEmpty()){
            Toast.makeText(Register.this, "You must enter a password", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!passwordreg.equals(confirmpasswordreg)){
            Toast.makeText(Register.this, "Password does't match", Toast.LENGTH_SHORT).show();
            return;
        }

        firebaseAuth.createUserWithEmailAndPassword(emailreg,passwordreg).addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    firebaseAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                            addUerData();
                           firebaseAuth.signOut();
                                Toast.makeText(Register.this, "Registered successfully, Please verify your Email before Login  ", Toast.LENGTH_SHORT).show();
                        }else {
                                Toast.makeText(Register.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


                } else {
                    Toast.makeText(Register.this, "Registration failed ", Toast.LENGTH_SHORT).show();
                }
            }

});


    }

    public void addUerData(){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
       DatabaseReference databaseReference = firebaseDatabase.getReference(firebaseAuth.getCurrentUser().getUid());
                User user = new User(usernamereg,emailreg,agereg,reg_no,course,"");
                databaseReference.setValue(user);
    }
}





