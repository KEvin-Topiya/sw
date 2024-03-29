package com.example.sweet_wave.firebase;


import static com.google.common.io.Files.getFileExtension;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.util.HashMap;

public class addToFirebase {
    boolean res;
    String img="";
    int x=0;
    StorageReference storageReference;
    FirebaseStorage storage;
    Uri file;
    public boolean addtofirebae(String collection,HashMap<String,String> map,String doc){
        if(x==9){
            map.put("Img",img);
            x=0;
        }
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
                                    addtofirebae("Products",product,pname);
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


}