package com.example.esocietymanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.BreakIterator;

public class changeMarq extends AppCompatActivity {

    Button marqok;
    EditText marq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_marq);

        marqok=findViewById(R.id.marqok);
        marq=findViewById(R.id.editText);

        SharedPreferences preferences=getSharedPreferences("login",MODE_PRIVATE);

        String scode = preferences.getString("scode", null);


        DatabaseReference dbrmarq =FirebaseDatabase.getInstance().getReference("society");
        dbrmarq.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String setMarquee=snapshot.child(scode).child("marquee").child("marquee").getValue(String.class);
                marq.setText(setMarquee);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        marqok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mtext=marq.getText().toString();

                String scode = preferences.getString("scode", null);


                DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("society").child(scode);
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        MarqueeHelper marqueeHelper=new MarqueeHelper(mtext);
                        databaseReference.child("marquee").setValue(marqueeHelper);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

    }
}