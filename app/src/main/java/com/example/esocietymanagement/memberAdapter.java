package com.example.esocietymanagement;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class memberAdapter extends RecyclerView.Adapter<memberAdapter.MyViewHolder> {

    List<memberHelper> item;
    Context context;

    public memberAdapter(List<memberHelper> item, Context context) {
        this.item = item;
        this.context = context;
    }

    @NonNull
    @Override
    public memberAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new memberAdapter.MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.member_content,null));
    }

    @Override
    public void onBindViewHolder(@NonNull memberAdapter.MyViewHolder holder, int position) {

        memberHelper memberHelper=item.get(position);

        holder.name.setText(memberHelper.getName());
        holder.flatno.setText(memberHelper.getFlatno());
    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView flatno,name;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            flatno=itemView.findViewById(R.id.mFlatno);
            name=itemView.findViewById(R.id.mName);

        }
    }
}


