package com.example.placement2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ViewApplicationDetails extends AppCompatActivity {

    String username,placementid;
    RelativeLayout responseback;
    TextView response;
    Button deleteapplicationbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_view_application_details);
        response = findViewById(R.id.response);
        responseback = findViewById(R.id.responseback);
        username=getIntent().getStringExtra("username");
        placementid=getIntent().getStringExtra("id");
        deleteapplicationbtn=findViewById(R.id.deleteapplicationbtn);

        //showData();



    }

    private void showData() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("applicationdata").child(username+placementid);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child("response").getValue().toString().equals("Accepted")){
                    response.setTextColor(Color.parseColor("#008000"));
                    //responseback.setBackgroundResource(R.drawable.applicationresponsebox2);
                    response.setText(dataSnapshot.child("response").getValue().toString());


                }
                else if (dataSnapshot.child("response").getValue().toString().equals("Rejected")){
                    response.setTextColor(Color.parseColor("#ff0000"));
                    //responseback.setBackgroundResource(R.drawable.applicationresponsebox3);
                    response.setText(dataSnapshot.child("response").getValue().toString());
                }
                else {
                    response.setTextColor(Color.parseColor("#55acf7"));
                    response.setText("Applied");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
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
                        Intent intent = new Intent(ViewApplicationDetails.this,ApplicationPage.class);
                        Toast.makeText(ViewApplicationDetails.this,"Application Deleted Sucessfully",Toast.LENGTH_SHORT);
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
}