package com.example.attendanceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity2 extends AppCompatActivity {
    EditText et6,et7,et8;
    Button button6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        et6=findViewById(R.id.et6);
        et7=findViewById(R.id.et7);
        et8=findViewById(R.id.et8);
        String unique2=getIntent().getStringExtra("throw");
        button6=findViewById(R.id.button9);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=et6.getText().toString();
                String month=et7.getText().toString();
                String year=et8.getText().toString();
                Intent intent=new Intent(MainActivity2.this,userList.class);
                intent.putExtra("date5",name);
                intent.putExtra("month5",month);
                intent.putExtra("year5",year);
                intent.putExtra("throw",unique2);
                startActivity(intent);
            }
        });
    }
}