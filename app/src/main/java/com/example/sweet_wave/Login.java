package com.example.sweet_wave;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {
Button login;
TextView fpwd,crtacc;
EditText usr,pwd;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login=findViewById(R.id.login);
        usr=findViewById(R.id.usr);
        pwd=findViewById(R.id.pwd);
        fpwd=findViewById(R.id.frgpwd);
        crtacc=findViewById(R.id.crtacc);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(usr.getText().toString().equals("") || pwd.getText().toString().equals("")){
                    Toast.makeText(Login.this, "Please Enter all valid Credentials", Toast.LENGTH_SHORT).show();
                }
                else if(usr.getText().toString().equals("RJK") && pwd.getText().toString().equals("RJK") ){
                    startActivity(new Intent(getApplicationContext(), Home.class));
                }
                else{
                    Toast.makeText(Login.this, "Wrong Username or Password", Toast.LENGTH_SHORT).show();
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
                startActivity(new Intent(getApplicationContext(), sign_up.class));
            }
        });



    }
}