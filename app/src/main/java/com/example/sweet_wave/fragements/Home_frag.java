package com.example.sweet_wave.fragements;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sweet_wave.R;
import com.example.sweet_wave.adapter.ProductStructure;
import com.example.sweet_wave.adapter.rcAdapter;
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
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class Home_frag extends Fragment {


    Context context;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    ;


    public Home_frag() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home_frag, container, false);
        RecyclerView rc = view.findViewById(R.id.product);

        ArrayList<ProductStructure> data = new ArrayList<>();
        context = container.getContext();

        String nm ="", ps="";

        rcAdapter rca = new rcAdapter(context, data);

        db.collection("Products")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot document : task.getResult()) {
                                // Fetch the "name" field from each document
                                String name = document.getString("Name");
                                String price = document.getString("Price");
                                String img = document.getString("Img");
                                String dec = document.getString("Desc");
                            data.add(new ProductStructure(""+name,""+price,""+img,""+dec));
                            }
                            rc.setLayoutManager(new GridLayoutManager(context, 2));
                            rc.setAdapter(rca);
                        } else {
                            Toast.makeText(context, "error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        ///


        return view;
    }
}