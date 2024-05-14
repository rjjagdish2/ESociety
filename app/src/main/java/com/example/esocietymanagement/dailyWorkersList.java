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

public class dailyWorkersList extends AppCompatActivity {

    ImageButton btnAdd;
    String scode,mobile;
    LinearLayout linearLayout;
    RecyclerView recyclerView;

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("society");
    final List<workerHelper> workerHelperList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_workers_list);

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
                            Intent intent = new Intent(getApplicationContext(), DailyWorkersDetails.class);
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

        recyclerView.setLayoutManager(new LinearLayoutManager(dailyWorkersList.this));

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                workerHelperList.clear();
                for (DataSnapshot workers : snapshot.child(scode).child("dailyworkers").getChildren()) {
                    if (workers.hasChild("name") && workers.hasChild("mobile") && workers.hasChild("profession") ) {

                        String name= workers.child("name").getValue(String.class);
                        String pro= workers.child("profession").getValue(String.class);
                        String mono= workers.child("mobile").getValue(String.class);

                        workerHelper workerHelper = new workerHelper(name,mono,pro);

                        workerHelperList.add(workerHelper);
                    }
                }

                recyclerView.setAdapter(new dailyWAdapter(workerHelperList, dailyWorkersList.this));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}