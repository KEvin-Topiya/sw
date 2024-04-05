package com.example.sweet_wave.fragements;

import static android.app.Activity.RESULT_OK;

import android.content.Context;
import android.content.Intent;
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
import  com.example.sweet_wave.firebase.*;

import java.util.HashMap;


public class add_Product extends Fragment {
    FragmentAddProductBinding av;
    private static final int PICK_IMAGE_REQUEST = 1;
    Bitmap image;
    String picturePath;
    Uri filePath;
    Spinner sp;

    public add_Product() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Context context = container.getContext();
        av = FragmentAddProductBinding.inflate(inflater, container, false);
        String[] categ = { "Cake","Tart","Bread"};
        ArrayAdapter<String> cate=new ArrayAdapter<>(context, android.R.layout.simple_spinner_item,categ);
        av.cat.setAdapter(cate);
        other o=new other(context);
        String id=""+o.getSp("pCount","lid");
        int i=Integer.parseInt(id)+1;

        av.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent,PICK_IMAGE_REQUEST);
            }
        });

        av.addp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                addToFirebase a=new addToFirebase();
                HashMap<String,String> product = new HashMap<>();
                product.put("Id", String.valueOf(i));
                product.put("Name",av.pname.getText().toString());
                product.put("Category",av.cat.getSelectedItem().toString());
                product.put("Price",av.price.getText().toString());
                product.put("Desc",av.desc.getText().toString());


                if(!a.imgup(filePath,context,product,av.pname.getText().toString())){
                    AppCompatActivity activity= (AppCompatActivity) v.getContext();
                    Home_frag h=new Home_frag();
                    addToFirebase aa=new addToFirebase();
                    aa.getp(context);
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.container,h).addToBackStack(null).commit();

                    Toast.makeText(context, "uploaded", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(
                            context, "Fail", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return av.getRoot();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST
                && resultCode == RESULT_OK
                && data != null
                && data.getData() != null)
            filePath = data.getData();
        av.imgurl.setText(filePath.toString());
        }




}