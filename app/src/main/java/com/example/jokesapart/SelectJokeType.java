package com.example.jokesapart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SelectJokeType extends AppCompatActivity {

    TextView KnockKnock, Programming, General, AllType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_joke_type);

        KnockKnock =  findViewById(R.id.btn_knk);
        Programming = findViewById(R.id.btn_prog);
        General = findViewById(R.id.btn_general);
        AllType= findViewById(R.id.btn_all_jokes);

        KnockKnock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String str = KnockKnock.getText().toString();

                Intent i1 = new Intent(SelectJokeType.this, MainActivity.class);
                i1.putExtra("message", str);
                //i1.putExtra("selection",KnockKnock.getText().toString().trim());

                startActivity(i1);
            }
        });

        Programming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = Programming.getText().toString();
                Intent i2 = new Intent(SelectJokeType.this, MainActivity.class);
                i2.putExtra("message", str);
                //i2.putExtra("selection",Programming.getText().toString().trim());
                startActivity(i2);
            }
        });

        General.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String str = General.getText().toString();
                Intent i3 = new Intent(SelectJokeType.this, MainActivity.class);
                i3.putExtra("message", str);
                //i3.putExtra("selection",General.getText().toString().trim());
                startActivity(i3);
            }
        });

        AllType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = AllType.getText().toString();
                Intent i4 = new Intent(SelectJokeType.this, MainActivity.class);
                i4.putExtra("message", str);
                //i4.putExtra("selection",AllType.getText().toString().trim());
                startActivity(i4);


            }
        });

    }
}