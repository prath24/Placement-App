package com.example.placementstaff;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ApplicationPage extends AppCompatActivity  {

    Toolbar toolbar;

    DatabaseReference ref ;
    ArrayList<ApplicationData> list;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.activity_application_page);



        toolbar = findViewById(R.id.tool);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        ref = FirebaseDatabase.getInstance().getReference().child("applicationdata");
        recyclerView = findViewById(R.id.rv);

    }


    @Override
    protected void onStart() {
        super.onStart();

        if(ref!=null)
        {
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.exists()){
                        list = new ArrayList<>();
                        for(DataSnapshot ds : dataSnapshot.getChildren())
                        {
                            list.add(ds.getValue(ApplicationData.class));
                        }
                        ApplicationAdapterClass adapterClass=new ApplicationAdapterClass(list);
                        recyclerView.setAdapter(adapterClass);
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(ApplicationPage.this,databaseError.getMessage(),Toast.LENGTH_SHORT).show();

                }
            });
        }

    }




}