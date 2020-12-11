package com.example.placement2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class UserProfile extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    TextView fullNameLabel;
    TextInputLayout fullname,email,phoneNo,branch,username,div;
    Button update,academicbtn;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_user_profile);

        fullname=findViewById(R.id.name);
        email=findViewById(R.id.email);
        phoneNo=findViewById(R.id.phoneNo);
        branch=findViewById(R.id.branch);
        username=findViewById(R.id.rollno);
        div=findViewById(R.id.div);
        academicbtn=findViewById(R.id.academicbtn);
        fullNameLabel=findViewById(R.id.full_name);



        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        toolbar = findViewById(R.id.tool);
        setSupportActionBar(toolbar);
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_profile);

        academicbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(UserProfile.this,AcademicProfile.class);

                startActivity(intent);

            }
        });
        showAllUserData();

    }

    private void showAllUserData() {


       final String user_username=Login.getActivityInstance().getData();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        Query checkUser = reference.orderByChild("username").equalTo(user_username);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                String user_name = dataSnapshot.child(user_username).child("name").getValue(String.class);
                String user_phoneNo = dataSnapshot.child(user_username).child("phoneNo").getValue(String.class);
                String user_branch = dataSnapshot.child(user_username).child("branch").getValue(String.class);
                String user_email = dataSnapshot.child(user_username).child("email").getValue(String.class);
                String user_div = dataSnapshot.child(user_username).child("div").getValue(String.class);
                String uusername = dataSnapshot.child(user_username).child("username").getValue(String.class);


                fullname.getEditText().setText(user_name);
                phoneNo.getEditText().setText(user_phoneNo);
                email.getEditText().setText(user_email);
                branch.getEditText().setText(user_branch);
                username.getEditText().setText(uusername);
                div.getEditText().setText(user_div);
                fullNameLabel.setText(user_name);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        /*fullname.getEditText().setText(user_name);
        phoneNo.getEditText().setText(user_phoneNo);
        email.getEditText().setText(user_email);
        branch.getEditText().setText(user_branch);
        div.getEditText().setText(user_div);
        fullNameLabel.setText(user_name);*/






    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_home:
                Intent intent1 = new Intent(UserProfile.this, HomePage.class);

                startActivity(intent1);
                break;
            case R.id.nav_application:
                Intent intent = new Intent(UserProfile.this, ApplicationPage.class);

                startActivity(intent);
                break;
            case R.id.nav_placement:
                Intent intent2 = new Intent(UserProfile.this,PlacementPage.class);

                startActivity(intent2);
                break;
            case R.id.nav_profile:

                break;
            case R.id.nav_logout:
                Intent intent3 = new Intent(UserProfile.this,Login.class);
                startActivity(intent3);
        }
        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            Intent intent=new Intent(getApplicationContext(),HomePage.class);
            startActivity(intent);
        }
    }
}