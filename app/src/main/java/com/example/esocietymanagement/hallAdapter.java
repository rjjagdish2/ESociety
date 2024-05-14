package com.example.esocietymanagement;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class hallAdapter extends RecyclerView.Adapter<hallAdapter.MyViewHolder> {


        List<hallHelper> item;
        Context context;

    public hallAdapter(List<hallHelper> item, Context context) {
        this.item = item;
        this.context = context;
    }


    @NonNull
    @Override
    public hallAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new hallAdapter.MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.hallcontent,null));
    }

    @Override
    public void onBindViewHolder(@NonNull hallAdapter.MyViewHolder holder, int position) {

        hallHelper hallHelper=item.get(position);

        holder.date.setText(hallHelper.getDay());
        holder.title.setText(hallHelper.getReason());
        holder.amount.setText(hallHelper.getName());
    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView date,title,amount;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            date=itemView.findViewById(R.id.eDate);
            title=itemView.findViewById(R.id.eTitle);
            amount=itemView.findViewById(R.id.eAmt);

        }
    }
}
