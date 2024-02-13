package com.example.sweet_wave;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class chang_pwd extends AppCompatActivity {

    EditText new_pwd,cnew_pwd;

    Button ok;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chang_pwd);
        new_pwd = findViewById(R.id.new_pwd);
        cnew_pwd = findViewById(R.id.cnew_pwd);

        startActivity(new Intent(getApplicationContext(), Login.class));

    }
}