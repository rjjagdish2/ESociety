package com.example.esocietymanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class putOtp extends AppCompatActivity {

    EditText otpb1, otpb2, otpb3, otpb4, otpb5, otpb6;
    TextView sharemobileno,resendotp;
    Button btnverify;
    ProgressBar progressBar;
    private String otpcode;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_put_otp);

        otpb1 = findViewById(R.id.otpbox1);
        otpb2 = findViewById(R.id.otpbox2);
        otpb3 = findViewById(R.id.otpbox3);
        otpb4 = findViewById(R.id.otpbox4);
        otpb5 = findViewById(R.id.otpbox5);
        otpb6 = findViewById(R.id.otpbox6);
        sharemobileno = findViewById(R.id.shareMobileno);
        btnverify = findViewById(R.id.btnverOtp);
        progressBar = findViewById(R.id.progressbarverify);
        resendotp=findViewById(R.id.resendotp);


        String tempMono=getIntent().getStringExtra("mobileno");

        sharemobileno.setText(tempMono);
        otpcode = getIntent().getStringExtra("otp");

        btnverify.setOnClickListener(v -> {
            if (!otpb1.getText().toString().trim().isEmpty() && !otpb2.getText().toString().trim().isEmpty() && !otpb3.getText().toString().trim().isEmpty() && !otpb4.getText().toString().trim().isEmpty() && !otpb5.getText().toString().trim().isEmpty() && !otpb6.getText().toString().trim().isEmpty()) {

                String tempotp = otpb1.getText().toString() +
                        otpb2.getText().toString() +
                        otpb3.getText().toString() +
                        otpb4.getText().toString() +
                        otpb5.getText().toString() +
                        otpb6.getText().toString();

                if (!(tempotp == null)) {
                    progressBar.setVisibility(View.VISIBLE);
                    btnverify.setVisibility(View.INVISIBLE);

                    PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(
                            otpcode, tempotp
                    );
                    FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                            .addOnCompleteListener(task -> {
                                progressBar.setVisibility(View.GONE);
                                btnverify.setVisibility(View.VISIBLE);
                                if (task.isSuccessful()) {



                                    Intent intent = new Intent(getApplicationContext(), register_screen.class);
                                    intent.putExtra("tempMono",tempMono);
                                    startActivity(intent);
                                    finish();

                                } else {
                                    Toast.makeText(putOtp.this, "Enter OTP", Toast.LENGTH_SHORT).show();
                                }
                            });
                } else {
                    Toast.makeText(putOtp.this, "Please Enter All Digits", Toast.LENGTH_SHORT).show();
                }


                Toast.makeText(putOtp.this, "OTP Verified", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(putOtp.this, "Please Enter All Digits", Toast.LENGTH_SHORT).show();
            }
        });

        numberOtpmove();
resendotp.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+91"+getIntent().getStringExtra("mobileno"),
                60,
                TimeUnit.SECONDS,
                putOtp.this,
                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {


                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {


                        Toast.makeText(putOtp.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCodeSent(@NonNull String newotp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {

                        otpcode=newotp;
                        Toast.makeText(putOtp.this, "OTP Resent Successfully", Toast.LENGTH_SHORT).show();


                    }
                }
        );
    }
});

    }

    private void numberOtpmove() {

        otpb1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (!s.toString().trim().isEmpty()) {
                    otpb2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        otpb2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (!s.toString().trim().isEmpty()) {
                    otpb3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        otpb3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (!s.toString().trim().isEmpty()) {
                    otpb4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        otpb4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (!s.toString().trim().isEmpty()) {
                    otpb5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        otpb5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (!s.toString().trim().isEmpty()) {
                    otpb6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


}