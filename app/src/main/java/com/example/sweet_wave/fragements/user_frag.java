package com.example.sweet_wave.fragements;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.os.ConditionVariable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.sweet_wave.Home;
import com.example.sweet_wave.Login;
import com.example.sweet_wave.R;
import com.example.sweet_wave.adapter.other;
import com.example.sweet_wave.databinding.FragmentAddProductBinding;
import com.example.sweet_wave.databinding.FragmentUserFragBinding;
import com.google.firebase.auth.FirebaseAuth;

import java.util.HashMap;


public class user_frag extends Fragment {

    FirebaseAuth log=  FirebaseAuth.getInstance();
    public MeowBottomNavigation bottomNavigation;
    TextView un,mb;
    public user_frag() {
        // Required empty public constructor
    }
    AppCompatButton logout;
    Context context;

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_user_frag, container, false);
//        av= FragmentUserFragBinding.inflate(inflater,container,false);
        context=container.getContext();
        un=view.findViewById(R.id.un);
        mb=view.findViewById(R.id.mb);

        other o=new other(context);
        un.setText(o.getSp("SW","user"));
        mb.setText(o.getSp("SW","phone"));


        bottomNavigation = requireActivity().findViewById(R.id.nav);
        bottomNavigation.show(4,true);
    logout=view.findViewById(R.id.logout);
    logout.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            log.signOut();
            other o=new other(container.getContext());
            HashMap<String ,String> l=new HashMap();
            l.put("login","23");
            o.setSP("SW",l);
                startActivity(new Intent(getActivity(), Login.class));
                getActivity().finish();
        }
    });



//                log.signOut();
//                Intent intent = new Intent(getActivity(), Login.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                startActivity(intent);
//                getActivity().finish();
        return view;
    }
}