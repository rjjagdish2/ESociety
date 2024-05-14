package com.example.esocietymanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class registerUser extends AppCompatActivity {

    Button loginBtn,signupBtn;
    TextInputLayout txtname,txtmail,txtuser,txtpass;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        loginBtn=findViewById(R.id.loginBtn);
        signupBtn=findViewById(R.id.signupBtn);
        txtname=findViewById(R.id.txtName);
        txtmail=findViewById(R.id.txtMail);
        txtpass=findViewById(R.id.txtPassword);
        txtuser=findViewById(R.id.txtUser);




        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent=new Intent(getApplicationContext(),loginScreen.class);
                    startActivity(intent);
                    finish();
            }
        });

        signupBtn.setOnClickListener(new View.OnClickListener() {
            private  Boolean validateUserName(){

                String val=txtuser.getEditText().getText().toString();

                if(val.isEmpty()){
                    txtuser.setError("Field cannot be empty");
                    return false;
                }
                else if (val.length()>15){
                    txtuser.setError("User Name must be less than 15 character");
                    return false;
                }
                else{

                    txtuser.setError(null);
                    txtuser.setErrorEnabled(false);
                    return true;
                }
            }
            private  Boolean validateEmail(){

                String val=txtmail.getEditText().getText().toString();
                String emailPattern="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

                if(val.isEmpty()){
                    txtuser.setError("Field cannot be empty");
                    return false;
                }
                else if(!val.matches(emailPattern)){
                    txtmail.setError("Invalid Email address");
                    return false;
                }
                else{

                    txtmail.setError(null);
                    txtmail.setErrorEnabled(false);
                    return true;
                }
            }
            private  Boolean validatePass(){

                String val=txtpass.getEditText().getText().toString();

                if(val.isEmpty()){
                    txtpass.setError("Field cannot be empty");
                    return false;
                }
                else{

                    txtpass.setError(null);
                    txtpass.setErrorEnabled(false);
                    return true;
                }
            }
            private  Boolean validateName(){

                String val=txtname.getEditText().getText().toString();

                if(val.isEmpty()){
                    txtname.setError("Field cannot be empty");
                    return false;
                }
                else{

                    txtname.setError(null);
                    txtname.setErrorEnabled(false);
                    return true;
                }
            }
            @Override
            public void onClick(View v) {

                if(!validateName() | !validateEmail() | !validateUserName() | !validatePass()){

                    return;
                }

                String name=txtname.getEditText().getText().toString();
                String username=txtuser.getEditText().getText().toString();
                String password=txtpass.getEditText().getText().toString();
                String mail=txtmail.getEditText().getText().toString();

                firebaseDatabase=FirebaseDatabase.getInstance();
                reference = firebaseDatabase.getReference("user");
                storingData storingData=new storingData(name,username,mail,password);
                reference.child(username).child("userdata").setValue(storingData);




            }
        });


    }
}
