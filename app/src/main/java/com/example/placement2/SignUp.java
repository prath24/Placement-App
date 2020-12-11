package com.example.placement2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {
    TextInputLayout regName,regUsername,regPassword,regEmail,regPhoneNo,regBranch,regDiv,regSboard,regTenthpercentage,regTenthyear,regHboard,regTwelvethpercentage,regTwelvethyear,regSem1,regSem2,regSem3,regSem4,regSem5,regSem6,regSem7,regSem8;
    Button regBtn,regToLoginBtn;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up);

        regName = findViewById(R.id.name);
        regUsername = findViewById(R.id.username);
        regPassword = findViewById(R.id.password);
        regEmail = findViewById(R.id.email);
        regPhoneNo = findViewById(R.id.phoneNo);
        regBranch = findViewById(R.id.branch);
        regDiv = findViewById(R.id.div);
        regSboard = findViewById(R.id.sboard);
        regTenthpercentage = findViewById(R.id.tenthpercentage);
        regTenthyear = findViewById(R.id.tenthyear);
        regHboard = findViewById(R.id.hboard);
        regTwelvethpercentage = findViewById(R.id.twelvethpercentage);
        regTwelvethyear = findViewById(R.id.twelvethyear);
        regSem1 = findViewById(R.id.sem1);
        regSem2 = findViewById(R.id.sem2);
        regSem3 = findViewById(R.id.sem3);
        regSem4 = findViewById(R.id.sem4);
        regSem5 = findViewById(R.id.sem5);
        regSem6 = findViewById(R.id.sem6);
        regSem7 = findViewById(R.id.sem7);
        regSem8 = findViewById(R.id.sem8);
        regBtn = findViewById(R.id.reg_btn);
        regToLoginBtn = findViewById(R.id.callLogin);


    }

    //account data
    private Boolean validateName(){
        String val=regName.getEditText().getText().toString();
        if(val.isEmpty()){
            regName.setError("Field cannot be empty");
            return false;
        }
        else{
            regName.setError(null);
            regName.setErrorEnabled(false);
            return true;
        }

    }

    private Boolean validateUsername(){
        String val=regUsername.getEditText().getText().toString();
        String noWhiteSpace = "\\A\\w{4,20}\\z";
        if(val.isEmpty()){
            regUsername.setError("Field cannot be empty");
            return false;
        }
        else if(val.length()>15){
            regUsername.setError("maximum 10 characters");
            return false;
        }
        else if(!val.matches(noWhiteSpace)){
            regUsername.setError("White Spaces not allowed");
            return false;
        }
        else{
            regUsername.setError(null);
            regUsername.setErrorEnabled(false);
            return true;
        }

    }

    private Boolean validateEmail(){
        String val=regEmail.getEditText().getText().toString();
        String emailPattern="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if(val.isEmpty()){
            regEmail.setError("Field cannot be empty");
            return false;
        }

        else{
            regEmail.setError(null);
            regEmail.setErrorEnabled(false);
            return true;
        }

    }

    private Boolean validatePhoneNo(){
        String val=regPhoneNo.getEditText().getText().toString();
        if(val.isEmpty()){
            regPhoneNo.setError("Field cannot be empty");
            return false;
        }
        else{
            regPhoneNo.setError(null);
            regPhoneNo.setErrorEnabled(false);
            return true;
        }

    }

    private Boolean validatePassword(){
        String val=regPassword.getEditText().getText().toString();
        String noWhiteSpace = "\\A\\w{4,20}\\z";
        if(val.isEmpty()){
            regPassword.setError("Field cannot be empty");
            return false;
        }
        else if(val.length()<6) {
            regPassword.setError("minimum 6 characters");
            return false;
        }
        else if(!val.matches(noWhiteSpace)) {
            regPassword.setError("White Spaces not allowed");
            return false;
        }
        else{
            regPassword.setError(null);
            regPassword.setErrorEnabled(false);
            return true;
        }

    }

    //academic data
    private Boolean validateBranch(){
        String val=regBranch.getEditText().getText().toString();
        String noWhiteSpace = "\\A\\w{4,20}\\z";
        if(val.isEmpty()){
            regBranch.setError("Field cannot be empty");
            return false;
        }



        else{
            regBranch.setError(null);
            regBranch.setErrorEnabled(false);
            return true;
        }

    }
    private Boolean validateDiv(){
        String val=regDiv.getEditText().getText().toString();
        String noWhiteSpace = "\\A\\w{4,20}\\z";
        if(val.isEmpty()){
            regDiv.setError("Field cannot be empty");
            return false;
        }
        else if(val.length()>1){
            regDiv.setError("Only 1 character");
            return false;
        }


        else{
            regDiv.setError(null);
            regDiv.setErrorEnabled(false);
            return true;
        }

    }
    private Boolean validateSboard(){
        String val=regSboard.getEditText().getText().toString();

        if(val.isEmpty()){
            regSboard.setError("Field cannot be empty");
            return false;
        }

        else{
            regSboard.setError(null);
            regSboard.setErrorEnabled(false);
            return true;
        }

    }
    private Boolean validateHboard(){
        String val=regHboard.getEditText().getText().toString();

        if(val.isEmpty()){
            regHboard.setError("Field cannot be empty");
            return false;
        }

        else{
            regHboard.setError(null);
            regHboard.setErrorEnabled(false);
            return true;
        }

    }
    private Boolean validateTenthpercentage(){
        String val=regTenthpercentage.getEditText().getText().toString();
        String noWhiteSpace = "\\A\\w{4,20}\\z";
        if(val.isEmpty()){
            regTenthpercentage.setError("Field cannot be empty");
            return false;
        }


        else{
            regTenthpercentage.setError(null);
            regTenthpercentage.setErrorEnabled(false);
            return true;
        }

    }
    private Boolean validateTwelvethpercentage(){
        String val=regTwelvethpercentage.getEditText().getText().toString();
        String noWhiteSpace = "\\A\\w{4,20}\\z";
        if(val.isEmpty()){
            regTwelvethpercentage.setError("Field cannot be empty");
            return false;
        }


        else{
            regTwelvethpercentage.setError(null);
            regTwelvethpercentage.setErrorEnabled(false);
            return true;
        }

    }
    private Boolean validateTenthyear(){
        String val=regTenthyear.getEditText().getText().toString();
        String noWhiteSpace = "\\A\\w{4,20}\\z";
        if(val.isEmpty()){
            regTenthyear.setError("Field cannot be empty");
            return false;
        }
        else if(val.length()>4){
            regTenthyear.setError("Invalid year");
            return false;
        }
        else if(!val.matches(noWhiteSpace)){
            regTenthyear.setError("White Spaces not allowed");
            return false;
        }
        else{
            regTenthyear.setError(null);
            regTenthyear.setErrorEnabled(false);
            return true;
        }

    }
    private Boolean validateTwelvethyear(){
        String val=regTwelvethyear.getEditText().getText().toString();
        String noWhiteSpace = "\\A\\w{4,20}\\z";
        if(val.isEmpty()){
            regTwelvethyear.setError("Field cannot be empty");
            return false;
        }
        else if(val.length()>4){
            regTwelvethyear.setError("Invalid year");
            return false;
        }
        else if(!val.matches(noWhiteSpace)){
            regTwelvethyear.setError("White Spaces not allowed");
            return false;
        }
        else{
            regTwelvethyear.setError(null);
            regTwelvethyear.setErrorEnabled(false);
            return true;
        }

    }
    private Boolean validateSem1(){
        String val=regSem1.getEditText().getText().toString();

        if(val.isEmpty()){
            regSem1.setError("Field cannot be empty");
            return false;
        }


        else{
            regSem1.setError(null);
            regSem1.setErrorEnabled(false);
            return true;
        }

    }
    private Boolean validateSem2(){
        String val=regSem2.getEditText().getText().toString();

        if(val.isEmpty()){
            regSem2.setError("Field cannot be empty");
            return false;
        }


        else{
            regSem2.setError(null);
            regSem2.setErrorEnabled(false);
            return true;
        }

    }
    private Boolean validateSem3(){
        String val=regSem3.getEditText().getText().toString();

        if(val.isEmpty()){
            regSem3.setError("Field cannot be empty");
            return false;
        }


        else{
            regSem3.setError(null);
            regSem3.setErrorEnabled(false);
            return true;
        }

    }
    private Boolean validateSem4(){
        String val=regSem4.getEditText().getText().toString();

        if(val.isEmpty()){
            regSem4.setError("Field cannot be empty");
            return false;
        }


        else{
            regSem4.setError(null);
            regSem4.setErrorEnabled(false);
            return true;
        }

    }
    private Boolean validateSem5(){
        String val=regSem5.getEditText().getText().toString();

        if(val.isEmpty()){
            regSem5.setError("Field cannot be empty");
            return false;
        }


        else{
            regSem5.setError(null);
            regSem5.setErrorEnabled(false);
            return true;
        }

    }
    private Boolean validateSem6(){
        String val=regSem6.getEditText().getText().toString();

        if(val.isEmpty()){
            regSem6.setError("Field cannot be empty");
            return false;
        }


        else{
            regSem6.setError(null);
            regSem6.setErrorEnabled(false);
            return true;
        }

    }
    private Boolean validateSem7(){
        String val=regSem7.getEditText().getText().toString();

        if(val.isEmpty()){
            regSem7.setError("Field cannot be empty");
            return false;
        }


        else{
            regSem7.setError(null);
            regSem7.setErrorEnabled(false);
            return true;
        }

    }
    private Boolean validateSem8(){
        String val=regSem8.getEditText().getText().toString();

        if(val.isEmpty()){
            regSem8.setError("Field cannot be empty");
            return false;
        }


        else{
            regSem8.setError(null);
            regSem8.setErrorEnabled(false);
            return true;
        }

    }


    //Save data in firebase on click
     public void registerUser(View view)
     {
         if(!validateName() | !validatePassword() | !validateEmail() | !validatePhoneNo() | !validateUsername() | !validateBranch() | !validateDiv() | !validateSboard() | !validateHboard() | !validateTenthpercentage() | !validateTwelvethpercentage() | !validateTenthyear() | !validateTwelvethyear() | !validateSem1() | !validateSem2() | !validateSem3() | !validateSem4() | !validateSem5() | !validateSem6() | !validateSem7() | !validateSem8()){
             return;
         }

         rootNode=FirebaseDatabase.getInstance();
         reference=rootNode.getReference("users");

         String name=regName.getEditText().getText().toString();
         String username=regUsername.getEditText().getText().toString();
         String email=regEmail.getEditText().getText().toString();
         String phoneNo=regPhoneNo.getEditText().getText().toString();
         String password=regPassword.getEditText().getText().toString();
         String branch=regBranch.getEditText().getText().toString();
         String div=regDiv.getEditText().getText().toString();
         String sboard=regSboard.getEditText().getText().toString();
         String hboard=regHboard.getEditText().getText().toString();
         String tenthpercentage=regTenthpercentage.getEditText().getText().toString();
         String twelvethpercentage=regTwelvethpercentage.getEditText().getText().toString();
         String tenthyear=regTenthyear.getEditText().getText().toString();
         String twelvethyear=regTwelvethyear.getEditText().getText().toString();
         String sem1=regSem1.getEditText().getText().toString();
         String sem2=regSem2.getEditText().getText().toString();
         String sem3=regSem3.getEditText().getText().toString();
         String sem4=regSem4.getEditText().getText().toString();
         String sem5=regSem5.getEditText().getText().toString();
         String sem6=regSem6.getEditText().getText().toString();
         String sem7=regSem7.getEditText().getText().toString();
         String sem8=regSem8.getEditText().getText().toString();

         Intent intent= new Intent(getApplicationContext(),VerifyPhoneNo.class);
         intent.putExtra("phoneNo",phoneNo);
         startActivity(intent);

         UserHelperClass helperClass = new UserHelperClass(name,username,email,phoneNo,password,branch,div,sboard,tenthpercentage,tenthyear,hboard,twelvethpercentage,twelvethyear,sem1,sem2,sem3,sem4,sem5,sem6,sem7,sem8);
         reference.child(username).setValue(helperClass);

     }


}