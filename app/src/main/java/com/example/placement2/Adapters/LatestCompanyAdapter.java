package com.example.placement2.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.placement2.LatestCompany;
import com.example.placement2.PlacementDetailsActivity;
import com.example.placement2.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;

public class LatestCompanyAdapter extends RecyclerView.Adapter<LatestCompanyAdapter.MyViewHolder>{

    ArrayList<LatestCompany> list;

    public LatestCompanyAdapter(ArrayList<LatestCompany> list) {
        this.list = list;
        Collections.reverse(this.list);
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_placement_holder,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        holder.job.setText(list.get(position).getJob());
        holder.name.setText(list.get(position).getName());
        Picasso.get().load(list.get(position).getImageurl()).into(holder.logo);
        holder.apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.context, PlacementDetailsActivity.class);
                intent.putExtra("id",list.get(position).getPlacementid());
                holder.context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView job,name,expdate;
        ImageView logo;
        FloatingActionButton apply;
        private final Context context;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            context = itemView.getContext();
            job=itemView.findViewById(R.id.job);
            name=itemView.findViewById(R.id.name);
            expdate=itemView.findViewById(R.id.expdate);
            logo=itemView.findViewById(R.id.logo);
            apply=itemView.findViewById(R.id.applybtn);
        }
    }
}
