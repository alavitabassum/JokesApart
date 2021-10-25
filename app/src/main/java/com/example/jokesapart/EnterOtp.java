package com.example.jokesapart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

public class EnterOtp extends AppCompatActivity {
    TextView  viewPhnNum;
    TextView resendOTP;
    Button verifyOTP_btn;
    private String verificationId;
    EditText c1,c2,c3,c4,c5,c6;
    ProgressBar progressBarVerify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_otp);



        getSupportActionBar().hide();
        viewPhnNum =findViewById(R.id.printPhnNum);
        resendOTP = findViewById(R.id.resentOtp);
        verifyOTP_btn=findViewById(R.id.verify_btn);
        progressBarVerify = findViewById(R.id.progressBarVerify);
        c1=findViewById(R.id.c1);
        c2=findViewById(R.id.c2);
        c3=findViewById(R.id.c3);
        c4=findViewById(R.id.c4);
        c5=findViewById(R.id.c5);
        c6=findViewById(R.id.c6);

        OtpInput();
        viewPhnNum.setText(String.format("OTP sent to:" + "+1-%s",getIntent().getStringExtra("phone")));
        verificationId = getIntent().getStringExtra("verificationId");

        resendOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(EnterOtp.this,"OTP resent successfully!",Toast.LENGTH_SHORT).show();
            }
        });

        verifyOTP_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressBarVerify.setVisibility(View.VISIBLE);
                verifyOTP_btn.setVisibility(View.INVISIBLE);

                if(c1.getText().toString().trim().isEmpty() ||
                        c2.getText().toString().trim().isEmpty() ||
                        c3.getText().toString().trim().isEmpty() ||
                        c4.getText().toString().trim().isEmpty() ||
                        c5.getText().toString().trim().isEmpty() ||
                        c6.getText().toString().trim().isEmpty())
                {
                    Toast.makeText(EnterOtp.this,"Invalid OTP!",Toast.LENGTH_SHORT).show();


                }else if(verificationId !=null){
                    String code = c1.getText().toString().trim() +
                            c2.getText().toString().trim() +
                            c3.getText().toString().trim() +
                            c4.getText().toString().trim() +
                            c5.getText().toString().trim() +
                            c6.getText().toString().trim();

                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
                    FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){

                                progressBarVerify.setVisibility(View.VISIBLE);
                                verifyOTP_btn.setVisibility(View.INVISIBLE);
                                Toast.makeText(EnterOtp.this,"Welcome!",Toast.LENGTH_SHORT).show();
                                Intent jokesIntent = new Intent(EnterOtp.this, SelectJokeType.class);
                                jokesIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(jokesIntent);
                            }else{
                                progressBarVerify.setVisibility(View.GONE);
                                verifyOTP_btn.setVisibility(View.VISIBLE);
                                Toast.makeText(EnterOtp.this,"Invalid OTP!",Toast.LENGTH_SHORT).show();

                            }

                        }
                    });
                }
            }
        });
    }

    private void OtpInput() {
        c1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                c2.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        c2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                c3.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        c3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                c4.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        c4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                c5.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        c5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                c6.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}