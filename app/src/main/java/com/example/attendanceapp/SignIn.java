package com.example.attendanceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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

public class SignIn extends AppCompatActivity {
    EditText et3,et4;
    Button button9;
    TextView textView9;
    FirebaseAuth mAuth;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        et3=findViewById(R.id.et3);
        et4=findViewById(R.id.et4);
        button9=findViewById(R.id.button9);
        textView9=findViewById(R.id.textView9);
        mAuth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        button9.setOnClickListener(view -> {
               mAuth.signInWithEmailAndPassword(et3.getText().toString(),et4.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Intent intent=new Intent(SignIn.this,MainActivity.class);
                        String id=task.getResult().getUser().getUid();
                        intent.putExtra("throw",id);
                        startActivity(intent);
                    }else{
                        Toast.makeText(SignIn.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });

        });
        textView9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent5=new Intent(SignIn.this,SignUpActivity2.class);
                startActivity(intent5);
            }
        });
    }
}
