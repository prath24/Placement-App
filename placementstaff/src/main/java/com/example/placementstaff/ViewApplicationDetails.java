package com.example.placementstaff;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.placementstaff.Fragments.AcceptedFragment;
import com.example.placementstaff.Fragments.RejectedFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class ViewApplicationDetails extends AppCompatActivity {

    String username,placementid;
    TextView name,rollno,email,phoneno,branch,div,sboard,spercentage,syear,hboard,hpercentage,hyear,sem1,sem2,sem3,sem4,sem5,sem6,sem7,id,companyname,jobtitle,address,requirements,salary;
    Button deleteapplicationbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_view_application_details);
        username=getIntent().getStringExtra("username");
        placementid=getIntent().getStringExtra("id");
        deleteapplicationbtn=findViewById(R.id.deleteapplicationbtn);

        name = findViewById(R.id.name);
        rollno = findViewById(R.id.rollno);
        email = findViewById(R.id.email);
        phoneno = findViewById(R.id.phoneno);
        branch = findViewById(R.id.branch);
        div = findViewById(R.id.div);
        sboard = findViewById(R.id.sboard);
        spercentage = findViewById(R.id.spercentage);
        syear = findViewById(R.id.syear);
        hboard = findViewById(R.id.hboard);
        hpercentage = findViewById(R.id.hpercentage);
        hyear = findViewById(R.id.hyear);
        sem1 = findViewById(R.id.sem1);
        sem2 = findViewById(R.id.sem2);
        sem3 = findViewById(R.id.sem3);
        sem4 = findViewById(R.id.sem4);
        sem5 = findViewById(R.id.sem5);
        sem6 = findViewById(R.id.sem6);
        sem7 = findViewById(R.id.sem7);
        id = findViewById(R.id.placementid);
        companyname = findViewById(R.id.companyname);
        jobtitle = findViewById(R.id.jobtitle);
        address = findViewById(R.id.address);
        requirements = findViewById(R.id.requirements);
        salary = findViewById(R.id.salary);

        id.setText(placementid);
        rollno.setText(username);

        showAllData();

    }

    private void showAllData() {

        DatabaseReference ref1 = FirebaseDatabase.getInstance().getReference("users");
        DatabaseReference ref2 = FirebaseDatabase.getInstance().getReference("companydata");
        Query checkuser = ref1.orderByChild("username").equalTo(username);
        Query checkid = ref2.orderByChild("placementid").equalTo(placementid);
        checkuser.addListenerForSingleValueEvent(new ValueEventListener() {
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
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        checkid.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String scompanyname = dataSnapshot.child(placementid).child("name").getValue(String.class);
                String sjobtitle = dataSnapshot.child(placementid).child("job").getValue(String.class);
                String saddress = dataSnapshot.child(placementid).child("address").getValue(String.class);
                String srequirements = dataSnapshot.child(placementid).child("requirements").getValue(String.class);

                String ssalary = dataSnapshot.child(placementid).child("salary").getValue(String.class);

                companyname.setText(scompanyname);
                jobtitle.setText(sjobtitle);
                address.setText(saddress);
                requirements.setText(srequirements);

                salary.setText(ssalary);
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

                        Intent intent = new Intent(ViewApplicationDetails.this,ApplicationActivity.class);
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
    public void acceptapplication(View view){

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Accept Application!")
                .setMessage("Do you want to accept this application ?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DatabaseReference reff1= FirebaseDatabase.getInstance().getReference("applicationdata").child(username+placementid);

                        StoreApplicationData resp = new StoreApplicationData();
                        resp.setResponse("Accepted");
                        HashMap<String,Object> hashMap = new HashMap<>();
                        hashMap.put("response",resp.getResponse());
                        reff1.updateChildren(hashMap);


                        Intent intent = new Intent(getApplicationContext(), ApplicationActivity.class);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(),"Application Accepted",Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }

                });
        alertDialogBuilder.show();

    }
    public void rejectapplication(View view){


        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Reject Application!")
                .setMessage("Do you want to reject this application ?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DatabaseReference reff1= FirebaseDatabase.getInstance().getReference("applicationdata").child(username+placementid);

                        StoreApplicationData resp = new StoreApplicationData();
                        resp.setResponse("Rejected");
                        HashMap<String,Object> hashMap = new HashMap<>();
                        hashMap.put("response",resp.getResponse());
                        reff1.updateChildren(hashMap);

                        Intent intent = new Intent(getApplicationContext(), ApplicationActivity.class);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(),"Application Rejected",Toast.LENGTH_SHORT).show();
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