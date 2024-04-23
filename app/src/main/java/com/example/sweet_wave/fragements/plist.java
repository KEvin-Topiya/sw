package com.example.sweet_wave.fragements;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sweet_wave.*;
import com.example.sweet_wave.adapter.other;
import com.example.sweet_wave.adapter.sqlLiteHelper;
import com.example.sweet_wave.firebase.addToFirebase;

public class plist extends Fragment {

    public plist() {
        // Required empty public constructor
    }
    int x;
    String t="",p="",i="",d="",c="",id="";
    Uri im;
    Button buy;
    AppCompatButton ad,rm,dlt,edt;

    ImageView img;
    addToFirebase a;
    TextView title,prc,dec,qt,cat;
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
        ad=view.findViewById(R.id.add);
        rm=view.findViewById(R.id.remove);
        qt=view.findViewById(R.id.qty);
        cat=view.findViewById(R.id.cat);
        dlt=view.findViewById(R.id.dlt);
        edt=view.findViewById(R.id.edt);


        other o=new other(context);
        dlt.setVisibility(View.INVISIBLE);
        edt.setVisibility(View.INVISIBLE);
            buy.setVisibility(View.VISIBLE);
        if(o.getSp("SW","login").equals("9")){
            dlt.setVisibility(View.VISIBLE);
            edt.setVisibility(View.VISIBLE);
            buy.setVisibility(View.INVISIBLE);
        }

         x=1;



        ad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                x++;
                qt.setText(""+x);
            }
        });
        rm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(x>1) x--;
                qt.setText(""+x);
            }
        });


        sp=context.getSharedPreferences("Product",MODE_PRIVATE);
        id=sp.getString("Id","");
        t=""+sp.getString("Name","");
        c=""+sp.getString("Category","");
        p=""+sp.getString("Price","");
        i=""+sp.getString("Img","");
        d=""+sp.getString("Dec","");
        im= Uri.parse(i);

        title.setText("Name: "+t);
        dec.setText("\nDescription:\n  "+d+"\n");
        prc.setText("\nPrice: "+p+" ₹");
        cat.setText("Category: "+c);
        Glide.with(context).load(i).into(img);

        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            sqlLiteHelper db=new sqlLiteHelper(context);
            db.addtoCart(Integer.parseInt(id),t,i,c,x,(Integer.parseInt(p)*x));
                AppCompatActivity activity= (AppCompatActivity) v.getContext();
                cart_frag cart=new cart_frag();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.container,cart).addToBackStack(null).commit();
            }
        });
        dlt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToFirebase ab=new addToFirebase();
                ab.delete("Products",id);
                AppCompatActivity activity= (AppCompatActivity)v.getContext();
                Home_frag p=new Home_frag();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.container,p).addToBackStack(null).commit();
            }
        });

        edt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity= (AppCompatActivity) v.getContext();
                editproduct p=new editproduct();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.container,p).addToBackStack(null).commit();

            }
        });
        return view;
    }
}