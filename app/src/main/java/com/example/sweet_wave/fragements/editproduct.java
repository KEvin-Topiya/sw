package com.example.sweet_wave.fragements;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.sweet_wave.R;
import com.example.sweet_wave.adapter.other;
import com.example.sweet_wave.databinding.FragmentAddProductBinding;
import com.example.sweet_wave.databinding.FragmentEditproductBinding;
import com.example.sweet_wave.firebase.addToFirebase;
import com.example.sweet_wave.fragements.Home_frag;

import java.util.HashMap;

public class editproduct extends Fragment {

    FragmentEditproductBinding av;
    SharedPreferences s;
    private static final int PICK_IMAGE_REQUEST = 1;
    Bitmap image;
    String picturePath;
    Uri filePath;
    Spinner sp;
    public editproduct() {
        // Required empty public constructor
    }
    String id,i;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Context context = container.getContext();
        av = FragmentEditproductBinding.inflate(inflater, container, false);
        String[] categ = { "Cake","Tart","Bread"};
        ArrayAdapter<String> cate=new ArrayAdapter<>(context, android.R.layout.simple_spinner_item,categ);
        av.cat.setAdapter(cate);
        other o=new other(context);

        s=context.getSharedPreferences("Product",MODE_PRIVATE);
        id=s.getString("Id","");

        av.pname.setText(s.getString("Name",""));
        av.price.setText(s.getString("Price",""));
        av.desc.setText(s.getString("Dec",""));
        i=""+s.getString("Img","");
        av.addp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                addToFirebase a=new addToFirebase();
                HashMap<String,String> product = new HashMap<>();
                product.put("Id", id);
                product.put("Name",av.pname.getText().toString());
                product.put("Category",av.cat.getSelectedItem().toString());
                product.put("Price",av.price.getText().toString());
                product.put("Desc",av.desc.getText().toString());
                product.put("Img",i);
a.addtofirebae("Products",product,""+id);
                AppCompatActivity activity= (AppCompatActivity) v.getContext();
                Home_frag h=new Home_frag();
                a.getp(context);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.container,h).addToBackStack(null).commit();

            }
        });

        return av.getRoot();
    }



}