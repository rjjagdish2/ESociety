package com.example.esocietymanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Constraints;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.lang.reflect.Type;

public class flatView extends AppCompatActivity {

    LinearLayout layout;

    private Context context;
    LayoutInflater layoutInflater;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flat_view);

        layout= findViewById(R.id.layout);

        /*Intent intent=getIntent();
        int totalflat=intent.getIntExtra("keynumber",0);
        int totalfloorflat=intent.getIntExtra("keyname",0);*/

        //int floor=totalflat/totalfloorflat;


        for(int i=1;i<=20;i++)
        {

            for(int j=1;j<=4;j++)
            {

                TextView textView=new TextView(this);
                textView.setText(i+"   " +j);
                textView.setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));
                textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
                textView.setBackgroundResource(R.color.purple_200);
                textView.setGravity(Gravity.CENTER);
                layout.setGravity(Gravity.CENTER);
                layout.addView(textView);
                textView.setPadding(0,5,0,5);

            }


        }




    }
}