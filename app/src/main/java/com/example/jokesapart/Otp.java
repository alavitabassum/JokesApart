package com.example.jokesapart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.internal.InternalTokenProvider;

import java.util.concurrent.TimeUnit;

public class Otp extends AppCompatActivity {

    Button btnVerify;
    EditText phoneNum;
    ProgressBar progressBar;
    private FirebaseAuth mAuth;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_otp);


       mAuth = FirebaseAuth.getInstance();

        getSupportActionBar().hide();


         btnVerify = findViewById(R.id.verify_btn);
         phoneNum = findViewById(R.id.etPhone);
         progressBar= findViewById(R.id.progressBar);


                btnVerify.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (phoneNum.getText().toString().trim().isEmpty()){
                            Toast.makeText(Otp.this,"Invalid Phone Number!",Toast.LENGTH_SHORT).show();
                        }else if(phoneNum.getText().toString().trim().length()!=10){
                            Toast.makeText(Otp.this,"Phone Number must be 10 digits!",Toast.LENGTH_SHORT).show();
                        }else{

                            otpSend();
                        }
                    }
                });

    }

    private void otpSend() {
        progressBar.setVisibility(View.VISIBLE);
        btnVerify.setVisibility(View.INVISIBLE);

                mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(PhoneAuthCredential credential) {

            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                progressBar.setVisibility(View.GONE);
                btnVerify.setVisibility(View.VISIBLE);
                Toast.makeText(Otp.this,e.getLocalizedMessage(),Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onCodeSent(@NonNull String verificationId,
                                   @NonNull PhoneAuthProvider.ForceResendingToken token) {
                progressBar.setVisibility(View.GONE);
                btnVerify.setVisibility(View.VISIBLE);
                Toast.makeText(Otp.this,"OTP sent successfully!",Toast.LENGTH_SHORT).show();

                Intent intentotpEnter =new Intent(Otp.this,EnterOtp.class);
                intentotpEnter.putExtra("phone",phoneNum.getText().toString().trim());
                intentotpEnter.putExtra("verificationId",verificationId);
                startActivity(intentotpEnter);

            }
        };

        PhoneAuthOptions options = PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber("+1" + phoneNum.getText().toString().trim())       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

}