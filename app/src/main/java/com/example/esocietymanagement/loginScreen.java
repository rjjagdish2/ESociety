package com.example.esocietymanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.badge.BadgeUtils;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class loginScreen extends AppCompatActivity {

    Button loginBtn,signupBtn;
    EditText txtusername,txtpassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login_screen);

        loginBtn=findViewById(R.id.loginBtn);
        signupBtn=findViewById(R.id.signupBtn);
        txtusername=findViewById(R.id.txtUser);
        txtpassword=findViewById(R.id.txtPassword);

        signupBtn.setOnClickListener(v -> {
            Intent intent=new Intent(loginScreen.this,registerUser.class);
            startActivity(intent);
        });


        loginBtn.setOnClickListener(v -> {


            String username=txtusername.getEditableText().toString();
            String password=txtpassword.getEditableText().toString();

            if(!username.isEmpty()){
                txtusername.setError(null);
                //txtusername.setEnabled(false);

                if(!password.isEmpty()){
                    txtpassword.setError(null);
                    //txtpassword.setEnabled(false);

                    final String username_d=txtusername.getText().toString();

                    FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
                    DatabaseReference databaseReference=firebaseDatabase.getReference("user");

                    Query checkusername=databaseReference.orderByChild("username").equalTo(username_d);

                    checkusername.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            if(snapshot.exists()){

                                txtusername.setError(null);
                                 String password_d=snapshot.child(username_d).child("password").getValue(String.class);

                                if(password_d.equals(password)){
                                    txtpassword.setError(null);
                                    Toast.makeText(getApplicationContext(), "Login Successfully", Toast.LENGTH_SHORT).show();
                                    Intent intent=new Intent(getApplicationContext(),userDashbord.class);
                                    startActivity(intent);
                                    finish();

                                }else {
                                    txtpassword.setError("Incorrect Password");
                                }

                            }else{
                                txtusername.setError("User Dose Not Exists");
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }else{
                    txtpassword.setError("Please Enter Password");
                }

            }else{
                txtusername.setError("Please Enter Username");
            }


        });

    }
}