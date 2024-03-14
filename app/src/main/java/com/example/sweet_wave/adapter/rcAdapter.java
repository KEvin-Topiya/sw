package com.example.sweet_wave.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sweet_wave.R;
import com.example.sweet_wave.fragements.Home_frag;

import java.util.ArrayList;

public class rcAdapter extends RecyclerView.Adapter<rcAdapter.ViewHolder>{
Context context;
ArrayList<ProductStructure> data;
    public rcAdapter(Context context , ArrayList<ProductStructure> data){
        this.context=context;
        this.data=data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.product_list_design,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.un.setText(data.get(position).nm);
        holder.ps.setText(data.get(position).ps);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
TextView un,ps;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            un=itemView.findViewById(R.id.uname);
            ps=itemView.findViewById(R.id.upass);
        }
    }

}
/*
 private List<String> mData;

        public rcAdapter(List<String> data) {
            this.mData = data;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_list_design, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            String item = mData.get(position);
            holder.textViewName.setText(item);
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        public static class ViewHolder extends RecyclerView.ViewHolder {
            TextView textViewName;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                textViewName = itemView.findViewById(R.id.textViewName);
            }
        }
    }
 */