package com.example.esocietymanagement;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class incAdapter extends RecyclerView.Adapter<incAdapter.MyViewHolder> {

    List<incHalper> item;
    Context context;

    public incAdapter(List<incHalper> item, Context context) {
        this.item = item;
        this.context = context;
    }

    @NonNull
    @Override
    public incAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.incomecontent,null));
    }

    @Override
    public void onBindViewHolder(@NonNull incAdapter.MyViewHolder holder, int position) {

        incHalper incHalper=item.get(position);

        holder.date.setText(incHalper.getDay());
        holder.title.setText(incHalper.getName());
        holder.amount.setText(incHalper.getAmt());
    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView date,title,amount;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            date=itemView.findViewById(R.id.eDate);
            title=itemView.findViewById(R.id.eTitle);
            amount=itemView.findViewById(R.id.eAmt);

        }
    }
}

