package com.example.sweet_wave.adapter;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.sweet_wave.Home;
import com.example.sweet_wave.R;
import com.example.sweet_wave.fragements.Home_frag;
import com.example.sweet_wave.fragements.plist;

import java.util.ArrayList;
import java.util.List;

public class rcAdapter extends RecyclerView.Adapter<rcAdapter.ViewHolder> {
    Context context;
    ArrayList<ProductStructure> arrl;

    SharedPreferences sp;
    SharedPreferences.Editor editor;

    public rcAdapter(Context context, ArrayList<ProductStructure> arrl) {
        this.context = context;
        this.arrl = arrl;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.product_list_design, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
//        holder.img.setImageResource(arrl.get(position).img);


        Glide.with(context)
                .load(arrl.get(position).img)
                .into(holder.img);

        holder.name.setText(arrl.get(position).nm);
        holder.price.setText(arrl.get(position).ps);

        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sp=context.getSharedPreferences("Product",MODE_PRIVATE);
                editor = sp.edit();

//                editor.putString("page", "new plist()");

                String temp=""+arrl.get(position).id;
                editor.putString("Id",temp );
                editor.putString("Name", arrl.get(position).nm);
                editor.putString("Price", arrl.get(position).ps);
                editor.putString("Category", arrl.get(position).cat);
                editor.putString("Img", arrl.get(position).img);
                editor.putString("Dec", arrl.get(position).des);
                editor.apply();

                AppCompatActivity activity= (AppCompatActivity) v.getContext();
                plist p=new plist();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.container,p).addToBackStack(null).commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrl.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView name, price ;
        ImageView img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.pname);
            price = itemView.findViewById(R.id.price);
            img = itemView.findViewById(R.id.img_rc);


        }

    }


}