package com.example.esocietymanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class setPass extends AppCompatActivity {
    ImageButton loginBtn;
    Button signupBtn;
    String mo;
    TextInputLayout txtUser, txtPassword, txtconPassword;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_pass);

        loginBtn = findViewById(R.id.loginBtn);
        signupBtn = findViewById(R.id.signupBtn);
        txtPassword = findViewById(R.id.txtPassword);
        txtUser = findViewById(R.id.mobileUsername);
        txtconPassword = findViewById(R.id.txtconPassword);


        /*SharedPreferences preferences = getSharedPreferences("login", MODE_PRIVATE);
         mo = preferences.getString("mobileno", "123");*/

        //txtUser.getEditText().setText(mo);

        mo=getIntent().getStringExtra("tempMono");
        loginBtn.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), loginScreen2.class);
            startActivity(intent);
        });

        signupBtn.setOnClickListener(v -> {
            String conpass = Objects.requireNonNull(txtconPassword.getEditText()).getText().toString();
            String pass = Objects.requireNonNull(txtPassword.getEditText()).getText().toString();

            if (pass == conpass) {

                firebaseDatabase = FirebaseDatabase.getInstance();
                databaseReference = firebaseDatabase.getReference("user");
                databaseReference=FirebaseDatabase.getInstance().getReference("society").child(mo);

                setPassHelp setPassHelp= new setPassHelp(pass);
                databaseReference.child(mo).setValue(setPassHelp);

                Toast.makeText(setPass.this, "Password set Successfully", Toast.LENGTH_SHORT).show();

                Intent intent=new Intent(getApplicationContext(),register_screen.class);
                startActivity(intent);
                finish();

            } else {
                txtconPassword.setError("Both Password Shouls be Same");
            }
        });
    }
}