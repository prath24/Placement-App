package com.example.placementstaff.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.example.placementstaff.ApplicationAdapterClass;
import com.example.placementstaff.ApplicationData;
import com.example.placementstaff.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class PendingFragment extends Fragment {

    ArrayList<ApplicationData> list;
    DatabaseReference ref ;
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pending, container, false);
        recyclerView=view.findViewById(R.id.rv);
        ref= FirebaseDatabase.getInstance().getReference().child("applicationdata");

        return view;
    }
    @Override
    public void onStart() {
        super.onStart();

        if(ref!=null)
        {
            Query checkAccepted = ref.orderByChild("response").equalTo("");
            checkAccepted.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.exists()){
                        list = new ArrayList<>();
                        for(DataSnapshot ds : dataSnapshot.getChildren())
                        {
                            list.add(ds.getValue(ApplicationData.class));
                        }
                        ApplicationAdapterClass adapterClass=new ApplicationAdapterClass(list);
                        recyclerView.setAdapter(adapterClass);
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getContext(),databaseError.getMessage(),Toast.LENGTH_SHORT).show();

                }
            });
        }

    }
}