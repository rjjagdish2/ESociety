package com.example.esocietymanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class FirstScreen extends AppCompatActivity {

    Button join,create;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen);

        create= findViewById(R.id.create);
        join= findViewById(R.id.join);



        create.setOnClickListener(v -> {
            Intent intent=new Intent(getApplicationContext(), societyregisterScreen.class);
            startActivity(intent);
        });

        join.setOnClickListener(v -> {
            Intent intent1=new Intent(getApplicationContext(),joinSociety.class);
            startActivity(intent1);
        });

    }
}