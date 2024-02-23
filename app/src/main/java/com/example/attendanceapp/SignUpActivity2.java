package com.example.attendanceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.attendanceapp.Model.AuthModels;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity2 extends AppCompatActivity {
    EditText et1;
    EditText et2;
    Button button8;
    TextView button5;
    FirebaseAuth mAuth;
    FirebaseDatabase database;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sign_up2);
        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);
        button8 = findViewById(R.id.button8);
        button5 = findViewById(R.id.textView9);
        mAuth = FirebaseAuth.getInstance();
//        getSupportActionBar().hide();
        database = FirebaseDatabase.getInstance();
        progressDialog = new ProgressDialog(SignUpActivity2.this);
        progressDialog.setTitle("Creating Account");
        progressDialog.setMessage("We are creating your account");
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressDialog.show();
                mAuth.createUserWithEmailAndPassword(et1.getText().toString(), et2.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
                            Toast.makeText(SignUpActivity2.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                            AuthModels authModels = new AuthModels(et1.getText().toString(), et2.getText().toString());
                            String id = task.getResult().getUser().getUid();
                            database.getReference().child("Users").child(id).setValue(authModels);
                            Intent intent2 = new Intent(SignUpActivity2.this, MainActivity.class);
                            intent2.putExtra("throw", id);
                            startActivity(intent2);
                        } else {
                            Toast.makeText(SignUpActivity2.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                });
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity2.this, SignIn.class);
                startActivity(intent);
            }
        });
    }
}


