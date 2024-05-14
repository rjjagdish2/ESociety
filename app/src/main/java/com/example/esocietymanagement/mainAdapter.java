package com.example.esocietymanagement;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class mainAdapter extends RecyclerView.Adapter<mainAdapter.MyViewHolder>{

        List<mainHelper> item;
        Context context;

    public mainAdapter(List<mainHelper> item, Context context) {
        this.item = item;
        this.context = context;
    }

    @NonNull
    @Override
    public mainAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new mainAdapter.MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.main_content,null));
    }

    @Override
    public void onBindViewHolder(@NonNull mainAdapter.MyViewHolder holder, int position) {

        mainHelper mainHelper=item.get(position);

        holder.date.setText(mainHelper.getDay());
        holder.title.setText(mainHelper.getTitle());
        holder.amount.setText(mainHelper.getAmt());
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
