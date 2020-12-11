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
import android.view.WindowManager;

import com.example.placementstaff.Adapters.StudentAdapter;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class StudentsPage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    DatabaseReference reference ;

    RecyclerView recyclerView;

    ArrayList<Student> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.activity_students_page);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        recyclerView = findViewById(R.id.rv);

        toolbar = findViewById(R.id.tool);
        setSupportActionBar(toolbar);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_students);

        reference= FirebaseDatabase.getInstance().getReference().child("users");

    }

    @Override
    protected void onStart() {
        super.onStart();
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                   list = new ArrayList<>();
                   for (DataSnapshot ds : dataSnapshot.getChildren()){
                       list.add(ds.getValue(Student.class));
                   }
                    StudentAdapter studentAdapter = new StudentAdapter(list);
                   recyclerView.setAdapter(studentAdapter);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            Intent intent = new Intent(getApplicationContext(),HomePage.class);
            startActivity(intent);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.nav_home:
                Intent intent1=new Intent(StudentsPage.this,HomePage.class);

                startActivity(intent1);
                break;
            case R.id.nav_applicationActivity:
                Intent intent=new Intent(StudentsPage.this,ApplicationActivity.class);

                startActivity(intent);
                break;
            case R.id.nav_placement:
                Intent intent2=new Intent(StudentsPage.this,ApplicationPage.class);

                startActivity(intent2);

                break;
            case R.id.nav_students:

                break;



        }


        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}