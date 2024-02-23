package com.example.attendanceapp.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.attendanceapp.Model.StudentData;
import com.example.attendanceapp.Model.model;
import com.example.attendanceapp.R;
import com.example.attendanceapp.userList;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class StudentAdapters extends RecyclerView.Adapter<StudentAdapters.ViewHolder>{
    ArrayList<StudentData> list;
    Context context;
    DatabaseReference databaseReference;
    public StudentAdapters(ArrayList<StudentData> list, Context context) {
        this.list = list;
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.activity_sample,parent,false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    StudentData studentData= list.get(position);
    //Calendar calendar=Calendar.getInstance();
        Date cuurentTime=Calendar.getInstance().getTime();

   String date= DateFormat.getDateInstance(DateFormat.DATE_FIELD).format(cuurentTime);
       // String date= DateFormat.getDateInstance().format(calendar.get(Calendar.DATE));
       // String date= calendar.get(Calendar.DATE) + "/" + calendar.get(Calendar.MONTH)+"/"+calendar.get(Calendar.YEAR);
       // int date=calendar.get(Calendar.MONTH);
    String id=studentData.getId();
    databaseReference=FirebaseDatabase.getInstance().getReference().child("Students").child(id).child(date);
    holder.textView.setText(studentData.getData());
/*
    holder.itemView.findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
//            Intent intent=new Intent(context, userList.class);
//            intent.putExtra("data",StudentData.class);

            String data2="Present";
            String roll=studentData.getData();

          //  model studentModel=new model(data2,roll);
            databaseReference.child(roll).setValue(studentData);

        }
    });*/

        holder.itemView.findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//            Intent intent=new Intent(context, userList.class);
//            intent.putExtra("data",StudentData.class);
                String data="Absent";
                String roll=studentData.getData();
//            StudentModels studentModels=new StudentModels(data);
                model studentModel=new model(data,roll);
                databaseReference.child(roll).setValue(studentModel);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        Button button2,button4;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView=itemView.findViewById(R.id.textView);
            button2=itemView.findViewById(R.id.button2);
            button4=itemView.findViewById(R.id.button4);
        }
    }
}