package com.example.sweet_wave.fragements;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.sweet_wave.R;
import com.example.sweet_wave.adapter.ProductStructure;
import com.example.sweet_wave.adapter.orderAdapter;
import com.example.sweet_wave.adapter.orderStructure;
import com.example.sweet_wave.adapter.other;
import com.example.sweet_wave.adapter.rcAdapter;
import com.example.sweet_wave.firebase.addToFirebase;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class Home_frag extends Fragment {

    Context context;
    int ck=0,b=0,p=0;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    public int count;

    int c=0;

    LinearLayout cakeback;
    LinearLayout breadback;
    LinearLayout pastryback;

    public Home_frag() {
        // Required empty public constructor
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_frag, container, false);
        context = container.getContext();

        SharedPreferences sp= context.getSharedPreferences("pCount",Context.MODE_PRIVATE);
        addToFirebase aa=new addToFirebase();
        aa.getp(context);

        RecyclerView rc = view.findViewById(R.id.product);


        TextView seeall=view.findViewById(R.id.all);

          cakeback=view.findViewById(R.id.catcakeback);
          breadback=view.findViewById(R.id.catbreadback);
          pastryback=view.findViewById(R.id.catpastryback);

         cakeback.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                 cakeback.setBackgroundResource(R.drawable.catback2);
                 breadback.setBackgroundResource(R.drawable.catback_shape);
                 pastryback.setBackgroundResource(R.drawable.catback_shape);
                 chRC(rc,"Category","Cake");

             }

         });
         breadback.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 breadback.setBackgroundResource(R.drawable.catback2);
                 cakeback.setBackgroundResource(R.drawable.catback_shape);
                 pastryback.setBackgroundResource(R.drawable.catback_shape);
                 chRC(rc,"Category","Bread");
             }

         });

         pastryback.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 pastryback.setBackgroundResource(R.drawable.catback2);
                 breadback.setBackgroundResource(R.drawable.catback_shape);
                 cakeback.setBackgroundResource(R.drawable.catback_shape);
                 chRC(rc,"Category","Tart");
             }
         });


        seeall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
loadrc(rc);
            }
        });


//        other h=new other(context);

        try{
         c=Integer.parseInt(sp.getString("count",""));
        }catch (Exception e){}

      loadrc(rc);


        ///


        return view;
    }

    public  void loadrc(RecyclerView rc){
        ArrayList<ProductStructure> data = new ArrayList<>();
        addToFirebase a=new addToFirebase();


        rcAdapter rca = new rcAdapter(context, data);

        db.collection("Products")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot document : task.getResult()) {
                                // Fetch the "name" field from each document
                                String Id=document.getString("Id");
                                String name = document.getString("Name");
                                String price = document.getString("Price");
                                String img = document.getString("Img");
                                String cat = document.getString("Category");
                                String dec = document.getString("Desc");
                                data.add(new ProductStructure(Integer.parseInt(Id),""+name,""+price,""+cat,""+img,""+dec));
                            }
                            count=data.size();
//                            Toast.makeText(context, ""+count, Toast.LENGTH_SHORT).show();
                            rc.setLayoutManager(new GridLayoutManager(context, 2));
                            rc.setAdapter(rca);
                        } else {
                            Toast.makeText(context, "error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
    public void chRC(RecyclerView orc,String tp,String cat){
        ArrayList<ProductStructure> data = new ArrayList<>();


        String un=new other(context).getSp("SW","user");
        rcAdapter rca = new rcAdapter(context, data);


        //
        db.collection("Products")
                .whereEqualTo(""+tp,""+cat )
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String Id=document.getString("Id");
                                String name = document.getString("Name");
                                String price = document.getString("Price");
                                String img = document.getString("Img");
                                String cat = document.getString("Category");
                                String dec = document.getString("Desc");
                                data.add(new ProductStructure(Integer.parseInt(Id),""+name,""+price,""+cat,""+img,""+dec));

                            }
                            orc.setLayoutManager(new GridLayoutManager(context, 2));
                            orc.setAdapter(rca);
                        } else {
                            Toast.makeText(context, "No data", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        //

    }


}