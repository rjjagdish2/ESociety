package com.example.esocietymanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class workerDetails extends AppCompatActivity {

    TextInputLayout wname,wmobile,wprofession;
    Button wadd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_details);

        wname=findViewById(R.id.wname);
        wmobile=findViewById(R.id.wmobile);
        wprofession=findViewById(R.id.wprofession);
        wadd=findViewById(R.id.wadd);

        wadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!(wname.getEditText().getText().toString().matches("") && wmobile.getEditText().getText().toString().matches("")&& wprofession.getEditText().getText().toString().matches(""))){

                    SharedPreferences preferences = getSharedPreferences("login", MODE_PRIVATE);
                    String scode = preferences.getString("scode", null);

                    String name = wname.getEditText().getText().toString();
                    String pro = wprofession.getEditText().getText().toString();
                    String mono = wmobile.getEditText().getText().toString();


                    FirebaseDatabase firebaseDatabase1 = FirebaseDatabase.getInstance();
                    DatabaseReference dataexp = firebaseDatabase1.getReference("society").child(scode);

                    workerHelper workerHelper = new workerHelper(name, mono, pro);
                    dataexp.child("workers").child(mono).setValue(workerHelper);

                    clr c1=new clr();
                    c1.clear(wname,wmobile,wprofession);
                    wmobile.clearFocus();

                    Toast.makeText(workerDetails.this, "Contact Added", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(workerDetails.this, "Please Enter All details", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}