package com.example.sweet_wave.fragements;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.DocumentsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sweet_wave.Home;
import com.example.sweet_wave.R;
import com.example.sweet_wave.adapter.*;

import java.util.ArrayList;


public class Home_frag extends Fragment {


    ArrayList<ProductStructure> data=new ArrayList<>();

    Context context;
    public Home_frag() {
        // Required empty public constructor
    }

        @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

                View view=inflater.inflate(R.layout.fragment_home_frag,container,false);
            RecyclerView rc= view.findViewById(R.id.product);

            context = container.getContext();



            data.add(new ProductStructure("AbC","232523"));
            data.add(new ProductStructure("AbC","232523"));
            data.add(new ProductStructure("AbC","232523"));
            data.add(new ProductStructure("AbC","232523"));
            data.add(new ProductStructure("AbC","232523"));
            data.add(new ProductStructure("AbC","232523"));
            data.add(new ProductStructure("AbC","232523"));
            data.add(new ProductStructure("AbC","232523"));
            data.add(new ProductStructure("AbC","232523"));
            data.add(new ProductStructure("AbC","232523"));
            data.add(new ProductStructure("AbC","232523"));
            data.add(new ProductStructure("AbC","232523"));


            rc.setLayoutManager(new GridLayoutManager(view.getContext(),2));
            rcAdapter r=new rcAdapter(context, data);
            rc.setAdapter(r);

        return view;
    }
}