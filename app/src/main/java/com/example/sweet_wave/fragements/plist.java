package com.example.sweet_wave.fragements;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.sweet_wave.*;
import com.example.sweet_wave.firebase.addToFirebase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;

public class plist extends Fragment {

    public plist() {
        // Required empty public constructor
    }
    Button buy;
    ImageView img;
    addToFirebase a;
    TextView title,prc,dec;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_plist,container,false);
        Context context = container.getContext();

        title =view.findViewById(R.id.ptitle);
        img=view.findViewById(R.id.pimg);
        prc=view.findViewById(R.id.price);
        dec=view.findViewById(R.id.Dec);
        buy=view.findViewById(R.id.Buy);

        String t="",p="",i="",d="";
        Uri im;
        sp=context.getSharedPreferences("Product",MODE_PRIVATE);
        t=""+sp.getString("Name","");
        p=""+sp.getString("Price","");
        i=""+sp.getString("Img","");
        d=""+sp.getString("Dec","");
        im= Uri.parse(i);

        title.setText("\nName:"+t);
        dec.setText("\nDescription:\n"+d);
        prc.setText("\nPrice"+p+" $");
        Glide.with(context).load(i).into(img);
        return view;
    }
}