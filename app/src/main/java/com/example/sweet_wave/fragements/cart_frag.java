package com.example.sweet_wave.fragements;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.sweet_wave.R;
import com.example.sweet_wave.adapter.ProductStructure;
import com.example.sweet_wave.adapter.*;
import com.example.sweet_wave.databinding.FragmentCartFragBinding;

import java.util.ArrayList;


public class cart_frag extends Fragment {


    RecyclerView rc;
    sqlLiteHelper db;
    ArrayList<cart> ac;



    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_cart_frag, container, false);
        Context context=container.getContext();


        Context act=getActivity();

         rc=view.findViewById(R.id.cartlist);
        db=new sqlLiteHelper(context);
        ac=new ArrayList<>();
        ac=db.selectAll();

        cartAdapter rca=new cartAdapter(context, ac,act);
        rc.setLayoutManager(new LinearLayoutManager(context));
        rc.setAdapter(rca);

        return view;
    }
}