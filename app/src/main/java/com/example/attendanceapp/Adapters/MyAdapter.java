package com.example.attendanceapp.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.attendanceapp.Model.StudentData;
import com.example.attendanceapp.Model.User;
import com.example.attendanceapp.R;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    ArrayList<User> list2;


    Context context;

    public MyAdapter(ArrayList<User> list2, Context context) {
        this.list2 = list2;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        User user=list2.get(position);

        holder.textViews.setText(user.getData2());
        holder.data.setText(user.getData());
        Calendar calendar=Calendar.getInstance();
        String date= DateFormat.getDateInstance().format(calendar.getTime());
        holder.button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(holder.button3.getContext());
                builder.setTitle("Delete");
                builder.setMessage("Delete.....?");

                builder.setPositiveButton("Yes",(dialogInterface, i) -> {
                    FirebaseDatabase.getInstance().getReference().child("Students").child(date).child(user.getData2()).removeValue();
                    Toast.makeText(context, "Go back and open attendance report again", Toast.LENGTH_LONG).show();
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.show();
            }
        });
    }
    @Override
    public int getItemCount() {
        return list2.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView data,textViews;
        Button button3,button2;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            data=itemView.findViewById(R.id.textView2);
            textViews=itemView.findViewById(R.id.textViews);
            button3=itemView.findViewById(R.id.button3);
            button2=itemView.findViewById(R.id.button2);
        }
    }
}
