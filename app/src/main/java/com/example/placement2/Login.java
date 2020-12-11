package com.example.placement2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
    Button callSignup,loginbt,forgetPassbtn;
    ImageView logoImage;
    TextView logoText,text1;
    TextInputLayout username,password;
    FirebaseAuth fAuth;
    static Login INSTANCE;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        logoImage=findViewById(R.id.logo_Image);
        logoText=findViewById(R.id.logo_name);
        loginbt=findViewById(R.id.login);
        text1=findViewById(R.id.slogan_name);
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        forgetPassbtn=findViewById(R.id.forgetpassbtn);
        fAuth=FirebaseAuth.getInstance();
        INSTANCE=this;


        callSignup = findViewById(R.id.callSignup);

        callSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Login.this,SignUp.class);
                Pair[] pairs = new Pair[5];
                pairs[0] = new Pair<View,String>(logoImage,"logo_image");
                pairs[1] = new Pair<View,String>(logoText,"logo_text");
                pairs[2] = new Pair<View,String>(text1,"text1");
                pairs[3] = new Pair<View,String>(loginbt,"tbutton1");
                pairs[4] = new Pair<View,String>(callSignup,"tbutton2");


                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Login.this,pairs);
                startActivity(intent,options.toBundle());
            }
        });

        forgetPassbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final EditText resetMail = new EditText(v.getContext());
                AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(v.getContext());
                passwordResetDialog.setTitle("Reset Password ?");
                passwordResetDialog.setMessage("Enter your Email for Password Reset Link");
                passwordResetDialog.setView(resetMail);
                passwordResetDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String mail = resetMail.getText().toString();
                        fAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(Login.this,"Reset Link sent to your Email",Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Login.this,"Error! Reset Link not sent"+e.getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                passwordResetDialog.create().show();
            }
        });



    }
    public static Login getActivityInstance()
    {
        return INSTANCE;
    }
    public String getData()
    {
        return this.username.getEditText().getText().toString();
    }

    private Boolean validateUsername(){
        String val=username.getEditText().getText().toString();

        if(val.isEmpty()){
            username.setError("Field cannot be empty");
            return false;
        }


        else{
            username.setError(null);
            username.setErrorEnabled(false);
            return true;
        }

    }

    private Boolean validatePassword(){
        String val=password.getEditText().getText().toString();

        if(val.isEmpty()){
            password.setError("Field cannot be empty");
            return false;
        }


        else{
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }

    }

    public void loginUser(View view){
        //Validate Login info
        if(!validateUsername() | !validatePassword()){
            return;
        }
        else{
            isUser();
        }
    }



    private void isUser() {
        final String userEnteredUsername= username.getEditText().getText().toString().trim();
        final String userEnteredPassword= password.getEditText().getText().toString().trim();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        Query checkUser = reference.orderByChild("username").equalTo(userEnteredUsername);//checking whether the username exists or not
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //if user exists
                if(dataSnapshot.exists()){

                    username.setError(null);
                    username.setErrorEnabled(false);
                    String passwordFromDB = dataSnapshot.child(userEnteredUsername).child("password").getValue(String.class);//Taking user password from DB

                    //comparing the user entered password with password in database
                    if(passwordFromDB.equals(userEnteredPassword)){

                        password.setError(null);
                        password.setErrorEnabled(false);

                        /*String nameFromDB = dataSnapshot.child(userEnteredUsername).child("name").getValue(String.class);//Taking user name from DB
                        String phoneNoFromDB = dataSnapshot.child(userEnteredUsername).child("phoneNo").getValue(String.class);//Taking user phoneNo from DB
                        String emailFromDB = dataSnapshot.child(userEnteredUsername).child("email").getValue(String.class);//Taking user email from DB
                        String usernameFromDB = dataSnapshot.child(userEnteredUsername).child("username").getValue(String.class);//Taking user username from DB
*/

                        /*Intent intent=new Intent(getApplicationContext(),UserProfile.class);

                        intent.putExtra("name",nameFromDB);
                        intent.putExtra("username",usernameFromDB);
                        intent.putExtra("email",emailFromDB);
                        intent.putExtra("phoneNo",phoneNoFromDB);
                        intent.putExtra("password",passwordFromDB);
*/
                        Intent intent1=new Intent(Login.this,HomePage.class);

                        Toast.makeText(Login.this,"Login Sucessfull", Toast.LENGTH_LONG).show();

                        startActivity(intent1);

                    }
                    else {
                        password.setError("Invalid password");
                        password.requestFocus();
                    }
                }
                //if user does not exist
                else{
                    username.setError("No such User exist");
                    username.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}