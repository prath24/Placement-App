package com.example.placement2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ApplicationAdapterClass extends RecyclerView.Adapter<ApplicationAdapterClass.MyViewHolder>{

    ArrayList<ApplicationData> list;

    public ApplicationAdapterClass(ArrayList<ApplicationData> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_holder_application,parent,false);
        return new ApplicationAdapterClass.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder,final int position) {
        holder.company.setText(list.get(position).getCompanyname());
        holder.student.setText(list.get(position).getUsername());
        holder.job.setText(list.get(position).getJob());
        if (list.get(position).getResponse().equals("Accepted")){
            holder.response.setText(list.get(position).getResponse());
            holder.response.setTextColor(Color.parseColor("#008000"));
        }
        else if (list.get(position).getResponse().equals("Rejected")){
            holder.response.setText(list.get(position).getResponse());
            holder.response.setTextColor(Color.parseColor("#ff0000"));

        }
        else {
            holder.response.setText("");
        }
        holder.viewapplicationdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.context,ViewApplicationDetails.class);
                intent.putExtra("id",list.get(position).getPlacementid());
                intent.putExtra("username",list.get(position).getUsername());
                holder.context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView student,company,job;
        TextView response;
        FloatingActionButton viewapplicationdetails;
        private final Context context;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            student=itemView.findViewById(R.id.student);
            company=itemView.findViewById(R.id.company);
            job=itemView.findViewById(R.id.job);
            response=itemView.findViewById(R.id.response);
            viewapplicationdetails=itemView.findViewById(R.id.viewapplicationdetails);
            context = itemView.getContext();
        }
    }

}
