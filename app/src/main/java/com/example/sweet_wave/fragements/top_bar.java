package com.example.sweet_wave.fragements;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.sweet_wave.R;


public class top_bar extends Fragment {

ImageView crt,add;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_top_bar, container, false);
        crt=view.findViewById(R.id.cart);
        add=view.findViewById(R.id.addp);

        crt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity= (AppCompatActivity)v.getContext();
                cart_frag p=new cart_frag();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.container,p).addToBackStack(null).commit();

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