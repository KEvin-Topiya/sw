package com.example.sweet_wave.adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.sweet_wave.R;
import com.example.sweet_wave.fragements.Home_frag;

import java.util.ArrayList;
import java.util.List;

public class rcAdapter extends RecyclerView.Adapter<rcAdapter.ViewHolder>{
    Context context;
    ArrayList<ProductStructure> arrl;
    public rcAdapter(Context context,ArrayList<ProductStructure> arrl){
        this.context=context;
        this.arrl=arrl;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.product_list_design,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        holder.img.setImageResource(arrl.get(position).img);


        Glide.with(context)
                .load(arrl.get(position).img)
                .into(holder.img);
        ;
        holder.name.setText(arrl.get(position).nm);
        holder.price.setText(arrl.get(position).ps);
    }

    @Override
    public int getItemCount() {
        return arrl.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView name,price;
        ImageView img;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.pname);
            price=itemView.findViewById(R.id.price);
            img=itemView.findViewById(R.id.img_rc);
        }

    }}