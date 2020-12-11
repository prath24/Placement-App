package com.example.placementstaff;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.placementstaff.Adapters.ApplicationPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class ApplicationDetailsActivity extends AppCompatActivity {

    String username,placementid;

    private Toolbar mToolbar;

    //View Pager
    private ViewPager mViewPager;

    private TabLayout mTabLayout;

    //Adapter class for Fragments
    private ApplicationPagerAdapter applicationPagerAdapter;

    //databasereff
    DatabaseReference reference;

    TextView name,rollno;
    static ApplicationDetailsActivity INSTANCE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.activity_application_details);
        username=getIntent().getStringExtra("username");
        placementid=getIntent().getStringExtra("id");

        mToolbar = findViewById(R.id.tool);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        name=findViewById(R.id.name);
        rollno=findViewById(R.id.username);
        rollno.setText(username);
        INSTANCE=this;

        reference= FirebaseDatabase.getInstance().getReference().child("users").child(username);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String sname = dataSnapshot.child("name").getValue().toString();
                name.setText(sname);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //setting View Pager with Fragmentpager adapter
        mViewPager=findViewById(R.id.pager);
        applicationPagerAdapter= new ApplicationPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(applicationPagerAdapter);

        //setting View pager with tablayout
        mTabLayout=findViewById(R.id.tab_layout);
        mTabLayout.setupWithViewPager(mViewPager);



    }
    public static ApplicationDetailsActivity getActivityInstance()
    {
        return INSTANCE;
    }
    public String getData()
    {
        return this.username;
    }
    public String getData1()
    {
        return this.placementid;
    }


    public void deleteapplication(View view){

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Delete Application!")
                .setMessage("Do you want to delete this application ?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DatabaseReference reff1= FirebaseDatabase.getInstance().getReference("applicationdata").child(username+placementid);


                        reff1.removeValue();

                        Intent intent = new Intent(getApplicationContext(),ApplicationActivity.class);
                        Toast.makeText(getApplicationContext(),"Application Deleted Sucessfully",Toast.LENGTH_SHORT);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        alertDialogBuilder.show();


    }
    public void acceptapplication(View view){

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Accept Application!")
                .setMessage("Do you want to accept this application ?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DatabaseReference reff1= FirebaseDatabase.getInstance().getReference("applicationdata").child(username+placementid);

                        StoreApplicationData resp = new StoreApplicationData();
                        resp.setResponse("Accepted");
                        HashMap<String,Object> hashMap = new HashMap<>();
                        hashMap.put("response",resp.getResponse());
                        reff1.updateChildren(hashMap);


                        Intent intent = new Intent(getApplicationContext(), ApplicationActivity.class);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(),"Application Accepted",Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }

                });
        alertDialogBuilder.show();

    }
    public void rejectapplication(View view){


        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Reject Application!")
                .setMessage("Do you want to reject this application ?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DatabaseReference reff1= FirebaseDatabase.getInstance().getReference("applicationdata").child(username+placementid);

                        StoreApplicationData resp = new StoreApplicationData();
                        resp.setResponse("Rejected");
                        HashMap<String,Object> hashMap = new HashMap<>();
                        hashMap.put("response",resp.getResponse());
                        reff1.updateChildren(hashMap);

                        Intent intent = new Intent(getApplicationContext(), ApplicationActivity.class);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(),"Application Rejected",Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

        alertDialogBuilder.show();
    }
}