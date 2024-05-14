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

public class MainActivity extends AppCompatActivity {

    ImageButton btnAdd;
    String scode,mobile;
    LinearLayout linearLayout;
    EditText getyear;
    Button chk;
    RecyclerView recyclerView;
    String getYear;


    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("society");
    final List<mainHelper> mainHelpersList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnAdd = findViewById(R.id.addBtn);
        linearLayout = findViewById(R.id.addlinear);
        recyclerView = findViewById(R.id.recycleview);
        getyear=findViewById(R.id.getyear);
        chk=findViewById(R.id.chkmain);


        getYear=getyear.getText().toString();

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
                            Intent intent = new Intent(getApplicationContext(), mainDetails.class);
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

        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));


        chk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {


                        if (getyear.getText().toString() == null) {
                            Toast.makeText(MainActivity.this, "please Enter Year", Toast.LENGTH_SHORT).show();

                        }
                        else {
                            String gyear = getyear.getText().toString();


                            mainHelpersList.clear();
                            for (DataSnapshot maintainance : snapshot.child(scode).child("maintainance").child(gyear).getChildren()) {

                                if (maintainance.hasChild("day") && maintainance.hasChild("month") && maintainance.hasChild("year") && maintainance.hasChild("title") && maintainance.hasChild("amt")) {


                                    String month = maintainance.child("month").getValue(String.class);
                                    String name = maintainance.child("title").getValue(String.class);
                                    String amt = maintainance.child("amt").getValue(String.class);

                                    mainHelper mainHelper = new mainHelper(name, month, amt);

                                    mainHelpersList.add(mainHelper);
                                }

                            }

                            recyclerView.setAdapter(new mainAdapter(mainHelpersList, MainActivity.this));
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