package com.example.esocietymanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class splash_screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                SharedPreferences preferences = getSharedPreferences("login", MODE_PRIVATE);
                Boolean check = preferences.getBoolean("flag", false);
                String scode = preferences.getString("scode", null);
                Boolean admin = preferences.getBoolean("admin", false);

                if (check == true && scode != null) {
                    startActivity(new Intent(getApplicationContext(), userDashbord.class));
                    finish();
                } else if (check == true && scode == null){
                    startActivity(new Intent(getApplicationContext(), FirstScreen.class));
                    finish();
                }
                else
                {
                    startActivity(new Intent(splash_screen.this, wlcScreen.class));
                    finish();
                }


            }
        }, 3000);
    }
}