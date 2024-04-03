package com.example.sweet_wave.adapter;


import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;

import com.bumptech.glide.Glide;
import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.sweet_wave.Home;
import com.example.sweet_wave.R;
import com.example.sweet_wave.fragements.Home_frag;
import com.example.sweet_wave.fragements.cart_frag;
import com.example.sweet_wave.fragements.plist;

import java.util.*;

public class cartAdapter extends RecyclerView.Adapter<cartAdapter.ViewHolder> {

    Context context,act;
    ArrayList<cart> arrl;
    sqlLiteHelper db;

    public cartAdapter(Context context, ArrayList<cart> arrl,Context act) {
        this.context = context;
        this.arrl = arrl;
        this.act=act;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cart_design, parent, false);
        return new com.example.sweet_wave.adapter.cartAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        Glide.with(context)
                .load(arrl.get(position).Img)
                .into(holder.img);

        holder.name.setText(arrl.get(position).Name);
        holder.price.setText(""+arrl.get(position).Price);
        holder.qty.setText(""+arrl.get(position).Qty);

        holder.rm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 db=new sqlLiteHelper(context);
                 if(db.deleteRow(arrl.get(position).Id)){
                Toast.makeText(context, "remove", Toast.LENGTH_SHORT).show();


                     AppCompatActivity activity= (AppCompatActivity)v.getContext();
                     cart_frag p=new cart_frag();
                     activity.getSupportFragmentManager().beginTransaction().replace(R.id.container,p).addToBackStack(null).commit();
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return arrl.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView name, price, qty,rm;
        ImageView img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.prc);
            img = itemView.findViewById(R.id.img);
            qty = itemView.findViewById(R.id.qty);
            rm = itemView.findViewById(R.id.rm);


        }
    }
}
