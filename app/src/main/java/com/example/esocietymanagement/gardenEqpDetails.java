package com.example.esocietymanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class gardenEqpDetails extends AppCompatActivity {

    TextInputLayout eqpName,eqpPrice,eqpDec;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garden_eqp_details);

        eqpName=findViewById(R.id.eqpName);
        eqpPrice=findViewById(R.id.eqpPrice);
        eqpDec=findViewById(R.id.eqpDec);
        btnAdd=findViewById(R.id.addBtn);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!(eqpPrice.getEditText().getText().toString().matches(""))){
                        if(!( eqpName.getEditText().getText().toString().matches(""))) {

                            SharedPreferences preferences = getSharedPreferences("login", MODE_PRIVATE);
                            String scode = preferences.getString("scode", null);

                            String name = eqpName.getEditText().getText().toString();
                            String price = eqpPrice.getEditText().getText().toString();
                            String dec = eqpDec.getEditText().getText().toString();

                            FirebaseDatabase firebaseDatabase1 = FirebaseDatabase.getInstance();
                            DatabaseReference dataexp = firebaseDatabase1.getReference("society").child(scode);


                            gardenHelper gardenHelper = new gardenHelper(name, price, dec);
                            dataexp.child("garden").child(name).setValue(gardenHelper);

                            clr c1=new clr();
                            c1.clear(eqpName,eqpPrice,eqpDec);
                            eqpDec.clearFocus();

                            Toast.makeText(gardenEqpDetails.this, "Equipment Added Successfully", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(gardenEqpDetails.this, "Fill All Details", Toast.LENGTH_SHORT).show();
                        }
                }
                else{
                    Toast.makeText(gardenEqpDetails.this, "Fill All Details", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}