package com.example.placement2.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.placement2.Login;
import com.example.placement2.PlacementDetailsActivity;
import com.example.placement2.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class DetailsFragment extends Fragment {

    TextView companyname,jobtitle,address,email,contact,salary;

    DatabaseReference reference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_details, container, false);

        //String placement_id=getArguments().getString("id");
        final String placement_id= PlacementDetailsActivity.getActivityInstance().getData();

        companyname = view.findViewById(R.id.comname);
        jobtitle = view.findViewById(R.id.jobtitle);
        address = view.findViewById(R.id.comaddress);
        email = view.findViewById(R.id.comemail);
        contact = view.findViewById(R.id.comphoneno);
        salary = view.findViewById(R.id.salary);

        reference = FirebaseDatabase.getInstance().getReference().child("companydata").child(placement_id);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                companyname.setText(dataSnapshot.child("name").getValue().toString());
                jobtitle.setText(dataSnapshot.child("job").getValue().toString());
                address.setText(dataSnapshot.child("address").getValue().toString());
                email.setText(dataSnapshot.child("email").getValue().toString());
                contact.setText(dataSnapshot.child("contact").getValue().toString());
                salary.setText(dataSnapshot.child("salary").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return view;


    }
}