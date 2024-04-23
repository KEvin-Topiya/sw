package com.example.sweet_wave.fragements;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.sweet_wave.R;
import com.example.sweet_wave.adapter.other;
import com.google.android.material.imageview.ShapeableImageView;


public class top_bar extends Fragment {

ImageView crt,add;
ShapeableImageView usr;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Context context=getActivity();
        View view= inflater.inflate(R.layout.fragment_top_bar, container, false);
        crt=view.findViewById(R.id.cart);
        add=view.findViewById(R.id.addp);
        usr=view.findViewById(R.id.usr);

        usr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity= (AppCompatActivity)v.getContext();
                user_frag p=new user_frag();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.container,p).addToBackStack(null).commit();
            }
        });



        other o=new other(context);
        add.setVisibility(View.INVISIBLE);
        if(o.getSp("SW","login").equals("9"))add.setVisibility(View.VISIBLE);




        crt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

        if(!o.getSp("SW","login").equals("9")){
                AppCompatActivity activity= (AppCompatActivity)v.getContext();
                cart_frag p=new cart_frag();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.container,p).addToBackStack(null).commit();
        }else{
            Toast.makeText(context, "cart not open in admin because it useless for admin", Toast.LENGTH_SHORT).show();
        }

            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity= (AppCompatActivity)v.getContext();
                add_Product p=new add_Product();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.container,p).addToBackStack(null).commit();

            }
        });

        return view;
    }
}