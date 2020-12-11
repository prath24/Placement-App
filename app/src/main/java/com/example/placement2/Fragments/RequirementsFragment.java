package com.example.placement2.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.placement2.PlacementDetailsActivity;
import com.example.placement2.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class RequirementsFragment extends Fragment {

    TextView requirements,branch,score,ktlimit;

    DatabaseReference reference;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_requirements, container, false);

        final String placement_id= PlacementDetailsActivity.getActivityInstance().getData();

        requirements=view.findViewById(R.id.requirements);
        branch=view.findViewById(R.id.branch);
        score=view.findViewById(R.id.pointer);
        ktlimit=view.findViewById(R.id.ktlimit);

        reference = FirebaseDatabase.getInstance().getReference().child("companydata").child(placement_id);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               requirements.setText(dataSnapshot.child("requirements").getValue().toString());
               branch.setText(dataSnapshot.child("branch").getValue().toString());
               score.setText(dataSnapshot.child("mincgpa").getValue().toString());
               ktlimit.setText(dataSnapshot.child("ktlimit").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return view;
    }
}