package com.example.placementstaff;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

public class AddPlacementOffer extends AppCompatActivity  {

    EditText placementid, companyname, jobtitle, companyaddress, requirements, salary;

    Toolbar toolbar;

    String imageid, imageurl;
    Button addplacement;
    TextView chooselogo;
    private ImageView logo;
    Uri filepath;
    Uri resultUri;
    StorageReference storageReference;
    FirebaseStorage storage;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    EditText branchselect,cgpascore,ktlimit,email,contact,compsite;
    private static final int PICK_IMAGE_REQUEST = 1;

    public AddPlacementOffer() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_add_placement_offer);

        toolbar=findViewById(R.id.tool);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        branchselect=findViewById(R.id.branchselect);
        cgpascore=findViewById(R.id.cgpa);
        ktlimit=findViewById(R.id.ktlimit);
        contact=findViewById(R.id.comcontactno);
        email=findViewById(R.id.comemail);
        compsite=findViewById(R.id.compsite);
       /* ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.branches,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        branchselect.setAdapter(adapter);
        branchselect.setOnItemSelectedListener(new BranchSelectClass());

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,R.array.scores,android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cgpascore.setAdapter(adapter1);
        cgpascore.setOnItemSelectedListener(new ScoresSelectClass());
*/

        placementid = findViewById(R.id.comid);
        companyname = findViewById(R.id.comname);
        companyaddress = findViewById(R.id.comaddress);
        jobtitle = findViewById(R.id.jobtitle);
        requirements = findViewById(R.id.requirements);
        //criteria = findViewById(R.id.criteria);
        salary = findViewById(R.id.salary);
        addplacement = findViewById(R.id.addofferbtn);
        chooselogo = findViewById(R.id.chooselogo);
        logo = findViewById(R.id.logo);
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        chooselogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

    class BranchSelectClass implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            String branch = adapterView.getItemAtPosition(i).toString();
            /*HashMap<String,Object> hashMap = new HashMap<>();
            hashMap.put("branch",branch);
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("companydata").child(placementid.getText().toString());
            reference.child("branch").setValue(branch);*/
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }
    class ScoresSelectClass implements AdapterView.OnItemSelectedListener{

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            String mincgpa = adapterView.getItemAtPosition(i).toString();
            /*HashMap<String,Object> hashMap = new HashMap<>();
            hashMap.put("minimumcgpa",adapterView.getItemAtPosition(i));
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("companydata").child(placementid.getText().toString());
            reference.updateChildren(hashMap);*/
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }


    //select image method
    /*private void SelectImage() {

        //defining implicit intent to mobile gallery
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(
                Intent.createChooser(
                        intent,
                        "Select Image from here..."), PICK_IMAGE_REQUEST);

    }*/

    //override onActivityResult method


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //checking request code and result code
        //if request code is PICK_IMAGE_REQUEST and
        //resultcode is RESULT_OK
        //then set image in the image view
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            //get Uri of data
            filepath = data.getData();

            CropImage.activity(filepath)
                    .setAspectRatio(1,1)
                    .start(this);
        }
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {

            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            //if (requestCode == RESULT_OK) {
                 resultUri = result.getUri();

                try {
                    //setting image on image view(logo) using Bitmap
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), resultUri);
                    logo.setImageBitmap(bitmap);
                } catch (IOException e) {

                    //Log the exception
                    e.printStackTrace();

                }


            //}
        }

    }

    //UploadImage method
    private void uploadImage() {
        if (resultUri != null) {
            //code for showing progressDialog while uploading
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            imageid = UUID.randomUUID().toString();

            //defining the child of storageReference
            final StorageReference ref = storageReference.child("images/" + imageid);

            //adding listeners on upload
            //or failure of image
            ref.putFile(resultUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(final UploadTask.TaskSnapshot taskSnapshot) {
                    //image uploaded sucessfully
                    //dismiss dialog
                    progressDialog.dismiss();
                    Toast.makeText(AddPlacementOffer.this, "Placement Added Sucessfully...", Toast.LENGTH_SHORT).show();


                }
            })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            //Error, image not uploaded
                            progressDialog.dismiss();
                            Toast.makeText(AddPlacementOffer.this, "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                            progressDialog.setMessage("Uploaded " + (int) progress + "%");
                        }
                    }).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                    task.getResult().getStorage().getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            imageurl = uri.toString();
                            //HashMap<String,String> hashMap=new HashMap<>();
                            //hashMap.put("imageurl",imageurl);
                            Log.d("URL", imageurl);
                            rootNode = FirebaseDatabase.getInstance();
                            reference = rootNode.getReference("companydata");



                            String cid = placementid.getText().toString();
                            String cname = companyname.getText().toString();
                            String cjobtitle = jobtitle.getText().toString();
                            String caddress = companyaddress.getText().toString();
                            String crequirements = requirements.getText().toString();
                            String cbranch = branchselect.getText().toString();
                            String cktlimit = ktlimit.getText().toString();
                            String cmincgpa = cgpascore.getText().toString();
                            String cemail = email.getText().toString();
                            String ccontact = contact.getText().toString();
                            String csite = compsite.getText().toString();


                            String csalary = salary.getText().toString();


                            //Log.d("URL after upload",imageurl);
                            CompanyDetailsAdderClass companyDetailsAdderClass = new CompanyDetailsAdderClass(cid, cname, cjobtitle, caddress, crequirements, csalary, imageurl,cbranch,cmincgpa,cktlimit,cemail,ccontact,csite);
                            reference.child(cid).setValue(companyDetailsAdderClass);

                            //DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference("companylogo");
                            //reference1.push().child("imageurl").setValue(imageurl);
                        }
                    });
                }
            });


        }


    }


    private Boolean validateid() {
        final String val = placementid.getText().toString();

        if (val.isEmpty()) {
            placementid.setError("Field cannot be empty");
            return false;
        } else {
            return true;
        }


    }


    private Boolean validateName() {
        String val = companyname.getText().toString();
        if (val.isEmpty()) {
            companyname.setError("Field cannot be empty");
            return false;
        } else {
            companyname.setError(null);
            return true;
        }

    }

    private Boolean validateJobtitle() {
        String val = jobtitle.getText().toString();
        if (val.isEmpty()) {
            jobtitle.setError("Field cannot be empty");
            return false;
        } else {
            jobtitle.setError(null);
            return true;
        }

    }

    public void addplacementdetails(View view) {
        if (!validateName() | !validateJobtitle() | !validateid()) {
            return;
        }


        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("companydata");
        Query checkid = ref.orderByChild("placementid").equalTo(placementid.getText().toString());
        checkid.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    placementid.setError("Id already Exists");
                    placementid.requestFocus();

                } else {
                    uploadImage();

                    Intent intent = new Intent(AddPlacementOffer.this, PlacementPage.class);
                    startActivity(intent);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }


}