package com.example.placement2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.placement2.Fragments.DetailsFragment;
import com.example.placement2.Fragments.RequirementsFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.zip.DataFormatException;

public class PlacementDetailsActivity extends AppCompatActivity {

    TextView companyname,companysite;
    ImageView Companylogo;

    Toolbar mToolbar;

    TabLayout tabLayout;

    private SectionPagerAdapter mSectionPagerAdapter;

    ViewPager viewPager;

    DatabaseReference reference;

    String placement_id,cname,cjob,min_cgpa;

    static PlacementDetailsActivity INSTANCE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.activity_placement_details);

        placement_id = getIntent().getStringExtra("id");

        mToolbar=findViewById(R.id.tool);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        reference = FirebaseDatabase.getInstance().getReference().child("companydata").child(placement_id);

        tabLayout=findViewById(R.id.tab_layout);
        mSectionPagerAdapter= new SectionPagerAdapter(getSupportFragmentManager());

        viewPager=findViewById(R.id.pager);

        viewPager.setAdapter(mSectionPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        companyname = findViewById(R.id.companyname);
        companysite = findViewById(R.id.companysite);
        Companylogo = findViewById(R.id.logo);
        INSTANCE=this;

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                cname=dataSnapshot.child("name").getValue().toString();
                cjob=dataSnapshot.child("job").getValue().toString();
                companyname.setText(dataSnapshot.child("name").getValue().toString());
                companysite.setText(dataSnapshot.child("site").getValue().toString());
                min_cgpa=dataSnapshot.child("mincgpa").getValue().toString();
                Picasso.get().load(dataSnapshot.child("imageurl").getValue().toString()).into(Companylogo);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        companysite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("companydata").child(placement_id);
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String website = dataSnapshot.child("site").getValue().toString();
                        Intent browseSite = new Intent(Intent.ACTION_VIEW, Uri.parse(website));
                        startActivity(browseSite);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });

    }
    public static PlacementDetailsActivity getActivityInstance()
    {
        return INSTANCE;
    }
    public String getData()
    {
        return this.placement_id;
    }


    public void applyapplication(View view) {

        String user_name=Login.getActivityInstance().getData();
        //Double scgpa=Double.parseDouble(student_cgpa);
        final Double mcgpa=Double.parseDouble(min_cgpa);
        DatabaseReference student = FirebaseDatabase.getInstance().getReference().child("users").child(user_name);

        student.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String user_sem1= dataSnapshot.child("sem1").getValue().toString();
                String user_sem2= dataSnapshot.child("sem2").getValue().toString();
                String user_sem3= dataSnapshot.child("sem3").getValue().toString();
                String user_sem4= dataSnapshot.child("sem4").getValue().toString();
                String user_sem5= dataSnapshot.child("sem5").getValue().toString();
                String user_sem6= dataSnapshot.child("sem6").getValue().toString();
                String user_sem7= dataSnapshot.child("sem7").getValue().toString();
                String user_sem8= dataSnapshot.child("sem8").getValue().toString();

                double sem1= Double.parseDouble(user_sem1);
                double sem2= Double.parseDouble(user_sem2);
                double sem3= Double.parseDouble(user_sem3);
                double sem4= Double.parseDouble(user_sem4);
                double sem5= Double.parseDouble(user_sem5);
                double sem6= Double.parseDouble(user_sem6);
                double sem7= Double.parseDouble(user_sem7);
                double sem8= Double.parseDouble(user_sem8);
                final double scgpa = (sem1+sem2+sem3+sem4+sem5+sem6+sem7+sem8)/8.0;

                if (scgpa>=mcgpa){
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(PlacementDetailsActivity.this);
                    alertDialogBuilder.setTitle("Apply!")
                            .setMessage("Are you sure , you want to apply  ?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    final String user_username = Login.getActivityInstance().getData();

                                    DatabaseReference reff = FirebaseDatabase.getInstance().getReference("applicationdata");
                                    String response = "";
                                    StoreApplicationData storeApplicationData = new StoreApplicationData(cname, cjob, user_username, placement_id, response);
                                    reff.child(user_username + placement_id).setValue(storeApplicationData);

                                    Intent intent = new Intent(getApplicationContext(), ApplicationPage.class);
                                    Toast.makeText(getApplicationContext(), "Applied Sucessfully", Toast.LENGTH_SHORT).show();
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
                else {
                    Toast.makeText(getApplicationContext(), "CANNOT APPLY! Please check the criteria", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });





    }
}
