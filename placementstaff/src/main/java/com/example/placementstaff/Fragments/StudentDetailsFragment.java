package com.example.placementstaff.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.placementstaff.ApplicationDetailsActivity;
import com.example.placementstaff.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class StudentDetailsFragment extends Fragment {

    TextView name,rollno,email,phoneno,branch,div,sboard,spercentage,syear,hboard,hpercentage,hyear,sem1,sem2,sem3,sem4,sem5,sem6,sem7,cgpa;
    DatabaseReference reference;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_student_details, container, false);

        final String username= ApplicationDetailsActivity.getActivityInstance().getData();


        name = view.findViewById(R.id.name);
        rollno = view.findViewById(R.id.username);
        email = view.findViewById(R.id.email);
        phoneno = view.findViewById(R.id.phoneno);
        branch = view.findViewById(R.id.branch);
        div = view.findViewById(R.id.div);
        sboard = view.findViewById(R.id.sboard);
        spercentage = view.findViewById(R.id.spercentage);
        syear = view.findViewById(R.id.syear);
        hboard = view.findViewById(R.id.hboard);
        hpercentage = view.findViewById(R.id.hpercentage);
        hyear = view.findViewById(R.id.hyear);
        sem1 = view.findViewById(R.id.sem1);
        sem2 = view.findViewById(R.id.sem2);
        sem3 = view.findViewById(R.id.sem3);
        sem4 = view.findViewById(R.id.sem4);
        sem5 = view.findViewById(R.id.sem5);
        sem6 = view.findViewById(R.id.sem6);
        sem7 = view.findViewById(R.id.sem7);
        cgpa = view.findViewById(R.id.cgpa);

        reference = FirebaseDatabase.getInstance().getReference().child("users");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String sname=dataSnapshot.child(username).child("name").getValue(String.class);
                String semail=dataSnapshot.child(username).child("email").getValue(String.class);
                String sphoneno=dataSnapshot.child(username).child("phoneNo").getValue(String.class);
                String sbranch=dataSnapshot.child(username).child("branch").getValue(String.class);
                String sdiv=dataSnapshot.child(username).child("div").getValue(String.class);
                String ssboard=dataSnapshot.child(username).child("tenthboard").getValue(String.class);
                String shboard=dataSnapshot.child(username).child("twelvethboard").getValue(String.class);
                String sspercentage=dataSnapshot.child(username).child("tenthpercentage").getValue(String.class);
                String shpercentage=dataSnapshot.child(username).child("twelvethpercentage").getValue(String.class);
                String ssyear=dataSnapshot.child(username).child("tenthyear").getValue(String.class);
                String shyear=dataSnapshot.child(username).child("twelvethyear").getValue(String.class);
                String ssem1=dataSnapshot.child(username).child("sem1").getValue(String.class);
                String ssem2=dataSnapshot.child(username).child("sem2").getValue(String.class);
                String ssem3=dataSnapshot.child(username).child("sem3").getValue(String.class);
                String ssem4=dataSnapshot.child(username).child("sem4").getValue(String.class);
                String ssem5=dataSnapshot.child(username).child("sem5").getValue(String.class);
                String ssem6=dataSnapshot.child(username).child("sem6").getValue(String.class);
                String ssem7=dataSnapshot.child(username).child("sem7").getValue(String.class);
                String ssem8=dataSnapshot.child(username).child("sem8").getValue(String.class);

                name.setText(sname);
                email.setText(semail);
                phoneno.setText(sphoneno);
                branch.setText(sbranch);
                div.setText(sdiv);
                sboard.setText(ssboard);
                hboard.setText(shboard);
                spercentage.setText(sspercentage);
                hpercentage.setText(shpercentage);
                syear.setText(ssyear);
                hyear.setText(shyear);
                sem1.setText(ssem1);
                sem2.setText(ssem2);
                sem3.setText(ssem3);
                sem4.setText(ssem4);
                sem5.setText(ssem5);
                sem6.setText(ssem6);
                sem7.setText(ssem7);

                //cgpa calculation
                float scgpa= (Float.parseFloat(ssem1)+Float.parseFloat(ssem2)+Float.parseFloat(ssem3)+Float.parseFloat(ssem4)+Float.parseFloat(ssem5)+Float.parseFloat(ssem6)+Float.parseFloat(ssem7)+Float.parseFloat(ssem8))/8;

                cgpa.setText(String.valueOf(scgpa));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return view;
    }
}