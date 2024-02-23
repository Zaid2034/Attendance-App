package com.example.attendanceapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.attendanceapp.Adapters.StudentAdapters;
import com.example.attendanceapp.Model.StudentData;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Button button;
    FirebaseAuth mAuth;
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        button=findViewById(R.id.button);
        mAuth=FirebaseAuth.getInstance();
        String unique=getIntent().getStringExtra("throw");

        ArrayList<StudentData> list = new ArrayList<>();
        for(i=1;i<=70;i++){
            list.add(new StudentData("Roll no "+i,unique));
        }
        StudentAdapters adapters = new StudentAdapters(list, this);
        recyclerView.setAdapter(adapters);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,MainActivity2.class);
                intent.putExtra("throw",unique);
               // Intent intent=new Intent(MainActivity.this,MainActivity2.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
       // finish();
        //System.exit(0);
        //Intent intent3=new Intent(MainActivity.this,MainActivity2.class);
        //startActivity(intent3);
    }
}