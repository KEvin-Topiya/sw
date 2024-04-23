package com.example.sweet_wave.fragements;

import static com.google.firebase.appcheck.internal.util.Logger.TAG;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.sweet_wave.R;
import com.example.sweet_wave.adapter.ProductStructure;
import com.example.sweet_wave.adapter.orderAdapter;
import com.example.sweet_wave.adapter.orderStructure;
import com.example.sweet_wave.adapter.other;
import com.example.sweet_wave.firebase.addToFirebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Order_frag extends Fragment {

    Context context;
    public MeowBottomNavigation bottomNavigation;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_order, container, false);
        context=container.getContext();


        RecyclerView orc=view.findViewById(R.id.orc);


        ArrayList<orderStructure> data = new ArrayList<>();


        String un=new other(context).getSp("SW","user");

        other o=new other(context);
        if(o.getSp("SW","login").equals("9")){
            db.collection("Order")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    String id= document.getId();
                                    String add=document.getString("Address");
                                    String date = document.getString("Date");
                                    String order = document.getString("Order");
                                    String pay = document.getString("Pay");
                                    String total = document.getString(  "Total").replace("₹","");
                                    String name = document.getString("Uname");
                                    String phone = document.getString("Phone");
                                    String status=document.getString("Status");
                                    data.add(new orderStructure((Integer.parseInt(total)),""+id,""+add,""+order,""+date,""+pay,""+status,""+name,""+phone));

                                }
                                orderAdapter o=new orderAdapter(context,data);
                                orc.setLayoutManager(new LinearLayoutManager(context));
                                orc.setAdapter(o);
                            } else {
                                Toast.makeText(context, "No data", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
        else{

        db.collection("Order")
                .whereEqualTo("Uname",un )
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String id= document.getId();
                                String add=document.getString("Address");
                                String date = document.getString("Date");
                                String order = document.getString("Order");
                                String pay = document.getString("Pay");
                                String total = document.getString(  "Total").replace("₹","");
                                String name = document.getString("Uname");
                                String phone = document.getString("Phone");
                                String status=document.getString("Status");
                                data.add(new orderStructure((Integer.parseInt(total)),""+id,""+add,""+order,""+date,""+pay,""+status,""+name,""+phone));

                            }
                            orderAdapter o=new orderAdapter(context,data);
                                orc.setLayoutManager(new LinearLayoutManager(context));
                                orc.setAdapter(o);
                        } else {
                            Toast.makeText(context, "No data", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        }
        //





        return  view;
    }
}