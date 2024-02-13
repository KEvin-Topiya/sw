package com.example.sweet_wave;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Forgat_pwd extends AppCompatActivity {

    EditText ETPhoneNumber;
    Button btverify;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgat_pwd);

        ETPhoneNumber = findViewById(R.id.ETPhoneNumber);
        btverify = findViewById(R.id.btverify);

        btverify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(btverify.getText().toString().equals("8200787849")) {
    startActivity(new Intent(getApplicationContext(), chang_pwd.class));                }

            }
        });

    }
}

