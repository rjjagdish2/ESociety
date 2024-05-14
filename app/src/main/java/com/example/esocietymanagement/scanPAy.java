package com.example.esocietymanagement;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


import java.io.File;
import java.util.UUID;

public class scanPAy extends AppCompatActivity {

    Button btn,upbtn;
    ImageView profilePic;
    ProgressDialog progressDialog;
    public Uri imageUri;
    private FirebaseStorage storage;
    private StorageReference storageReference,storageReference2;
    DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference("Images");
    int reqCode=1;

    String scode,mobile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_pay);

        btn=findViewById(R.id.qrBtn);
        upbtn=findViewById(R.id.btnUpload);
        profilePic=findViewById(R.id.qrImage);




        SharedPreferences preferences = getSharedPreferences("login", MODE_PRIVATE);
        //String mobileno=preferences.getString("mobile",null);
        scode=preferences.getString("scode",null);
        mobile = preferences.getString("mobile", null);

       storageReference =FirebaseStorage.getInstance().getReference();
       storageReference2 =FirebaseStorage.getInstance().getReference().child(scode);
        progressDialog=new ProgressDialog(scanPAy.this);
        progressDialog.setMessage("Fetching QR");
        //progressDialog.setCancelable(false);
        progressDialog.show();
        try{
            final File local=File.createTempFile(scode+"imagecode","*");
            storageReference2.getFile(local).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    if(progressDialog.isShowing()){
                        progressDialog.dismiss();
                        Bitmap bit= BitmapFactory.decodeFile(local.getAbsolutePath());
                        profilePic.setImageBitmap(bit);
                    }

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(scanPAy.this, "Please Upload QR", Toast.LENGTH_SHORT).show();
                    //Toast.makeText(scanPAy.this, scode, Toast.LENGTH_SHORT).show();
                }
            });
        }catch (Exception e){
            Toast.makeText(scanPAy.this, "Please Upload QR", Toast.LENGTH_SHORT).show();
            Toast.makeText(scanPAy.this, scode, Toast.LENGTH_SHORT).show();
        }

        ActivityResultLauncher<Intent> activityResultLauncher=registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode()== Activity.RESULT_OK){
                            Intent data=result.getData();
                            imageUri=data.getData();
                            profilePic.setImageURI(imageUri);
                        }
                        else
                        {
                            upbtn.setVisibility(View.INVISIBLE);
                            Toast.makeText(scanPAy.this, "Please Select QR", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );

        DatabaseReference chkadmin=FirebaseDatabase.getInstance().getReference("user");

        chkadmin.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String chkad = snapshot.child(mobile).child("society").child(scode).child("admin").getValue(String.class);

                if (chkad.equals("admin")) {

                }
                else
                {
                    btn.setVisibility(View.INVISIBLE);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


            //databaseReference= FirebaseDatabase.getInstance().getReference("society").child();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                upbtn.setVisibility(View.VISIBLE);
                Intent intent=new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");

                //startActivityForResult(intent,1);
                activityResultLauncher.launch(intent);
            }

        });
        upbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(imageUri!=null){
                    uploadQr(imageUri);

                }else{
                    Toast.makeText(scanPAy.this, "No", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void uploadQr(Uri imageUri) {
        final StorageReference imageRef=storageReference.child(scode);

        imageRef.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                imageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        DataClass dataClass=new DataClass(imageUri.toString()+"imagecode");
                        //String key=databaseReference.push().getKey();
                        databaseReference.setValue(dataClass);
                        upbtn.setVisibility(View.INVISIBLE);
                        Toast.makeText(scanPAy.this, "Uploaded", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }


}
