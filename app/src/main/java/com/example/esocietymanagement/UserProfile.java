package com.example.esocietymanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserProfile extends AppCompatActivity {


    TextInputLayout Fname,Member,mono,Password,Flatno,Lname;
    Button btnUpdate;
    String setolfflat,tempf,olfflat;
    public int k;




    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        Fname=findViewById(R.id.txtFName);
        mono=findViewById(R.id.txtmono);
        Password=findViewById(R.id.txtPassword);
        Flatno=findViewById(R.id.txtFlatno);
        Member=findViewById(R.id.txtMember);
        Lname=findViewById(R.id.txtLName);
        btnUpdate=findViewById(R.id.btnUpdate);

        SharedPreferences preferences = getSharedPreferences("login", MODE_PRIVATE);
        String mobileno=preferences.getString("mobile",null);
        String scode=preferences.getString("scode",null);



        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference databaseReference =firebaseDatabase.getReference("user").child(mobileno);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String setFname=snapshot.child(mobileno).child("fname").getValue(String.class);
                String setLname=snapshot.child(mobileno).child("lname").getValue(String.class);
                String setMobileno=snapshot.child(mobileno).child("mono").getValue(String.class);
                setolfflat=snapshot.child("society").child(scode).child("flatno").getValue(String.class);
                String setMember=snapshot.child("society").child(scode).child("member").getValue(String.class);

                Fname.getEditText().setText(setFname);
                Lname.getEditText().setText(setLname);
                mono.getEditText().setText(setMobileno);
                Flatno.getEditText().setText(setolfflat);
                Member.getEditText().setText(setMember);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                DatabaseReference dbr = FirebaseDatabase.getInstance().getReference("user").child(mobileno);

                dbr.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {


                        String setFname = Fname.getEditText().getText().toString();
                        String setLname = Lname.getEditText().getText().toString();
                        String setFlatno = Flatno.getEditText().getText().toString();
                        String setMember = Member.getEditText().getText().toString();
                        String setPass = snapshot.child(mobileno).child("password").getValue(String.class);

                        dbr.child(mobileno).child("fname").setValue(setFname);
                        dbr.child(mobileno).child("lname").setValue(setLname);
                        dbr.child("society").child(scode).child("flatno").setValue(setFlatno);
                        dbr.child("society").child(scode).child("member").setValue(setMember);


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                DatabaseReference dbs=FirebaseDatabase.getInstance().getReference("society").child(scode).child("members");
                dbs.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {



                        String setFlatno = Flatno.getEditText().getText().toString();
                        String setMember = Member.getEditText().getText().toString();

                        dbs.child(mobileno).child("flatno").setValue(setFlatno);
                        dbs.child(mobileno).child("member").setValue(setMember);
                        dbs.child("flatno").child(setFlatno).child("flatno").setValue(setFlatno);



                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });



    }


}