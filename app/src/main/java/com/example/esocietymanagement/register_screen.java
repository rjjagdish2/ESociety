
package com.example.esocietymanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class register_screen extends AppCompatActivity {

    ImageButton signupBtn;
    Button loginBtn;
    TextInputLayout txtfname,txtlname,txtcpass,txtpass,txtmono;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;

    String MoNo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_register);

        loginBtn=findViewById(R.id.loginBtn);
        signupBtn=findViewById(R.id.signupBtn);
        txtfname=findViewById(R.id.txtFName);
        txtlname=findViewById(R.id.txtLName);
        txtpass=findViewById(R.id.txtPassword);
        txtcpass=findViewById(R.id.txtCPassword);
        txtmono=findViewById(R.id.txtmono);



        MoNo=getIntent().getStringExtra("tempMono");
        txtmono.getEditText().setText(MoNo);


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),loginScreen2.class);
                startActivity(intent);
                finish();
            }
        });


       signupBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               String FName=txtfname.getEditText().getText().toString();
               String LName=txtlname.getEditText().getText().toString();
               String Pass=txtpass.getEditText().getText().toString();
               String CPass=txtcpass.getEditText().getText().toString();
               String MoNo=txtmono.getEditText().getText().toString();


               if(TextUtils.isEmpty(FName)){
                   Toast.makeText(register_screen.this, "First Name Should not be Empty", Toast.LENGTH_SHORT).show();
               }

               else if(TextUtils.isEmpty(LName)){
                   Toast.makeText(register_screen.this, "Last Name Should not be Empty", Toast.LENGTH_SHORT).show();
               }



               else if(TextUtils.isEmpty(Pass)){
                   Toast.makeText(register_screen.this, "Password Should not be Empty", Toast.LENGTH_SHORT).show();
               }

               else if(TextUtils.isEmpty(CPass)){
                   Toast.makeText(register_screen.this, "Both Password Must Be Same", Toast.LENGTH_SHORT).show();
               }
               else if(!CPass.equals(Pass)){
                   Toast.makeText(register_screen.this, "Both Password Must Be Same", Toast.LENGTH_SHORT).show();
               }
               else {

                   SharedPreferences preferences=getSharedPreferences("login",MODE_PRIVATE);
                   SharedPreferences.Editor editor=preferences.edit();

                   //editor.putString("mobileno",MoNo);
                   //editor.putBoolean("flag",true);
                   //editor.apply();

                   firebaseDatabase=FirebaseDatabase.getInstance();
                   reference = firebaseDatabase.getReference("user");


                   reference.addValueEventListener(new ValueEventListener() {
                       @Override
                       public void onDataChange(@NonNull DataSnapshot snapshot) {
                           if(snapshot.child("alluser").hasChild(MoNo)) {
                               Toast.makeText(register_screen.this, "User All Ready Exist!", Toast.LENGTH_SHORT).show();
                           }
                           else {
                               storingData storingData=new storingData(FName,LName,MoNo,Pass);
                               reference.child(MoNo).child(MoNo).setValue(storingData);

                               monoSetter monoSetter=new monoSetter(MoNo,Pass);
                               reference.child("alluser").child(MoNo).setValue(monoSetter);

                               Intent intent=new Intent(getApplicationContext(),loginScreen2.class);
                               startActivity(intent);

                               finish();

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