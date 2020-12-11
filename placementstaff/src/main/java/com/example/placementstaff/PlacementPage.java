package com.example.placementstaff;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PlacementPage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    FloatingActionButton addofferbtn;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    DatabaseReference ref ;
    ArrayList<CompanyData> list;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.activity_placement_page);


        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        toolbar = findViewById(R.id.tool);
        setSupportActionBar(toolbar);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_placement);

        ref = FirebaseDatabase.getInstance().getReference().child("companydata");
        recyclerView = findViewById(R.id.rv);

        addofferbtn=findViewById(R.id.addofferbtn);
        addofferbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intn = new Intent(PlacementPage.this,AddPlacementOffer.class);
                startActivity(intn);
            }
        });
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
                            list.add(ds.getValue(CompanyData.class));
                        }
                        AdapterClass adapterClass=new AdapterClass(list);
                        recyclerView.setAdapter(adapterClass);
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(PlacementPage.this,databaseError.getMessage(),Toast.LENGTH_SHORT).show();

                }
            });
        }


    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            Intent intent=new Intent(PlacementPage.this,HomePage.class);
            startActivity(intent);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.nav_home:
                Intent intent1=new Intent(PlacementPage.this,HomePage.class);

                startActivity(intent1);
                break;
            case R.id.nav_applicationActivity:
                Intent intent=new Intent(PlacementPage.this,ApplicationActivity.class);

                startActivity(intent);
                break;
            case R.id.nav_placement:

                break;
            case R.id.nav_students:
                Intent intent2 = new Intent(PlacementPage.this,StudentsPage.class);

                startActivity(intent2);
                break;



        }

        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }
}