package com.example.placementstaff;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.placementstaff.Adapters.LatestCompanyAdapter;
import com.example.placementstaff.Adapters.SliderAdapter;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class HomePage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    ArrayList<LatestCompany> list;

    DatabaseReference reference ;
    DatabaseReference reference1;

    RecyclerView recyclerView ;

    SliderView sliderView;

    TextView viewAll;

    ImageView edit;

    private List<SliderItem> mSliderItems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.activity_home_page);

        viewAll=findViewById(R.id.seemore);
        viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getApplicationContext(),PlacementPage.class);
                startActivity(intent);
            }
        });

        edit=findViewById(R.id.edit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),PosterChangeActivity.class);
                startActivity(intent);
            }
        });

        sliderView=findViewById(R.id.imageSlider);

        recyclerView=findViewById(R.id.rv1);

        reference= FirebaseDatabase.getInstance().getReference().child("companydata");
        reference1= FirebaseDatabase.getInstance().getReference().child("sliderimages");
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        toolbar = findViewById(R.id.tool);
        setSupportActionBar(toolbar);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);

        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);

        reference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    mSliderItems=new ArrayList<>();
                    for (DataSnapshot ds : dataSnapshot.getChildren()){
                        mSliderItems.add(ds.getValue(SliderItem.class));
                    }
                    SliderAdapter adapter = new SliderAdapter(getApplicationContext(),mSliderItems);

                    sliderView.setSliderAdapter(adapter);

                    sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using IndicatorAnimationType. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
                    sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
                    sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
                    sliderView.setIndicatorSelectedColor(Color.WHITE);
                    sliderView.setIndicatorUnselectedColor(Color.GRAY);
                    sliderView.setScrollTimeInSec(4); //set scroll delay in seconds :
                    sliderView.startAutoCycle();


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    list=new ArrayList<>();
                    for (DataSnapshot ds : dataSnapshot.getChildren()){
                        list.add(ds.getValue(LatestCompany.class));
                    }
                    LatestCompanyAdapter latestCompanyAdapter= new LatestCompanyAdapter(list);
                    recyclerView.setAdapter(latestCompanyAdapter);
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
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.nav_home:
                break;
            case R.id.nav_applicationActivity:
                Intent intent4=new Intent(HomePage.this,ApplicationActivity.class);

                startActivity(intent4);
                break;
            case R.id.nav_placement:
                Intent intent1=new Intent(HomePage.this,PlacementPage.class);

                startActivity(intent1);
                break;
            case R.id.nav_students:
                Intent intent2 = new Intent(HomePage.this,StudentsPage.class);

                startActivity(intent2);
                break;



        }

        drawerLayout.closeDrawer(GravityCompat.START);


        return true;
    }
}
