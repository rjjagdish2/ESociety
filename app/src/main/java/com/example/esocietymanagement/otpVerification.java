package com.example.esocietymanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.net.Inet4Address;
import java.util.concurrent.TimeUnit;

public class otpVerification extends AppCompatActivity {

    EditText mobileno;
    Button btngetotp;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verification);

        mobileno=findViewById(R.id.input_mobile_no);
        btngetotp=findViewById(R.id.btngetOtp);

        progressBar=findViewById(R.id.progressbarsend);

        btngetotp.setOnClickListener(v -> {

            if(!mobileno.getText().toString().trim().isEmpty()){
                if(mobileno.getText().toString().trim().length() == 10){

                    progressBar.setVisibility(View.VISIBLE);
                    btngetotp.setVisibility(View.INVISIBLE);

                    PhoneAuthProvider.getInstance().verifyPhoneNumber(
                            "+91"+mobileno.getText().toString(),
                            60,
                            TimeUnit.SECONDS,
                            otpVerification.this,
                            new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                @Override
                                public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                                    progressBar.setVisibility(View.GONE);
                                    btngetotp.setVisibility(View.VISIBLE);

                                }

                                @Override
                                public void onVerificationFailed(@NonNull FirebaseException e) {

                                    progressBar.setVisibility(View.GONE);
                                    btngetotp.setVisibility(View.VISIBLE);
                                    Toast.makeText(otpVerification.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onCodeSent(@NonNull String otp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {

                                    progressBar.setVisibility(View.GONE);
                                    btngetotp.setVisibility(View.VISIBLE);
                                    Intent intent= new Intent(getApplicationContext(),putOtp.class);
                                    intent.putExtra("mobileno",mobileno.getText().toString());
                                    intent.putExtra("otp",otp);
                                    startActivity(intent);
                                    finish();



                                }
                            }
                    );
                    //Intent intent=new Intent(getApplicationContext(),putOtp.class);
                    //intent.putExtra("mobileno",mobileno.getText().toString());
                    //startActivity(intent);
                }else {
                    Toast.makeText(otpVerification.this, "Invalid Mobile No.", Toast.LENGTH_SHORT).show();
                }
            }
            else{
                Toast.makeText(otpVerification.this, "Enter Mobile No.", Toast.LENGTH_SHORT).show();
            }
        });

    }
}