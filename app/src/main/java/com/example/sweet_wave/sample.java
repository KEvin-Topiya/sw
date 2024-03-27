package com.example.sweet_wave;

import static com.google.firebase.appcheck.internal.util.Logger.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.sweet_wave.adapter.ProductStructure;
import com.example.sweet_wave.adapter.rcAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class sample extends AppCompatActivity {
RecyclerView rc;
    ArrayList<ProductStructure> user;
    FirebaseFirestore db;
    rcAdapter rca;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);
        rc=findViewById(R.id.rc);

        db = FirebaseFirestore.getInstance();
        user=new ArrayList<ProductStructure>();
        rca=new rcAdapter(this,user);
        EventChangeList();

        rc.setAdapter(rca);
        rc.setLayoutManager(new GridLayoutManager(this,2));




//        arry_p_model.add(new ProductStructure("abc","1234567890"));

    }
    public void EventChangeList(){
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
                                Toast.makeText(sample.this, name+" "+price, Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(sample.this, "error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
}