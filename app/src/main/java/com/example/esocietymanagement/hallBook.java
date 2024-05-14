package com.example.esocietymanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class hallBook extends AppCompatActivity {

    ImageButton btnAdd;
    String scode,mobile;
    LinearLayout linearLayout;
    RecyclerView recyclerView;
    EditText getmonth,getyear;
    Button chk;

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("society");
    final List<hallHelper> expHelpersList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hall_book);


        btnAdd = findViewById(R.id.addBtn);
        linearLayout = findViewById(R.id.addlinear);
        recyclerView = findViewById(R.id.recycleview);
        getmonth=findViewById(R.id.getmonth);
        getyear=findViewById(R.id.getyear);
        chk=findViewById(R.id.chkhall);


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
                            Intent intent = new Intent(getApplicationContext(), hallDetails.class);
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

        recyclerView.setLayoutManager(new LinearLayoutManager(hallBook.this));

        chk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        if (getyear.getText().toString() == null) {
                            Toast.makeText(hallBook.this, "please Enter Year", Toast.LENGTH_SHORT).show();

                        } else if (getmonth.getText().toString() == null) {
                            Toast.makeText(hallBook.this, "Please Enter Month", Toast.LENGTH_SHORT).show();
                        } else {
                            String gyear = getyear.getText().toString();
                            String gmonth = getmonth.getText().toString();


                            expHelpersList.clear();
                            for (DataSnapshot expense : snapshot.child(scode).child("hallbook").child(gyear).child(gmonth).getChildren()) {

                                if (expense.hasChild("day") && expense.hasChild("month") && expense.hasChild("year") && expense.hasChild("name") && expense.hasChild("reason")) {


                                    String day = expense.child("day").getValue(String.class);
                                    String month = expense.child("month").getValue(String.class);
                                    String year = expense.child("year").getValue(String.class);
                                    String name = expense.child("name").getValue(String.class);
                                    String amt = expense.child("reason").getValue(String.class);
                                    String date = (day + " - " + month + " - " + year);

                                    hallHelper hallHelper = new hallHelper(date, name, amt);

                                    expHelpersList.add(hallHelper);
                                }

                            }

                            recyclerView.setAdapter(new hallAdapter(expHelpersList, hallBook.this));
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });



    }
}