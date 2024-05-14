package com.example.esocietymanagement;

import static android.content.Context.MODE_PRIVATE;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.widget.TintableCheckedTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class expAdapter extends RecyclerView.Adapter<expAdapter.MyViewHolder> {


    List<expHelper> item;
      Context context;


    public expAdapter(List<expHelper> item, Context context) {
        this.item = item;
        this.context = context;
    }



    @NonNull
    @Override
    public expAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_exp_content,null));
    }

    @Override
    public void onBindViewHolder(@NonNull expAdapter.MyViewHolder holder, int position) {

        expHelper expHelper=item.get(position);


        holder.date.setText(expHelper.getDay());
        holder.title.setText(expHelper.getName());
        holder.amount.setText(expHelper.getAmt());
       /* holder.delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                AlertDialog.Builder builderexpdel=new AlertDialog.Builder(context);
                builderexpdel.setTitle("Delete Expense");
                builderexpdel.setMessage("Realy You Want to delete Expense   "+expHelper.getName());
                builderexpdel.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences preferences=context.getSharedPreferences("login",MODE_PRIVATE);
                        String scode=preferences.getString("scode",null);
                        Toast.makeText(context,scode, Toast.LENGTH_SHORT).show();
                        DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference("society");
                        databaseReference.child(scode).child("expense").child(expHelper.getDay().substring(7)).removeValue();
                    }
                }).setNegativeButton("No",null).show();
            }
        });*/

    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

         TextView date,title,amount;
         Button delBtn;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            date=itemView.findViewById(R.id.eDate);
            title=itemView.findViewById(R.id.eTitle);
            amount=itemView.findViewById(R.id.eAmt);
            //delBtn=itemView.findViewById(R.id.btnDel);

        }
    }
}
