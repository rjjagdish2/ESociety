package com.example.esocietymanagement;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;


import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.util.GAuthToken;

import java.util.Objects;


public class loginScreen2 extends AppCompatActivity {

    ImageButton loginBtn;
    Button signupBtn;
    TextInputLayout txtUser,txtPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen2);


        loginBtn=findViewById(R.id.loginBtn);
        signupBtn=findViewById(R.id.signupBtn);
        txtPassword=findViewById(R.id.txtPassword);
        txtUser=findViewById(R.id.mobileUsername);

         signupBtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent=new Intent(getApplicationContext(),otpVerification.class);
                 startActivity(intent);
             }
         });



        loginBtn.setOnClickListener(v -> {

            String username= Objects.requireNonNull(txtUser.getEditText()).getText().toString();
            String password= Objects.requireNonNull(txtPassword.getEditText()).getText().toString();

            if(!username.isEmpty()){
                txtUser.setError(null);
                //txtusername.setEnabled(false);

                if(!password.isEmpty()){
                    txtPassword.setError(null);
                    //txtpassword.setEnabled(false);


                    FirebaseDatabase firebaseDatabase= FirebaseDatabase.getInstance();
                    DatabaseReference databaseReference=firebaseDatabase.getReference("user").child("alluser");


                    Query checkusername=databaseReference.orderByChild("mono").equalTo(username);

                    checkusername.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            if(snapshot.exists()){
                                txtUser.setError(null);
                                txtUser.setErrorEnabled(false);

                                String password_d=snapshot.child(username).child("password").getValue(String.class);

                                if(password_d.equals(password)){

                                    txtPassword.setErrorEnabled(false);
                                    txtPassword.setError(null);

                                    String aflatno=snapshot.child(username).child("flatno").getValue(String.class);
                                    SharedPreferences preferences=getSharedPreferences("login",MODE_PRIVATE);
                                    SharedPreferences.Editor editor=preferences.edit();

                                    editor.putBoolean("flag", true);
                                    editor.putString("mobile", username);
                                    editor.apply();

                                    Toast.makeText(getApplicationContext(), "Login Successfully", Toast.LENGTH_SHORT).show();

                                    SharedPreferences preferences1=getSharedPreferences("login",MODE_PRIVATE);
                                    String scode= preferences1.getString("scode", null);

                                    if(scode!=null) {
                                        Intent intent = new Intent(getApplicationContext(), userDashbord.class);
                                        startActivity(intent);
                                        finish();
                                    }else{
                                        Intent intent = new Intent(getApplicationContext(), FirstScreen.class);
                                        startActivity(intent);
                                        finish();
                                    }

                                }else {
                                    txtPassword.setError("Incorrect Password");
                                }

                            }else{
                                txtUser.setError("User Dose Not Exists");
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }else{
                    txtPassword.setError("Please Enter Password");
                }

            }else{

                txtUser.setError("Please Enter Mobile No.");
            }


        });

    }
    protected  void onStart() {
        super.onStart();


    }

}