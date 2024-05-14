package com.example.esocietymanagement;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class dailyWAdapter extends RecyclerView.Adapter<dailyWAdapter.MyViewHolder>{

    List<workerHelper> item;
    Context context;

    public dailyWAdapter(List<workerHelper> workerHelperList, Context context) {
        this.item = workerHelperList;
        this.context = context;
    }



    @NonNull
    @Override
    public dailyWAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_worker_content, null));
    }

    @Override
    public void onBindViewHolder(@NonNull dailyWAdapter.MyViewHolder holder, int position) {

        workerHelper workerHelper=item.get(position);

        holder.name.setText(workerHelper.getName());
        holder.profession.setText(workerHelper.getProfession());
        holder.mobile.setText(workerHelper.getMobile());

        holder.btncall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phno= workerHelper.getMobile();
                String call="tel:"+phno.trim();
                Intent intent=new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse(call));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name,profession,mobile;
        Button btncall;
        public MyViewHolder(View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.getwname);
            profession=itemView.findViewById(R.id.getwpro);
            mobile=itemView.findViewById(R.id.getwmobile);

            btncall=itemView.findViewById(R.id.call);
        }
    }
}
