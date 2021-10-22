package com.example.jokesapart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Spalshscreen2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spalshscreen2);

        getSupportActionBar().hide();


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent i = new Intent(Spalshscreen2.this, MainActivity.class);
                startActivity(i);
                finish();

            }

        }, 5000); // wait for 3 seconds
    }
}