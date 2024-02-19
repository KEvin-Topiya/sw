package com.example.sweet_wave.fragements;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sweet_wave.R;


public class Home_frag extends Fragment {
    private  static final  String arg1="Argument1";
    private  static final  String arg2="Argument2";
    public Home_frag() {
        // Required empty public constructor
    }
    public  static  Home_frag getInstance(String value1,int value2){

        Home_frag homeFrag=new Home_frag();
        Bundle bundle=new Bundle();
        bundle.putString(arg1,value1);
        bundle.putInt(arg2,value2);
        homeFrag.setArguments(bundle);

        return new Home_frag();
    }
        @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

            if(getArguments()!=null){
                String name=getArguments().getString(arg1);
                int id=getArguments().getInt(arg2);
                Log.d("valu from argument","Name:"+name+" Roll No:"+id);
            }


        return inflater.inflate(R.layout.fragment_home_frag, container, false);
    }
}