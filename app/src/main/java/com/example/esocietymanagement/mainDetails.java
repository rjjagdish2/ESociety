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

public class mainDetails extends AppCompatActivity implements  DatePickerDialog.OnDateSetListener{


    Button fromdate,todate,btnAdd;
    String yearS,dayS;
    Integer monthS;

    String currentDateString;
    TextInputLayout expTitle,expAmt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_details);

        fromdate=findViewById(R.id.fromDate);
        //todate=findViewById(R.id.toDate);
        Calendar calendar=Calendar.getInstance();

        expTitle=findViewById(R.id.expTitle);
        expAmt=findViewById(R.id.expAmt);
        btnAdd=findViewById(R.id.expBtn);

        expTitle.getEditText().setText("Maintainance");


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!(expTitle.getEditText().getText().toString().matches("")) && yearS != null) {

                    if (!(expAmt.getEditText().getText().toString().matches(""))) {

                        SharedPreferences preferences = getSharedPreferences("login", MODE_PRIVATE);
                        String scode = preferences.getString("scode", null);

                        String name = expTitle.getEditText().getText().toString();
                        String amt = expAmt.getEditText().getText().toString();
                        String months = String.valueOf((monthS + 1));

                        FirebaseDatabase firebaseDatabase1 = FirebaseDatabase.getInstance();
                        DatabaseReference dataexp = firebaseDatabase1.getReference("society").child(scode);

                        mainHelper mainHelper = new mainHelper(name, dayS, amt, months, yearS);
                        dataexp.child("maintainance").child(yearS).child(months).setValue(mainHelper);

                        clr c1=new clr();
                        c1.clear(expAmt,expTitle);
                        expAmt.clearFocus();

                        Toast.makeText(mainDetails.this, "Expense Added Successfully", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(mainDetails.this, "Fill All Details", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(mainDetails.this, "Fill All Details", Toast.LENGTH_SHORT).show();
                }

            }
        });



        fromdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker=new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(),"date picker");


            }
        });
        /*todate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {
                DialogFragment datePicker2=new DatePickerFragment();
                datePicker2.show(getSupportFragmentManager(),"date picker2");
            }
        });*/
    }



    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        Calendar c=Calendar.getInstance();
        c.set(Calendar.YEAR,year);
        c.set(Calendar.MONTH,month);
        c.set(Calendar.DAY_OF_MONTH,dayOfMonth);


        yearS= String.valueOf(year);
        dayS= String.valueOf(dayOfMonth);
        monthS= month;

        currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());

        fromdate.setText(currentDateString);



    }



}