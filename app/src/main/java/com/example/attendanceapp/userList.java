package com.example.attendanceapp;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.attendanceapp.Adapters.MyAdapter;
import com.example.attendanceapp.Model.InputDate;
import com.example.attendanceapp.Model.StudentData;
import com.example.attendanceapp.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class userList extends AppCompatActivity {
    RecyclerView recyclerView2;
    DatabaseReference databaseReference;
    MyAdapter myAdapter;
    ArrayList<User> list2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        recyclerView2=findViewById(R.id.recyclerView2);

        Calendar calendar=Calendar.getInstance();
        String date= DateFormat.getDateInstance().format(calendar.getTime());
       final String date5=getIntent().getStringExtra("date5");
       final String month5=getIntent().getStringExtra("month5");
        final String year5=getIntent().getStringExtra("year5");
        String unique2=getIntent().getStringExtra("throw");



        databaseReference = FirebaseDatabase.getInstance().getReference().child("Students").child(unique2).child(month5).child(date5).child(year5);

                recyclerView2.setHasFixedSize(true);
//        String zaid=getIntent().getStringExtra("data");
                recyclerView2.setLayoutManager(new LinearLayoutManager(this));
                list2 = new ArrayList<>();


                myAdapter = new MyAdapter(list2, this);
                recyclerView2.setAdapter(myAdapter);
//        list2.add(new User(zaid));

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot datasnapshot : snapshot.getChildren()) {
                            User user = datasnapshot.getValue(User.class);
                            list2.add(user);
                        }
                        myAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


    }
  /*  @Override
    public void onBackPressed() {
        //super.onBackPressed();
//        finish();
//        System.exit(0);
        Intent intent3=new Intent(userList.this,MainActivity.class);
        startActivity(intent3);
    }*/
}