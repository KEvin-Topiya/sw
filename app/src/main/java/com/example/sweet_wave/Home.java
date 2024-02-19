package com.example.sweet_wave;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.sweet_wave.fragements.Home_frag;
import com.example.sweet_wave.fragements.cart_frag;
import com.example.sweet_wave.fragements.offer_frag;
import com.example.sweet_wave.fragements.user_frag;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class Home extends AppCompatActivity {
BottomNavigationView nav;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        nav=findViewById(R.id.navbar);

       nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
           @Override
           public boolean onNavigationItemSelected(@NonNull MenuItem item) {
               int id = item.getItemId();
               if (id == R.id.nav_home) {
                    loadfrag(new Home_frag(),false);
               } else if (id == R.id.nav_dis) {
                    loadfrag(new offer_frag(),false);
               } else if (id == R.id.nav_cart) {
                   loadfrag(new cart_frag(),false);
               } else {
                   loadfrag(new user_frag(),true);
               }
               return true;
           }
       });
       nav.setSelectedItemId(R.id.nav_home);
    }
    public void loadfrag(Fragment fr,boolean x){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if(x)ft.add(R.id.container,fr);
        else ft.replace(R.id.container, fr);
        ft.commit();
    }
}
