package com.example.esocietymanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class mainRules extends AppCompatActivity {


    LinearLayout linearLayout;
    ImageButton btnAdd;
    FirebaseDatabase firebaseDatabase;
    Button okBtn;
    String scodep,mobile;
    DatabaseReference databaseReference;

    ArrayList<String> ruleHalper=new ArrayList<String>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_rules);

        linearLayout=findViewById(R.id.addlinear);
        btnAdd=findViewById(R.id.addBtn);
        okBtn=findViewById(R.id.okBtn);




        SharedPreferences preferences = getSharedPreferences("login", MODE_PRIVATE);
        scodep = preferences.getString("scode", null);
        mobile = preferences.getString("mobile", null);


        display();


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference chkadmin=FirebaseDatabase.getInstance().getReference("user");
                chkadmin.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String chkad=snapshot.child(mobile).child("society").child(scodep).child("admin").getValue(String.class);

                        if(chkad.equals("admin")){
                            addView();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });


        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseReference chkadmin=FirebaseDatabase.getInstance().getReference("user");
                chkadmin.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String chkad=snapshot.child(mobile).child("society").child(scodep).child("admin").getValue(String.class);

                        if(chkad.equals("admin")){
                            storeRules();

                            Intent intent=new Intent(getApplicationContext(),userDashbord.class);
                            startActivity(intent);
                            finish();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


            }
        });

    }

    private void storeRules() {

        ruleHalper.clear();
        databaseReference = FirebaseDatabase.getInstance().getReference("society").child(scodep).child("rules");
        Task<Void> mTask = databaseReference.removeValue();

        boolean result = true;


        int i;
        for (i = 0; i < linearLayout.getChildCount(); i++) {

            View view = linearLayout.getChildAt(i);
            EditText ruleeditName = (EditText) view.findViewById(R.id.editRule);
            String name = ruleeditName.getText().toString();
            com.example.esocietymanagement.ruleHalper halper = new ruleHalper(name);
            databaseReference.child("r" + i).setValue(halper);


        }


    }

    private void addView(){
        View view=getLayoutInflater().inflate(R.layout.activity_rule_content,null);

         EditText ruleedit=(EditText)view.findViewById(R.id.editRule);
         ImageButton clearBtn= (ImageButton) view.findViewById(R.id.clearRule);

        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference chkadmin=FirebaseDatabase.getInstance().getReference("user");
                chkadmin.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String chkad=snapshot.child(mobile).child("society").child(scodep).child("admin").getValue(String.class);

                        if(chkad.equals("admin")){
                            removeView(view);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


            }
        });

        linearLayout.addView(view);
    }
    private void removeView(View view){

        linearLayout.removeView(view);
    }

    private void display(){

        FirebaseDatabase firebaseDatabase1=FirebaseDatabase.getInstance();
        DatabaseReference datarule=firebaseDatabase1.getReference("society").child(scodep).child("rules");

        datarule.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int counter=(int) snapshot.getChildrenCount();

                for (int i=0;i<counter;i++){

                    String rn=snapshot.child("r"+i).child("ruleName").getValue(String.class);
                    View view=getLayoutInflater().inflate(R.layout.activity_rule_content,null);

                    EditText ruleedit=(EditText)view.findViewById(R.id.editRule);
                    ImageButton clearBtn= (ImageButton) view.findViewById(R.id.clearRule);
                    linearLayout.addView(view);
                    ruleedit.setText(rn);

                    clearBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            removeView(view);
                        }
                    });

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}