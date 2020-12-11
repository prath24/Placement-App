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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

public class ViewPlacementDetails extends AppCompatActivity {

    EditText companyname,jobtitle,companyaddress,requirements,salary;
    ImageView logo;
    EditText branchselect,cgpascore,ktlimit,email,contact,compsite;
    Button editbtn,deletebtn;
    String cid;
    TextView placementid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_view_placement_details);

        cid=getIntent().getStringExtra("id");

        companyname=findViewById(R.id.comname);
        companyaddress=findViewById(R.id.comaddress);
        jobtitle=findViewById(R.id.jobtitle);
        requirements=findViewById(R.id.requirements);

        branchselect=findViewById(R.id.branchselect);
        cgpascore=findViewById(R.id.cgpa);
        ktlimit=findViewById(R.id.ktlimit);
        email=findViewById(R.id.comemail);
        contact=findViewById(R.id.comcontactno);
        compsite=findViewById(R.id.compsite);

        salary=findViewById(R.id.salary);
        editbtn=findViewById(R.id.editbtn);
        deletebtn=findViewById(R.id.deletebtn);
        placementid=findViewById(R.id.comid);
        logo=findViewById(R.id.logo);

      placementid.setText(cid);

        showAllUserData();
    }
    private void showAllUserData(){

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("companydata");
        Query checkUser = reference.orderByChild("placementid").equalTo(cid);
        checkUser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String cjob = dataSnapshot.child(cid).child("job").getValue(String.class);
                String caddress = dataSnapshot.child(cid).child("address").getValue(String.class);
                String crequirements = dataSnapshot.child(cid).child("requirements").getValue(String.class);

                String csalary = dataSnapshot.child(cid).child("salary").getValue(String.class);
                String cname = dataSnapshot.child(cid).child("name").getValue(String.class);
                String imageurl = dataSnapshot.child(cid).child("imageurl").getValue(String.class);

                jobtitle.setText(cjob);
                companyaddress.setText(caddress);
                requirements.setText(crequirements);

                salary.setText(csalary);
                companyname.setText(cname);
                branchselect.setText(dataSnapshot.child(cid).child("branch").getValue(String.class));
                cgpascore.setText(dataSnapshot.child(cid).child("mincgpa").getValue(String.class));
                ktlimit.setText(dataSnapshot.child(cid).child("ktlimit").getValue(String.class));
                email.setText(dataSnapshot.child(cid).child("email").getValue(String.class));
                contact.setText(dataSnapshot.child(cid).child("contact").getValue(String.class));
                compsite.setText(dataSnapshot.child(cid).child("site").getValue(String.class));
                final int radius = 20;
                final int margin = 20;
                final Transformation transformation = new RoundedCornersTransformation(radius, margin);
                Picasso.get().load(imageurl).resize(470, 470).transform(transformation).into(logo);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    public void deleteplacement(View view){

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Delete Placement!")
                .setMessage("Are you sure , you want to delete ?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DatabaseReference reff=FirebaseDatabase.getInstance().getReference("companydata").child(cid);
                        reff.removeValue();
                        Toast.makeText(ViewPlacementDetails.this,"Placement sucessfully deleted",Toast.LENGTH_SHORT);
                        Intent intent=new Intent(ViewPlacementDetails.this,PlacementPage.class);
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