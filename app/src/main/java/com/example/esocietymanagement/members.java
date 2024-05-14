package com.example.esocietymanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class members extends AppCompatActivity {

    String scode,mobile;
    LinearLayout linearLayout;
    RecyclerView recyclerView;
    String totflats;
    int i=0;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("society");
    final List<memberHelper> memberHelperList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_members);

        linearLayout = findViewById(R.id.addlinear);
        recyclerView = findViewById(R.id.recycleview);

        SharedPreferences preferences = getSharedPreferences("login", MODE_PRIVATE);
        scode = preferences.getString("scode", null);
        mobile = preferences.getString("mobile", null);


        DatabaseReference totflat=FirebaseDatabase.getInstance().getReference("society");
        totflat.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                totflats=snapshot.child(scode).child(scode).child("totflats").getValue(String.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(members.this));

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                    memberHelperList.clear();
                    for (DataSnapshot member : snapshot.child(scode).child("members").child("flatno").getChildren()) {
                        i+=1;
                        if (member.hasChild("flatno") ) {


                            String flatno = member.child("flatno").getValue(String.class);

                            String tempMo=member.child("mobile").getValue(String.class);



                            DatabaseReference chkname= FirebaseDatabase.getInstance().getReference("user");
                            chkname.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    String fcname=snapshot.child(tempMo).child(tempMo).child("fname").getValue(String.class);
                                    String lcname=snapshot.child(tempMo).child(tempMo).child("lname").getValue(String.class);


                                    String name=fcname+" "+lcname;

                                    memberHelper memberHelper = new memberHelper(flatno,name);

                                    memberHelperList.add(memberHelper);
                                    recyclerView.setAdapter(new memberAdapter(memberHelperList, members.this));
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });


                        }

                    }


                    while (i<Integer.parseInt(totflats)){
                        memberHelper memberHelper = new memberHelper();
                        memberHelperList.add(memberHelper);
                        recyclerView.setAdapter(new memberAdapter(memberHelperList, members.this));
                        i+=1;
                    }
                }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}