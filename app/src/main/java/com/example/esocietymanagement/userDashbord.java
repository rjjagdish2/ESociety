package com.example.esocietymanagement;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Notification;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.sql.Array;
import java.time.Instant;
import java.util.ArrayList;

public class userDashbord extends AppCompatActivity {

    TextView txtmaeq,txtwlc;
    Button btnRule,btnWorker,btnExp,btnGarden,btnInc,btnpay,btnmain,btnhall,btnmember,btnDailyw;
    Boolean checkF=false;
    String martxt;
    private  Boolean oncebackpress=false;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;



    @Override
    public void onBackPressed() {

        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            if(oncebackpress){
                super.onBackPressed();
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
                return;

            }
            Toast.makeText(this, "Press Again To Exit", Toast.LENGTH_SHORT).show();
            oncebackpress=true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    oncebackpress=false;
                }
            },2000);
        }
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashbord);

        txtmaeq=findViewById(R.id.txtmarqu);
        btnWorker=findViewById(R.id.workerBtn);
        txtmaeq.setSelected(true);
        btnRule=findViewById(R.id.rulsBtn);
        txtwlc=findViewById(R.id.txtwlc);
        btnExp=findViewById(R.id.expBtn);
        btnGarden=findViewById(R.id.gardenBtn);
        btnmember=findViewById(R.id.elecBtn);
        btnInc=findViewById(R.id.incomeBtn);
        btnpay=findViewById(R.id.scanPayBtn);
        btnmain=findViewById(R.id.maintainanceBtn);
        btnhall=findViewById(R.id.hallBtn);
        toolbar=findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        drawerLayout=findViewById(R.id.headdrawable);
        navigationView=findViewById(R.id.nevigation);
        btnDailyw=findViewById(R.id.dailyworkerBtn);



        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        SharedPreferences preferences = getSharedPreferences("login", MODE_PRIVATE);
        String code = preferences.getString("mobile", null);
        String scode = preferences.getString("scode", null);
        navigationView.bringToFront();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id=item.getItemId();
                drawerLayout.closeDrawer(GravityCompat.START);

                switch (id)
                {
                    case R.id.homemenu:

                        Intent intent=new Intent(getApplicationContext(),UserProfile.class);
                        startActivity(intent);
                        break;

                    case R.id.societydetails:

                        Intent intent1=new Intent(getApplicationContext(),society_details_view.class);
                        startActivity(intent1);
                        break;

                    case R.id.share:
                        Intent sharec=new Intent();
                        sharec.setAction(Intent.ACTION_SEND);
                        sharec.putExtra(Intent.EXTRA_TEXT,"This is Our Society Code: "+scode);
                        sharec.setType("text/plain");
                        Intent shareda=Intent.createChooser(sharec,null);
                        startActivity(shareda);
                        break;

                    case R.id.leavesociety:

                            AlertDialog.Builder builder=new AlertDialog.Builder(userDashbord.this);
                            builder.setTitle("Leave Society");
                            builder.setMessage("Are you want to Leave Society?");
                            builder.setPositiveButton("Leave", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    SharedPreferences preferences=getSharedPreferences("login",MODE_PRIVATE);
                                    SharedPreferences.Editor editor=preferences.edit();
                                    editor.putString("scode", null);
                                    editor.apply();

                                    Intent ileave=new Intent(userDashbord.this,FirstScreen.class);
                                    startActivity(ileave);
                                    finish();
                                }
                            }).setNegativeButton("Stay",null).show();

                        break;

                    case R.id.logout:
                        AlertDialog.Builder builderlogout=new AlertDialog.Builder(userDashbord.this);
                        builderlogout.setTitle("Logout");
                        builderlogout.setMessage("Are you want to Logout?");
                        builderlogout.setPositiveButton("Logout", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                SharedPreferences preferences=getSharedPreferences("login",MODE_PRIVATE);
                                SharedPreferences.Editor editor=preferences.edit();
                                editor.putString("mobile", null);
                                editor.putString("scode", null);
                                editor.putBoolean("flag", false);
                                editor.apply();

                                Intent ilogout=new Intent(userDashbord.this,wlcScreen.class);
                                startActivity(ilogout);
                                finish();
                            }
                        }).setNegativeButton("No",null).show();

                        break;

                    default:
                        return true;
                }
                return true;

            }
        });





        DatabaseReference dbrmarq =FirebaseDatabase.getInstance().getReference("society");
        dbrmarq.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String setMarquee=snapshot.child(scode).child("marquee").child("marquee").getValue(String.class);
                txtmaeq.setText(setMarquee);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        txtmaeq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference chkadmin=FirebaseDatabase.getInstance().getReference("user");
                chkadmin.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String chkad=snapshot.child(code).child("society").child(scode).child("admin").getValue(String.class);

                        if(chkad.equals("admin")){
                            Intent intent=new Intent(getApplicationContext(),changeMarq.class);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });





        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference databaseReference=firebaseDatabase.getReference("user");

        Query checkusername=databaseReference.child(code).orderByChild("mono").equalTo(code);
        checkusername.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String getname=snapshot.child(code).child("fname").getValue(String.class);
                txtwlc.setText("Hi, "+getname);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        btnGarden.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),gardenEqp.class);
                startActivity(intent);
            }
        });

        btnRule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getApplicationContext(),mainRules.class);
                startActivity(intent);
            }
        });

        btnWorker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),workerList.class);
                startActivity(intent);
            }
        });

        btnExp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),expence.class);
                startActivity(intent);
            }
        });

        btnInc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),income.class);
                startActivity(intent);
            }
        });

        btnpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),scanPAy.class);
                startActivity(intent);
            }
        });


        btnmain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });

        btnhall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),hallBook.class);
                startActivity(intent);
            }
        });


        btnmember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),members.class);
                startActivity(intent);
            }
        });


        btnDailyw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),dailyWorkersList.class);
                startActivity(intent);
            }
        });


    }

}
