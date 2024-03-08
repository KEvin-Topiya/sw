package com.example.sweet_wave;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class sign_up extends AppCompatActivity {
    Button lg;
    EditText un,phno,pwd;
    FirebaseAuth mAuth;
    ProgressBar pgbar;
    Intent i;

    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        mAuth=FirebaseAuth.getInstance();

        lg=findViewById(R.id.login);
        un=findViewById(R.id.usr);
        pwd=findViewById(R.id.pwd);
        phno=findViewById(R.id.phone);
        pgbar=findViewById(R.id.prog);
        pgbar.setVisibility(View.INVISIBLE);
        i=new Intent(this, otp.class);

        lg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    i.putExtra("pn",phno.getText().toString());
                    i.putExtra("un",un.getText().toString());
                    i.putExtra("pw",pwd.getText().toString());
                    sendOtp(phno.getText().toString());

            }

        });

    }

    private void sendOtp(String ph) {
        pgbar.setVisibility(View.VISIBLE);
        lg.setVisibility(View.INVISIBLE);
        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential credential) {
            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                pgbar.setVisibility(View.GONE);
                lg.setVisibility(View.VISIBLE);
                Toast.makeText(sign_up.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }


            @Override
            public void onCodeSent(@NonNull String verificationId,
                                   @NonNull PhoneAuthProvider.ForceResendingToken token) {
                pgbar.setVisibility(View.GONE);
                lg.setVisibility(View.VISIBLE);

                i.putExtra("vid",verificationId);
                startActivity(i);

            }

        };
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber("+91"+phno.getText().toString().trim())       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // (optional) Activity for callback binding
                        // If no activity is passed, reCAPTCHA verification can not be used.
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }
}