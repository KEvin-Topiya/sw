package com.example.sweet_wave.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.sweet_wave.R;
import com.example.sweet_wave.fragements.plist;

import java.util.ArrayList;
import java.util.HashMap;

public class other {

    SharedPreferences sp;
    SharedPreferences.Editor editor;

    Context context;

    public other(Context context){
        this.context=context;
    }
    public void setSP(String table,HashMap<String,String> d){
        HashMap<String,String> data = new HashMap<>();
        data=d;
        StringBuilder b = new StringBuilder();
       sp = context.getSharedPreferences(""+table, Context.MODE_PRIVATE);
       editor= sp.edit();
        for (String s : data.keySet()) {
            editor.putString(s, data.get(s));
            if(b.length() != 0){
                b.append(",");
            }
            b.append(s);
        }
        editor.putString("KEYS_OF_"+table,b.toString());

        editor.apply();

    }

    public HashMap<String,String> getallSP(String table){
        HashMap<String,String> data = new HashMap<>();
        sp = context.getSharedPreferences(""+table, Context.MODE_PRIVATE);

        String[] keys = sp.getString("KEYS_OF_"+table,"").split(",");
        for (int i = 0; i < keys.length; i++) {
            data.put(keys[i], sp.getString(keys[i],""));
        }
        return data;
    }
    public String getSp(String table,String key){
        String data="";
        sp = context.getSharedPreferences(""+table,Context.MODE_PRIVATE);

            data= sp.getString(""+key,"");
        return data;
    }

}
