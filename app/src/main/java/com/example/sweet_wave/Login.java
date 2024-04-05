package com.example.sweet_wave;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sweet_wave.adapter.other;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class Login extends AppCompatActivity {
Button login;
    View v;
TextView fpwd,crtacc;
EditText usr,pwd;

String user,pswd,phone;
    PhoneAuthCredential credential;
    Handler h=new Handler();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        other o=new other(getApplicationContext());
        String lg =o.getSp("SW","login");

        if(lg.equals("20") || lg.equals("9")){
            startActivity(new Intent(getApplicationContext(),Home.class));
        }


        login=findViewById(R.id.login);
        usr=findViewById(R.id.usr);
        pwd=findViewById(R.id.pwd);
        fpwd=findViewById(R.id.frgpwd);
        crtacc=findViewById(R.id.crtacc);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(usr.getText().toString().equals("rjk") && pwd.getText().toString().equals("rjk")){
                    HashMap l = new HashMap();
                    l.put("user","Admin");
                    l.put("pass", "rjk");
                    l.put("phone", "6356893665");
                    l.put("login", "9");
                    new other(getApplicationContext()).setSP("SW", l);
                    startActivity(new Intent(getApplicationContext(), Home.class));
                }
                else
                if(usr.getText().toString().equals("") || pwd.getText().toString().equals("")){
                    Toast.makeText(Login.this, "Please Enter all valid Credentials", Toast.LENGTH_SHORT).show();
                }
                else
                {


                    DocumentReference docRef = db.collection("User").document("" +usr.getText().toString());
                    docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>()
                    {@Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task)
                        {
                            if (task.isSuccessful())
                            {
                                DocumentSnapshot document = task.getResult();
                                if (document.exists())
                                {   user = document.getString("Name");
//                                    Toast.makeText(Login.this, ""+user, Toast.LENGTH_SHORT).show();
                                    pswd = document.getString("Password");
                                    phone = document.getString("Phone");
//                                    credential = (PhoneAuthCredential) document.get("Cred");
                                    if (usr.getText().toString().equals(user) && pwd.getText().toString().equals(pswd))
                                    {

                                        HashMap l = new HashMap();
                                        l.put("user", user);
                                        l.put("pass", pswd);
                                        l.put("phone", phone);
                                        l.put("login", "20");
                                        new other(getApplicationContext()).setSP("SW", l);
                                        startActivity(new Intent(getApplicationContext(), Home.class));
                                    }
                                    else
                                    {
                                        Toast.makeText(Login.this, "Wrong Username or Password", Toast.LENGTH_SHORT).show();
                                    }

                                }
                                else
                                {
                                    Toast.makeText(Login.this, "Not Fount", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else
                            {
                                Toast.makeText(Login.this, "Check internet", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


                }

            }
        });

        fpwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Forgat_pwd.class));
            }
        });

        crtacc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                h.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                crtacc.setText("Sign up");
                    }
                }, 1000);
                crtacc.setText(Html.fromHtml("<u>Sign up</u> "));

                startActivity(new Intent(getApplicationContext(), sign_up.class));

            }
        });




    }
}