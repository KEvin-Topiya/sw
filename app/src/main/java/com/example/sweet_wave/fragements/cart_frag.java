package com.example.sweet_wave.fragements;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.sweet_wave.R;
import com.example.sweet_wave.adapter.ProductStructure;
import com.example.sweet_wave.adapter.*;
import com.example.sweet_wave.databinding.FragmentCartFragBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


public class cart_frag extends Fragment {

    TextView a;

    RecyclerView rc;
    sqlLiteHelper db;
    ArrayList<cart> ac;
    AppCompatButton order;

    public MeowBottomNavigation bottomNavigation;

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_cart_frag, container, false);
        Context context=container.getContext();

        top_bar t=new top_bar();
//        t.crt.setColorFilter(getResources().getColor(R.color.red));

        bottomNavigation = requireActivity().findViewById(R.id.nav);
        bottomNavigation.show(0,true);
        order=view.findViewById(R.id.ord);
        a=view.findViewById(R.id.niproduct);
        Context act=getActivity();

         rc=view.findViewById(R.id.cartlist);
        db=new sqlLiteHelper(context);
        ac=new ArrayList<>();
        ac=db.selectAll();
        if(ac.size()==0){
a.setText("Cart is empty");
        }

        cartAdapter rca=new cartAdapter(context, ac,act);
        rc.setLayoutManager(new LinearLayoutManager(context));
        rc.setAdapter(rca);



        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity= (AppCompatActivity) v.getContext();
                orderform p=new orderform();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.container,p).addToBackStack(null).commit();


            }
        });

        return view;
    }
}