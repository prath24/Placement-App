package com.example.placement2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class AcademicProfile extends AppCompatActivity {
    TextInputLayout regSboard,regTenthpercentage,regTenthyear,regHboard,regTwelvethpercentage,regTwelvethyear,regSem1,regSem2,regSem3,regSem4,regSem5,regSem6,regSem7,regSem8,regcgpa;
    Button accountbtn;
    String user_cgpa;

    static AcademicProfile INSTANCE;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academic_profile);

        INSTANCE=this;

        regSboard=findViewById(R.id.sboard);
        regHboard=findViewById(R.id.hboard);
        regTenthpercentage=findViewById(R.id.tenthpercentage);
        regTwelvethpercentage=findViewById(R.id.twelvethpercentage);
        regTenthyear=findViewById(R.id.tenthyear);
        regTwelvethyear=findViewById(R.id.twelvethyear);
        regSem1=findViewById(R.id.sem1);
        regSem2=findViewById(R.id.sem2);
        regSem3=findViewById(R.id.sem3);
        regSem4=findViewById(R.id.sem4);
        regSem5=findViewById(R.id.sem5);
        regSem6=findViewById(R.id.sem6);
        regSem7=findViewById(R.id.sem7);
        regSem8=findViewById(R.id.sem8);
        regcgpa=findViewById(R.id.cgpa);
        accountbtn=findViewById(R.id.accpuntbtn);
        accountbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AcademicProfile.this,UserProfile.class);
                startActivity(intent);
            }
        });
    showAllUserData();
    }

    public static AcademicProfile getActivityInstance()
    {
        return INSTANCE;
    }
    public String getData()
    {
        return this.user_cgpa;
    }

    private void showAllUserData(){

        final String user_username=Login.getActivityInstance().getData();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        Query checkUser = reference.orderByChild("username").equalTo(user_username);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String user_sboard= dataSnapshot.child(user_username).child("tenthboard").getValue(String.class);
                String user_hboard= dataSnapshot.child(user_username).child("twelvethboard").getValue(String.class);
                String user_tenthpercentage= dataSnapshot.child(user_username).child("tenthpercentage").getValue(String.class);
                String user_twelvethpercentage= dataSnapshot.child(user_username).child("twelvethpercentage").getValue(String.class);
                String user_twelvethyear= dataSnapshot.child(user_username).child("twelvethyear").getValue(String.class);
                String user_tenthyear= dataSnapshot.child(user_username).child("tenthyear").getValue(String.class);
                String user_sem1= dataSnapshot.child(user_username).child("sem1").getValue(String.class);
                String user_sem2= dataSnapshot.child(user_username).child("sem2").getValue(String.class);
                String user_sem3= dataSnapshot.child(user_username).child("sem3").getValue(String.class);
                String user_sem4= dataSnapshot.child(user_username).child("sem4").getValue(String.class);
                String user_sem5= dataSnapshot.child(user_username).child("sem5").getValue(String.class);
                String user_sem6= dataSnapshot.child(user_username).child("sem6").getValue(String.class);
                String user_sem7= dataSnapshot.child(user_username).child("sem7").getValue(String.class);
                String user_sem8= dataSnapshot.child(user_username).child("sem8").getValue(String.class);

                regSboard.getEditText().setText(user_sboard);
                regHboard.getEditText().setText(user_hboard);
                regTenthpercentage.getEditText().setText(user_tenthpercentage);
                regTwelvethpercentage.getEditText().setText(user_twelvethpercentage);
                regTenthyear.getEditText().setText(user_tenthyear);
                regTwelvethyear.getEditText().setText(user_twelvethyear);
                regSem1.getEditText().setText(user_sem1);
                regSem2.getEditText().setText(user_sem2);
                regSem3.getEditText().setText(user_sem3);
                regSem4.getEditText().setText(user_sem4);
                regSem5.getEditText().setText(user_sem5);
                regSem6.getEditText().setText(user_sem6);
                regSem7.getEditText().setText(user_sem7);
                regSem8.getEditText().setText(user_sem8);
                double sem1= Double.parseDouble(user_sem1);
                double sem2= Double.parseDouble(user_sem2);
                double sem3= Double.parseDouble(user_sem3);
                double sem4= Double.parseDouble(user_sem4);
                double sem5= Double.parseDouble(user_sem5);
                double sem6= Double.parseDouble(user_sem6);
                double sem7= Double.parseDouble(user_sem7);
                double sem8= Double.parseDouble(user_sem8);
                double cgpa = (sem1+sem2+sem3+sem4+sem5+sem6+sem7+sem8)/8.0;
                user_cgpa= String.valueOf(cgpa);
                regcgpa.getEditText().setText("CGPA = "+user_cgpa);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}