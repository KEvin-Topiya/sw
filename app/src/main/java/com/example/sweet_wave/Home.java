package com.example.sweet_wave;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.sax.RootElement;
import android.view.MenuItem;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.sweet_wave.fragements.Home_frag;
import com.example.sweet_wave.fragements.add_Product;
import com.example.sweet_wave.fragements.cart_frag;
import com.example.sweet_wave.fragements.offer_frag;
import com.example.sweet_wave.fragements.user_frag;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class Home extends AppCompatActivity {
     MeowBottomNavigation nav;
     String ROOT_FRAGMENT_TAG;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        nav = findViewById(R.id.nav);

        nav.add(new MeowBottomNavigation.Model(1, R.drawable.home));
        nav.add(new MeowBottomNavigation.Model(2, R.drawable.cuppon_icn));
        nav.add(new MeowBottomNavigation.Model(3, R.drawable.bag_icn));
        nav.add(new MeowBottomNavigation.Model(4, R.drawable.user_icon));
        nav.show(1,true);
        loadfrag(new add_Product(),0);
        nav.setOnClickMenuListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                switch(model.getId())
                {
                    case 1:loadfrag(new Home_frag(),0);
                        break;
                    case 2:loadfrag(new offer_frag(),0);
                        break;
                    case 3: loadfrag(new cart_frag(),0);
                        break;
                    case 4: loadfrag(new user_frag(),1);
                        break;
                }
                return null;
            }
        });

    }

    /*
    *  if (id == R.id.nav_home) {
                    loadfrag(new Home_frag(),0);
               } else if (id == R.id.nav_dis) {
                    loadfrag(new offer_frag(),0);
               } else if (id == R.id.nav_cart) {
                   loadfrag(new cart_frag(),0);
               } else {
                   loadfrag(new user_frag(),1);
               }
    * */
    public  void si(){
        Toast.makeText(getApplicationContext(), "Hello", Toast.LENGTH_SHORT).show();
    }
    public void loadfrag(Fragment fr,int x){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if(x==0){
            ft.add(R.id.container,fr);
            fm.popBackStack(ROOT_FRAGMENT_TAG,FragmentManager.POP_BACK_STACK_INCLUSIVE);
            ft.addToBackStack(ROOT_FRAGMENT_TAG);
        }
        else {
            ft.replace(R.id.container, fr);
            ft.addToBackStack(null);
        }

        ft.commit();
    }
}
