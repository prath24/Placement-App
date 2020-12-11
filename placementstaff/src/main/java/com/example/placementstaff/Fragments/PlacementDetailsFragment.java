package com.example.placementstaff.Fragments;

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


public class PlacementDetailsFragment extends Fragment {

    TextView name,job,address,salary,requirements,site,ktlimit,email,contact,branch,cgpa;

    DatabaseReference reference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_placement_details, container, false);

        final String placement_id= ApplicationDetailsActivity.getActivityInstance().getData1();

        name=view.findViewById(R.id.name);
        job=view.findViewById(R.id.job);
        address=view.findViewById(R.id.address);
        salary=view.findViewById(R.id.salary);

        email=view.findViewById(R.id.email);
        contact=view.findViewById(R.id.contact);
        site=view.findViewById(R.id.site);

        requirements = view.findViewById(R.id.requirements);

        branch=view.findViewById(R.id.branch);
        cgpa=view.findViewById(R.id.pointer);
        ktlimit=view.findViewById(R.id.ktlimit);

        reference = FirebaseDatabase.getInstance().getReference().child("companydata");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String sname=dataSnapshot.child(placement_id).child("name").getValue(String.class);
                String sjob=dataSnapshot.child(placement_id).child("job").getValue(String.class);
                String saddress=dataSnapshot.child(placement_id).child("address").getValue(String.class);
                String ssalary=dataSnapshot.child(placement_id).child("salary").getValue(String.class);
                String semail=dataSnapshot.child(placement_id).child("email").getValue(String.class);
                String scontact=dataSnapshot.child(placement_id).child("contact").getValue(String.class);
                String ssite=dataSnapshot.child(placement_id).child("site").getValue(String.class);
                String sbranch=dataSnapshot.child(placement_id).child("branch").getValue(String.class);
                String scgpa=dataSnapshot.child(placement_id).child("mincgpa").getValue(String.class);
                String sktlimit=dataSnapshot.child(placement_id).child("ktlimit").getValue(String.class);

                name.setText(sname);
                job.setText(sjob);
                address.setText(saddress);
                salary.setText(ssalary);
                email.setText(semail);
                contact.setText(scontact);
                site.setText(ssite);
                branch.setText(sbranch);
                cgpa.setText(scgpa);
                ktlimit.setText(sktlimit);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        return view;
    }
}