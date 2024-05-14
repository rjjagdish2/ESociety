package com.example.esocietymanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class userflat_details extends AppCompatActivity {


    EditText flatNo;
    EditText members;
    ImageButton regBtn;
    Integer totf;
    FirebaseDatabase firebaseDatabase, firebaseDatabase2;
    DatabaseReference databaseReference, databaseReference2;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userflat_details);


        firebaseDatabase2 = FirebaseDatabase.getInstance();
        databaseReference2 = firebaseDatabase2.getReference("society");

        flatNo = findViewById(R.id.flatNo);
        members = findViewById(R.id.members);
        regBtn = findViewById(R.id.regBtn);


        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(flatNo.getText().toString().matches("") && members.getText().toString().matches(""))) {

                    String scode = getIntent().getStringExtra("scode");

                    String flatno = flatNo.getText().toString();
                    String member = members.getText().toString();

                    preferences = getSharedPreferences("login", MODE_PRIVATE);
                    String mobileno = preferences.getString("mobile", "123");
                    Boolean admin = preferences.getBoolean("admin", false);

                    firebaseDatabase = FirebaseDatabase.getInstance();
                    databaseReference = firebaseDatabase.getReference("user");
                    databaseReference = FirebaseDatabase.getInstance().getReference("user").child(mobileno).child("society");

                    databaseReference2.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            totf = Integer.parseInt(snapshot.child(scode).child(scode).child("totflats").getValue(String.class));
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });


                    //Toast.makeText(userflat_details.this, totf, Toast.LENGTH_SHORT).show();

                    Query cq = databaseReference2.child(scode).child("members").child("flatno").orderByChild("flatno").equalTo(flatno);
                    cq.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {


                            if (!(snapshot.exists())) {
                                String yadmin;
                                if (admin) {
                                    yadmin = "admin";
                                } else {
                                    yadmin = "member";
                                }

                                flatdetHelp flatdetHelp = new flatdetHelp(scode, flatno, member, yadmin, mobileno);
                                databaseReference.child(scode).setValue(flatdetHelp);

                                databaseReference2 = FirebaseDatabase.getInstance().getReference("society").child(scode).child("members");

                                flatdetHelp flatdetHelp2 = new flatdetHelp(scode, flatno, member, yadmin, mobileno);
                                databaseReference2.child(mobileno).setValue(flatdetHelp2);

                                flatdetHelp f3 = new flatdetHelp(flatno, mobileno);
                                databaseReference2.child("flatno").child(flatno).setValue(f3);

                                preferences = getSharedPreferences("login", MODE_PRIVATE);
                                SharedPreferences.Editor editor = preferences.edit();

                                editor.putString("scode", scode);
                                editor.apply();


                                Intent intent1 = new Intent(getApplicationContext(), userDashbord.class);
                                intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent1);
                                finish();
                            }
                            else {
                                //Toast.makeText(userflat_details.this, "Enter Your Flat Details", Toast.LENGTH_SHORT).show();
                            }
                        }


                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {


                        }
                    });


                }
            }
        });

    }
}