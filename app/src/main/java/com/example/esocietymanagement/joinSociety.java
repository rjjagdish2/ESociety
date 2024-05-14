package com.example.esocietymanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class joinSociety extends AppCompatActivity {

    EditText sCode;
    TextView societyName,cityName,plotno;
    Button chkBtn;
    ImageButton joinBtn;
    String scode;
    String admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_society);

        sCode=findViewById(R.id.code);
        societyName=findViewById(R.id.societyName);
        cityName=findViewById(R.id.cityName);
        chkBtn=findViewById(R.id.checkBtn);
        joinBtn=findViewById(R.id.joinBtn);
        plotno= findViewById(R.id.plotNo);


        SharedPreferences preferences=getSharedPreferences("login",MODE_PRIVATE);
        String mobile = preferences.getString("mobile", null);


       chkBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if(!(sCode.getText().toString().matches(""))){
                   scode = sCode.getEditableText().toString();
                   chkBtn.setVisibility(View.INVISIBLE);

                   FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                   DatabaseReference databaseReference = firebaseDatabase.getReference("society");


                   Query query = databaseReference.child(scode).orderByChild("scode").equalTo(scode);


                   query.addValueEventListener(new ValueEventListener() {
                       @Override
                       public void onDataChange(@NonNull DataSnapshot snapshot) {
                           if (snapshot.exists()) {

                               //second User Login Join from same flat

                               joinBtn.setVisibility(View.VISIBLE);

                               String name = snapshot.child(scode).child("society").getValue(String.class);
                               societyName.setVisibility(View.VISIBLE);
                               societyName.setText("Society Name: " + name);


                               String city = snapshot.child(scode).child("city").getValue(String.class);
                               cityName.setVisibility(View.VISIBLE);
                               cityName.setText("City: " + city);

                               String plot = snapshot.child(scode).child("plot").getValue(String.class);
                               plotno.setVisibility(View.VISIBLE);
                               plotno.setText("Plot No: " + plot);


                           } else {
                               chkBtn.setVisibility(View.VISIBLE);
                               joinBtn.setVisibility(View.INVISIBLE);
                               Toast.makeText(joinSociety.this, "Society Dose Not Exists", Toast.LENGTH_SHORT).show();
                           }
                       }

                       @Override
                       public void onCancelled(@NonNull DatabaseError error) {


                       }
                   });


               }
           }
       });

       joinBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {


               DatabaseReference chkuser=FirebaseDatabase.getInstance().getReference("society").child(scode).child("members");

               chkuser.addValueEventListener(new ValueEventListener() {
                   @Override
                   public void onDataChange(@NonNull DataSnapshot snapshot) {
                       //admin=snapshot.child(mobile).child("admin").getValue(String.class);

                       if(snapshot.hasChild(mobile)) {
                           SharedPreferences p = getSharedPreferences("login", MODE_PRIVATE);
                           SharedPreferences.Editor editor = p.edit();
                           editor.putString("scode", scode);
                           editor.putString("admin", admin);
                           editor.apply();

                           Intent intent = new Intent(getApplicationContext(), userDashbord.class);
                           startActivity(intent);
                           finish();
                       }
                       else{
                           Intent intent=new Intent(getApplicationContext(),userflat_details.class);
                           intent.putExtra("scode",scode);
                           startActivity(intent);
                           finish();
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