package com.example.esocietymanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class wlcScreen extends AppCompatActivity {

    Button login,signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wlc_screen);

        login=findViewById(R.id.loginBtn);
        signup=findViewById(R.id.signupBtn);

        login.setOnClickListener(v -> {
            Intent intent=new Intent(getApplicationContext(),loginScreen2.class);
            startActivity(intent);
        });

        signup.setOnClickListener(v -> {
            Intent intent=new Intent(getApplicationContext(),otpVerification.class);
            startActivity(intent);
        });
    }
}