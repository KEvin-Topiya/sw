package com.example.sweet_wave.firebase;


import static com.google.common.io.Files.getFileExtension;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.sweet_wave.R;
import com.example.sweet_wave.adapter.ProductStructure;
import com.example.sweet_wave.adapter.other;
import com.example.sweet_wave.adapter.rcAdapter;
import com.example.sweet_wave.fragements.Home_frag;
import com.example.sweet_wave.fragements.cart_frag;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.firestore.AggregateQuery;
import com.google.firebase.firestore.AggregateQuerySnapshot;
import com.google.firebase.firestore.AggregateSource;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class addToFirebase {
    boolean res;
    String img="";
    StorageReference storageReference;
    FirebaseFirestore db;
    FirebaseStorage storage;
    CollectionReference collection;
    Uri file;
    public boolean addtofirebae(String collection,HashMap<String,String> map,String doc){
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection(collection).document(doc).set(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        res =true;
                    }
                })
//
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        res=false;
                    }
                });
        return res;
    }
//image upload to firebase
    public boolean imgup(Uri filePath, Context context,HashMap<String,String> product,String pname)
    {
    FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageReference = storage.getReference();
        file=Uri.fromFile(new File(filePath.toString()));
        String imgnm=pname+" "+getFileExtension(filePath.toString());
        String imgpath="Products/"+ imgnm;

        StorageReference imgref=storageReference.child("Products/"+imgnm);

        if (filePath != null) {

            // Code for showing progressDialog while uploading
            ProgressDialog progressDialog
                    = new ProgressDialog(context);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            // Defining the child of storageReference
            StorageReference ref
                    = storageReference
                    .child(imgpath);

            // adding listeners on upload
            // or failure of image
            ref.putFile(filePath)
                    .addOnSuccessListener(
                            new OnSuccessListener<UploadTask.TaskSnapshot>() {

                                @Override
                                public void onSuccess(
                                        UploadTask.TaskSnapshot taskSnapshot)
                                {
                                    imgref.getDownloadUrl().addOnSuccessListener(uri -> {
                                        product.put("Img",uri.toString());
                                    addtofirebae("Products",product,product.get("Id"));
                                    progressDialog.dismiss();
                                    res=true;

                                    });

                                    // Image uploaded successfully
                                    // Dismiss dialog
//                                    product.put("file",imgpath);
                                }
                            })

                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e)
                        {

                            // Error, Image not uploaded
                            progressDialog.dismiss();
                            res=false;
                        }
                    })
                    .addOnProgressListener(
                            new OnProgressListener<UploadTask.TaskSnapshot>() {

                                // Progress Listener for loading
                                // percentage on the dialog box
                                @Override
                                public void onProgress(
                                        UploadTask.TaskSnapshot taskSnapshot)
                                {
                                    double progress
                                            = (100.0
                                            * taskSnapshot.getBytesTransferred()
                                            / taskSnapshot.getTotalByteCount());
                                    progressDialog.setMessage(
                                            "Uploaded "
                                                    + (int)progress + "%");
                                }
                            });
        }

        return  res;
    }


    public HashMap<String, String> fetchdoc(String doc) {

        HashMap<String, String> p = new HashMap<>();
//        int c=getCount("Products");
        FirebaseFirestore db = FirebaseFirestore.getInstance();


        return p;
    }

        public static int count=0;
//    public int getCount(String col) {
//        FirebaseFirestore db=FirebaseFirestore.getInstance();
//        db.collection(""+col)
//                .get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if (task.isSuccessful()) {
//                            for (QueryDocumentSnapshot document : task.getResult()) {
//                                count++;
//                            }
//
//
//                        }
//                    }
//                });
//        return  count;
//
//
//    }

    public void getp(Context context){
        ArrayList<ProductStructure> data = new ArrayList<>();
        db = FirebaseFirestore.getInstance();
        db.collection("Products")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot document : task.getResult()) {
                                data.add(new ProductStructure(0,"","","","",""));

                            }
                            other o =new other(context);
                            HashMap<String,String> p=new HashMap<>();
                            p.put("count",""+data.size());
                            o.setSP("pCount",p);




                        }
                    }
                });

    }


}