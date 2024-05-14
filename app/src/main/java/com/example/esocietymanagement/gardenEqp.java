package com.example.esocietymanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class gardenEqp extends AppCompatActivity {

    ImageButton btnAdd;
    String scode,mobile;
    LinearLayout linearLayout;
    RecyclerView recyclerView;

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("society");
    public List<gardenHelper> gardenHelperList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garden_eqp);

        btnAdd = findViewById(R.id.addBtn);
        linearLayout = findViewById(R.id.addlinear);
        recyclerView = findViewById(R.id.recycleview);

        SharedPreferences preferences = getSharedPreferences("login", MODE_PRIVATE);
        scode = preferences.getString("scode", null);
        mobile = preferences.getString("mobile", null);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference chkadmin=FirebaseDatabase.getInstance().getReference("user");
                chkadmin.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String chkad=snapshot.child(mobile).child("society").child(scode).child("admin").getValue(String.class);
                        if(chkad.equals("admin")){
                            Intent intent = new Intent(getApplicationContext(), gardenEqpDetails.class);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


            }
        });

        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(gardenEqp.this));

        databaseReference.addValueEventListener(new ValueEventListener() {


            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                gardenHelperList.clear();
                for (DataSnapshot garden : snapshot.child(scode).child("garden").getChildren()) {

                    if(garden.hasChild("name") && garden.hasChild("price") && garden.hasChild("dec")){


                        String name= garden.child("name").getValue(String.class);
                        String price= garden.child("price").getValue(String.class);
                        String dec= garden.child("dec").getValue(String.class);

                        gardenHelper gardenHelper = new gardenHelper(name,price,dec);

                        gardenHelperList.add(gardenHelper);
                    }

                    recyclerView.setAdapter(new gardenAdapter(gardenHelperList, gardenEqp.this));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




    }
}