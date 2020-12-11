package com.example.placement2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ApplicationPage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    DatabaseReference ref ;
    ArrayList<ApplicationData> list;
    RecyclerView recyclerView;
    String user_username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.activity_application_page);
        user_username=Login.getActivityInstance().getData();

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        toolbar = findViewById(R.id.tool);
        setSupportActionBar(toolbar);
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_application);

        ref = FirebaseDatabase.getInstance().getReference().child("applicationdata");
        recyclerView = findViewById(R.id.rv);

    }

    @Override
    protected void onStart() {
        super.onStart();
        Query onlyuser = ref.orderByChild("username").equalTo(user_username);
        onlyuser.addValueEventListener(new ValueEventListener() {
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



    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            Intent intent=new Intent(getApplicationContext(),HomePage.class);
            startActivity(intent);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {


        switch (menuItem.getItemId()){
            case R.id.nav_home:
                Intent intent=new Intent(ApplicationPage.this,HomePage.class);

                startActivity(intent);
                break;
            case R.id.nav_application:

                break;
            case R.id.nav_placement:
                Intent intent1=new Intent(ApplicationPage.this,PlacementPage.class);

                startActivity(intent1);
                break;
            case R.id.nav_profile:
                Intent intent2 = new Intent(ApplicationPage.this,UserProfile.class);

                startActivity(intent2);
                break;
            case R.id.nav_logout:
                Intent intent3 = new Intent(ApplicationPage.this,Login.class);
                startActivity(intent3);


        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}