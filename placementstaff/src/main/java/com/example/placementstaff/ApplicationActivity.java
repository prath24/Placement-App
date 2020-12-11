package com.example.placementstaff;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

public class ApplicationActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private Toolbar mToolbar;

    //View Pager
    private ViewPager mViewPager;

    private TabLayout mTabLayout;

    //Adapter class for Fragments
    private SectionPagerAdapter mSectionPagerAdapter;

    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.activity_application);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        mToolbar = findViewById(R.id.tool);
        setSupportActionBar(mToolbar);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        //setting View Pager with Fragmentpager adapter
        mViewPager=findViewById(R.id.pager);
        mSectionPagerAdapter= new SectionPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mSectionPagerAdapter);

        //setting View pager with tablayout
        mTabLayout=findViewById(R.id.tab_layout);
        mTabLayout.setupWithViewPager(mViewPager);

        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_applicationActivity);

    }
    //setting menu and menu option
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.all_application_menu,menu);
        return true;
    }

    //setting menu options selection
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);

        if (item.getItemId() == R.id.all_app) {
            Intent intent = new Intent(ApplicationActivity.this,ApplicationPage.class);
            startActivity(intent);
        }


        return true;
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.nav_home:
                Intent intent1=new Intent(ApplicationActivity.this,HomePage.class);

                startActivity(intent1);
                break;
            case R.id.nav_applicationActivity:

                break;
            case R.id.nav_application:
                Intent intent4 = new Intent(ApplicationActivity.this, ApplicationPage.class);

                startActivity(intent4);

                break;
            case R.id.nav_placement:
                Intent intent = new Intent(ApplicationActivity.this,PlacementPage.class);

                startActivity(intent);

                break;
            case R.id.nav_students:
                Intent intent2 = new Intent(ApplicationActivity.this,StudentsPage.class);

                startActivity(intent2);
                break;



        }


        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
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
}