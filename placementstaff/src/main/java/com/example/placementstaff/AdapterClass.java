package com.example.placementstaff;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.ArrayList;

public class AdapterClass extends RecyclerView.Adapter<AdapterClass.MyViewHolder> {

    ArrayList<CompanyData> list;
    public AdapterClass(ArrayList<CompanyData> list){
        this.list=list;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_hold,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.companyname.setText(list.get(position).getName());
        holder.job.setText(list.get(position).getJob());
        holder.viewdetailsbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.context,ViewPlacementDetails.class);
                intent.putExtra("id",list.get(position).getPlacementid());
                holder.context.startActivity(intent);
            }
        });

        final int radius = 40;
        final int margin = 40;
        final Transformation transformation = new RoundedCornersTransformation(radius, margin);
        Picasso.get().load(list.get(position).getImageurl()).resize(900,900).transform(transformation).into(holder.logo);



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView companyname,job;
        FloatingActionButton viewdetailsbtn;
        ImageView logo;
        private final Context context;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            companyname=itemView.findViewById(R.id.companyname);
            job=itemView.findViewById(R.id.job);
            viewdetailsbtn=itemView.findViewById(R.id.viewdetails);
            logo=itemView.findViewById(R.id.logo);
            context = itemView.getContext();

        }


    }
}
