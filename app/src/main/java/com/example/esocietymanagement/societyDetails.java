package com.example.esocietymanagement;

import static java.lang.Integer.parseInt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class societyDetails extends AppCompatActivity {

    EditText totalflat, totalfloorFlat, scode;
    Button nxtButton;
    FirebaseDatabase firebaseDatabase1;
    DatabaseReference reference;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_society_details);

        totalflat = findViewById(R.id.totalflat);
        totalfloorFlat = findViewById(R.id.totalfloorFlat);
        scode = findViewById(R.id.scode);
        nxtButton = findViewById(R.id.nxtButton);




        nxtButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!(totalflat.getText().toString().matches("") && totalfloorFlat.getText().toString().matches("") && scode.getText().toString().matches(""))) {
                    Intent intent = getIntent();
                    String plot = intent.getStringExtra("plot");
                    String societyName = intent.getStringExtra("society");
                    String address = intent.getStringExtra("address");
                    String city = intent.getStringExtra("city");

                    String totalfalts = totalflat.getText().toString();
                    String totalfloorflat = totalfloorFlat.getText().toString();
                    String sCode = scode.getText().toString();

                    int totalFlat = parseInt(totalfalts) * parseInt(totalfloorflat);


                    String tflat = String.valueOf(totalFlat);
                    firebaseDatabase1 = FirebaseDatabase.getInstance();
                    reference = firebaseDatabase1.getReference("society");


                    reference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            if (!(totalfalts.toString().matches("") && totalfloorflat.toString().matches("") && sCode.toString().matches(""))) {

                                if (!(snapshot.hasChild(sCode))) {
                                    storingData2 storingData2 = new storingData2(plot, societyName, address, city, totalfalts, totalfloorflat, sCode, tflat);
                                    reference.child(sCode).child(sCode).setValue(storingData2);


                                    SharedPreferences preferences = getSharedPreferences("login", MODE_PRIVATE);
                                    SharedPreferences.Editor editor = preferences.edit();
                                    editor.putString("mobileno", getIntent().getStringExtra("mobileno"));
                                    editor.putString("scode", sCode);
                                    editor.putBoolean("admin", true);
                                    editor.apply();


                                    //continue add child scode
                                    allsHelaper allsHelaper = new allsHelaper(sCode);
                                    reference.child("allsociety").child(sCode).setValue(allsHelaper);


                                    SharedPreferences sharedPreferences2 = getSharedPreferences("login", MODE_PRIVATE);
                                    String scode = sharedPreferences2.getString("scode", null);
                                    String mobileno = sharedPreferences2.getString("mobile", null);


                                    Intent intent1 = new Intent(getApplicationContext(), userflat_details.class);
                                    intent1.putExtra("scode", scode);
                                    startActivity(intent1);


                                } else if (snapshot.hasChild(sCode)) {
                                    //Toast.makeText(societyDetails.this, "Please Enter Unique Code", Toast.LENGTH_SHORT).show();

                                }

                            }
                        }


                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            //Toast.makeText(societyDetails.this, "Please Enter Unique Code", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }

        });


    }

    /*private void flatview() {
        int totflat= Integer.parseInt(totalflat.getText().toString());
        int floorflat= Integer.parseInt(totalfloorFlat.getText().toString());

        Intent intent=new Intent(this,flatView.class);
        intent.putExtra("keyname",floorflat);
        intent.putExtra("keynumber",totflat);
        startActivity(intent);
    }*/
}