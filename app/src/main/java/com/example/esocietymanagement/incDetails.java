package com.example.esocietymanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Calendar;

public class incDetails extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {


    Button datePicker;
    TextInputLayout expTitle, expDec, expAmt;
    Button btnAdd;
    String currentDateString;
    String yearS, dayS;
    Integer monthS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inc_details);

        datePicker = findViewById(R.id.expDate);
        Calendar calendar = Calendar.getInstance();
        expDec = findViewById(R.id.expDec);
        expTitle = findViewById(R.id.expTitle);
        expAmt = findViewById(R.id.expAmt);
        btnAdd = findViewById(R.id.expBtn);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!(expTitle.getEditText().getText().toString().matches("") && yearS == null)) {
                    if ((!(expAmt.getEditText().getText().toString().matches("")))) {


                        SharedPreferences preferences = getSharedPreferences("login", MODE_PRIVATE);
                        String scode = preferences.getString("scode", null);

                        String name = expTitle.getEditText().getText().toString();
                        String dec = expDec.getEditText().getText().toString();
                        String amt = expAmt.getEditText().getText().toString();
                        String months = String.valueOf((monthS + 1));

                        FirebaseDatabase firebaseDatabase1 = FirebaseDatabase.getInstance();
                        DatabaseReference dataexp = firebaseDatabase1.getReference("society").child(scode);

                        incHalper expHelper = new incHalper(name, dec, yearS, months, dayS, amt);
                        dataexp.child("income").child(yearS).child(name).setValue(expHelper);

                        clr c1=new clr();
                        c1.clear(expAmt,expTitle,expDec);
                        expDec.clearFocus();

                        Toast.makeText(incDetails.this, "Income Added Successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(incDetails.this, "Fill All Fields", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(incDetails.this, "Fill All Fields", Toast.LENGTH_SHORT).show();
                }

            }
        });
        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });
    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        yearS = String.valueOf(year);
        dayS = String.valueOf(dayOfMonth);
        monthS = month;

        currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());

        datePicker.setText(currentDateString);

    }
}