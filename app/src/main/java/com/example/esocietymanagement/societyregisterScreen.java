package com.example.esocietymanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

public class societyregisterScreen extends AppCompatActivity {

    EditText plotNo,address,societyName,city;
    ImageButton regBtn;
    public Spinner spinner;
    @SuppressLint({"MissingInflatedId", "ResourceAsColor"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_societyregister_screen);

        plotNo=findViewById(R.id.plotNo);
        address=findViewById(R.id.address);
        societyName=findViewById(R.id.societyName);
        city=findViewById(R.id.city);
        regBtn=findViewById(R.id.regBtn);



        regBtn.setOnClickListener(v -> {
            if(!(plotNo.getText().toString().matches("") && address.getText().toString().matches("") && societyName.getText().toString().matches("") && city.getText().toString().matches(""))) {
                String plot_no = plotNo.getText().toString();
                String society_name = societyName.getText().toString();
                String city_name = city.getText().toString();
                String address_full = address.getText().toString();


                Intent intent = new Intent(societyregisterScreen.this, societyDetails.class);
                intent.putExtra("plot", plot_no);
                intent.putExtra("address", address_full);
                intent.putExtra("city", city_name);
                intent.putExtra("society", society_name);

                startActivity(intent);

            }
        });

    }


}