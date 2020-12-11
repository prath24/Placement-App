package com.example.placementstaff;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;

import java.util.HashMap;
import java.util.Map;

public class PosterChangeActivity extends AppCompatActivity {

    ImageView image1, image2, image3, image4;
    ImageView edit1, edit2, edit3, edit4;

    Toolbar toolbar;

    DatabaseReference reference;

    ProgressDialog mProgress;

    private StorageReference mImageStorage;


    String id;

    private static final int PICK_IMAGE_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poster_change);

        id="0";

        mImageStorage= FirebaseStorage.getInstance().getReference();

        toolbar = findViewById(R.id.tool);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        image1 = findViewById(R.id.image1);
        image2 = findViewById(R.id.image2);
        image3 = findViewById(R.id.image3);
        image4 = findViewById(R.id.image4);

        edit1 = findViewById(R.id.edit1);
        edit2 = findViewById(R.id.edit2);
        edit3 = findViewById(R.id.edit3);
        edit4 = findViewById(R.id.edit4);

        reference = FirebaseDatabase.getInstance().getReference().child("sliderimages");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Picasso.get().load(dataSnapshot.child("1").child("imageUrl").getValue().toString()).into(image1);
                Picasso.get().load(dataSnapshot.child("2").child("imageUrl").getValue().toString()).into(image2);
                Picasso.get().load(dataSnapshot.child("3").child("imageUrl").getValue().toString()).into(image3);
                Picasso.get().load(dataSnapshot.child("4").child("imageUrl").getValue().toString()).into(image4);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        edit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id="1";
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(
                        Intent.createChooser(
                                intent,
                                "Select Image from here..."), PICK_IMAGE_REQUEST);
            }
        });
        edit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id="2";
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(
                        Intent.createChooser(
                                intent,
                                "Select Image from here..."), PICK_IMAGE_REQUEST);
            }
        });
        edit3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id="3";
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(
                        Intent.createChooser(
                                intent,
                                "Select Image from here..."), PICK_IMAGE_REQUEST);
            }
        });
        edit4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id="4";
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(
                        Intent.createChooser(
                                intent,
                                "Select Image from here..."), PICK_IMAGE_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK) {
            Uri imageUri = data.getData();

            CropImage.activity(imageUri)
                    .start(this);
        }
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);

            if (resultCode == RESULT_OK) {

                mProgress=new ProgressDialog(PosterChangeActivity.this);
                mProgress.setTitle("Uploading Image");
                mProgress.setMessage("Please wait...");
                mProgress.setCanceledOnTouchOutside(false);
                mProgress.show();

                Uri resultUri = result.getUri();

                final StorageReference filepath= mImageStorage.child("sliderimages").child(id+".jpg");//storing image name as id

                filepath.putFile(resultUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {

                        if (task.isSuccessful()){
                            filepath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    String imageUrl = uri.toString();

                                    Map hashMap = new HashMap<>();
                                    hashMap.put("imageUrl",imageUrl);

                                    reference.child(id).updateChildren(hashMap).addOnCompleteListener(new OnCompleteListener() {
                                        @Override
                                        public void onComplete(@NonNull Task task) {
                                            if (task.isSuccessful()){
                                                mProgress.dismiss();
                                                Toast.makeText(getApplicationContext(),"Uploaded Sucessfully",Toast.LENGTH_SHORT).show();
                                            }
                                            else {
                                                mProgress.dismiss();
                                                Toast.makeText(getApplicationContext(),"Something went wrong",Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                                }
                            });
                        }
                        else {
                            mProgress.dismiss();
                            Toast.makeText(getApplicationContext(),"Something went wrong",Toast.LENGTH_SHORT).show();
                        }

                    }
                });

            }


        }
    }
}