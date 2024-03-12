package com.example.sweet_wave;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.*;
import android.widget.Toast;

import com.example.sweet_wave.databinding.ActivityOtpBinding;
import com.example.sweet_wave.firebase.addUser;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthMissingActivityForRecaptchaException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.ktx.Firebase;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class otp extends AppCompatActivity {
ActivityOtpBinding av;
String ph,un,ps,vId,otp;
SharedPreferences sp;
SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        av=ActivityOtpBinding.inflate(getLayoutInflater());
        setContentView(av.getRoot());
        av.pgbar.setVisibility(View.INVISIBLE);

        Intent i=getIntent();
        sp=getSharedPreferences("SW",MODE_PRIVATE);
        ph="+91"+i.getStringExtra("pn");
        un=i.getStringExtra("un");
        ps=i.getStringExtra("pw");
        vId=i.getStringExtra("vid");

//          set phono in ****text
        av.phno.setText("+91 ******"+ph.substring(ph.length() - 4 ));

        av.resend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(otp.this, "resend not work", Toast.LENGTH_SHORT).show();
            }
        });

        av.vrfy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                otp=av.otp.getText().toString().trim();
                av.pgbar.setVisibility(View.VISIBLE);
                av.vrfy.setVisibility(View.INVISIBLE);
                if(otp.isEmpty()){
                    Toast.makeText(otp.this, "plese enter OTP", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(vId !=null){
                        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(vId, otp);
                        FirebaseAuth
                                .getInstance()
                                .signInWithCredential(credential)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Intent i=new Intent(getApplicationContext(), Home.class);
                                    av.pgbar.setVisibility(View.VISIBLE);
                                    av.vrfy.setVisibility(View.INVISIBLE);
                                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

                                    editor=sp.edit();
                                    editor.putString("phone",ph);
                                    editor.putString("user",un);
                                    editor.putString("login","20");

                                    editor.apply();
                                    addUser a=new addUser();
                                    if(!(a.addtofirebae(un,ph,ps))){
                                        startActivity(i);
                                    }
                                    else{
                                        Toast.makeText(otp.this, "Error To add User", Toast.LENGTH_SHORT).show();
                                    }


                                }else{
                                    av.vrfy.setVisibility(View.VISIBLE);
                                    Toast.makeText(otp.this, "OTP is Not valid", Toast.LENGTH_SHORT).show();
                                }

                            }
                        });

                    }
                }



                
            }
        });
        av.resend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), sign_up.class));
                finish();

            }
        });

    }



}
