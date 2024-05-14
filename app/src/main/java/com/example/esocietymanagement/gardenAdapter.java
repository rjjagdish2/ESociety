package com.example.esocietymanagement;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class gardenAdapter extends RecyclerView.Adapter<gardenAdapter.MyViewHolder> {


    List<gardenHelper> item;
    Context context;

    public gardenAdapter(List<gardenHelper> item, Context context) {
        this.item = item;
        this.context = context;
    }

    @NonNull
    @Override
    public gardenAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.garden_content,null));
    }

    @Override
    public void onBindViewHolder(@NonNull gardenAdapter.MyViewHolder holder, int position) {

        gardenHelper gardenHelper=item.get(position);

        holder.name.setText(gardenHelper.getName());
        holder.price.setText(gardenHelper.getPrice());
        holder.dec.setText(gardenHelper.getDec());
    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {


        TextView name,price,dec;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.gName);
            price=itemView.findViewById(R.id.gPrice);
            dec=itemView.findViewById(R.id.gDec);
        }
    }
}
