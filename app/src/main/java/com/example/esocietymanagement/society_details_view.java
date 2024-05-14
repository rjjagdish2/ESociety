package com.example.esocietymanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class society_details_view extends AppCompatActivity {

    TextInputLayout plotno,sname,totfloor,totflat,flatpfloor,add,city;
    Button btnupdate;
    Integer stotfloor,stotfloorflat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_society_details_view);

        plotno=findViewById(R.id.dplotno);
        sname=findViewById(R.id.dsocietyname);
        totflat=findViewById(R.id.dtotflat);
        totfloor=findViewById(R.id.dtotfloor);
        flatpfloor=findViewById(R.id.dflatpfloor);
        add=findViewById(R.id.daddress);
        city=findViewById(R.id.dcity);
        btnupdate=findViewById(R.id.sdbtnUpdate);



        SharedPreferences preferences=getSharedPreferences("login",MODE_PRIVATE);
       String scode= preferences.getString("scode",null);
       String mobile= preferences.getString("mobile",null);


        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("society");
        Query q=databaseReference.child(scode).orderByChild(scode);
        q.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.child(scode).hasChild("plot")) {

                    plotno.getEditText().setText(snapshot.child(scode).child("plot").getValue(String.class));
                    sname.getEditText().setText(snapshot.child(scode).child("society").getValue(String.class));
                    totflat.getEditText().setText(snapshot.child(scode).child("totflats").getValue(String.class));
                    totfloor.getEditText().setText(snapshot.child(scode).child("floor").getValue(String.class));
                    flatpfloor.getEditText().setText(snapshot.child(scode).child("totfloorflat").getValue(String.class));
                    add.getEditText().setText(snapshot.child(scode).child("address").getValue(String.class));
                    city.getEditText().setText(snapshot.child(scode).child("city").getValue(String.class));

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        DatabaseReference chkadmin=FirebaseDatabase.getInstance().getReference("user");
        chkadmin.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String chkad=snapshot.child(mobile).child("society").child(scode).child("admin").getValue(String.class);

                if(chkad.equals("admin")){




                    btnupdate.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {


                            DatabaseReference databaseReference1=FirebaseDatabase.getInstance().getReference("society");


                            databaseReference1.child(scode).child(scode).child("plot").setValue(plotno.getEditText().getText().toString());
                            databaseReference1.child(scode).child(scode).child("address").setValue(add.getEditText().getText().toString());
                            databaseReference1.child(scode).child(scode).child("city").setValue(city.getEditText().getText().toString());
                            databaseReference1.child(scode).child(scode).child("floor").setValue(totfloor.getEditText().getText().toString());
                            databaseReference1.child(scode).child(scode).child("society").setValue(sname.getEditText().getText().toString());
                            databaseReference1.child(scode).child(scode).child("totflats").setValue(totflat.getEditText().getText().toString());
                            databaseReference1.child(scode).child(scode).child("totfloorflat").setValue(flatpfloor.getEditText().getText().toString());



                        }
                    });
                }
                else{

                    btnupdate.setEnabled(false);
                    sname.setEnabled(false);
                    plotno.setEnabled(false);
                    totfloor.setEnabled(false);
                    totflat.setEnabled(false);
                    flatpfloor.setEnabled(false);
                    add.setEnabled(false);
                    city.setEnabled(false);

                    btnupdate.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {


            }
        });



    }
}