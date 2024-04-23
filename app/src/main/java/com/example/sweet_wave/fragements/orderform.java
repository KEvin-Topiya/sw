package com.example.sweet_wave.fragements;

import static com.google.firebase.appcheck.internal.util.Logger.TAG;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.sweet_wave.R;
import com.example.sweet_wave.adapter.other;
import com.example.sweet_wave.adapter.sqlLiteHelper;
import com.example.sweet_wave.databinding.FragmentOrderformBinding;
import com.example.sweet_wave.firebase.addToFirebase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Random;

public class orderform extends Fragment {
    Context context;
TextView date,total;
EditText add,upi;
Button order;
RadioGroup rg;
    HashMap<String,String> orders;

String unm,name,address,pay,dat,ord,totl,doc,phone;
    public MeowBottomNavigation bottomNavigation;
   HashMap<String,String > data;
    @SuppressLint({"MissingInflatedId", "ResourceType"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        bottomNavigation = requireActivity().findViewById(R.id.nav);
        bottomNavigation.show(3,true);
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_orderform, container, false);
        context=container.getContext();
        String da=new SimpleDateFormat("dd_MM_yyyy", Locale.getDefault()).format(new Date());

        date=view.findViewById(R.id.orDate);
        order=view.findViewById(R.id.order);
        add=view.findViewById(R.id.addres);
        upi=view.findViewById(R.id.upi);
        rg=view.findViewById(R.id.pay);
        total=view.findViewById(R.id.total);
        rg.check(1);

        date.setText(da);
        sqlLiteHelper db=new sqlLiteHelper(context);
        data =new HashMap<>();
        data=db.selectPid();
                 totl=data.get("total");
                 total.setText(totl+"₹");

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = view.findViewById(checkedId);
                if(radioButton.getText().toString().equals("UPI")){
                    upi.setText("");
                    upi.setVisibility(View.VISIBLE);


                }
                else if(radioButton.getText().toString().equals("COD")){

                   upi.setText("COD");
                    upi.setVisibility(View.GONE);
                }
            }
        });

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!add.getText().equals("") && !upi.getText().equals("")) {
                    ord = data.get("ord").toString().replaceAll(".$", "").trim();
                    name = new other(context).getSp("SW", "user");
                    address = add.getText().toString().trim();
                    phone =new other(context).getSp("SW", "phone");
                    pay = upi.getText().toString();
                    dat = da;


                    orders = new HashMap<>();
                    orders.put("Order", ord);
                    orders.put("Uname", name);
                    orders.put("Address", address);
                    orders.put("Pay", pay);
                    orders.put("Phone", phone);
                    orders.put("Date", dat);
                    orders.put("Status", "Persuing");
                    orders.put("Total", totl );//+ "₹");
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                    orders.entrySet().forEach(System.out::println);
//                }
                    doc = name + "_" + dat;
                    addToFirebase ad = new addToFirebase();

                    if (!ad.addtofirebae("Order", orders, doc + "_" + new Random().nextInt(100))) {

                        Toast.makeText(context, "successfully ordered", Toast.LENGTH_SHORT).show();

                        db.trunc("CART_TBL");
                    AppCompatActivity activity = (AppCompatActivity) getActivity();
                    Order_frag o=new Order_frag();
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.container,o).addToBackStack(null).commit();

                    } else {
                        Toast.makeText(context, "order fail", Toast.LENGTH_SHORT).show();
                    }
                }





            }
        });





        return view;
    }
}