package com.example.jokesapart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;


public class RandomImages extends AppCompatActivity {


    //private WebView webView;



    WebView webView;
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_random_images);


        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.grey)));
        getSupportActionBar().setTitle("Select any joke to get a random photo!");

        //getSupportActionBar().hide();

       webView = findViewById(R.id.random_view);
        //webView = new WebView(this);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://picsum.photos/200/300");
        //setContentView(webView);

        button = (Button) findViewById(R.id.see_joke);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openJustKidding();
            }
        });

    }
    protected void onDestroy(){
        super.onDestroy();

        if (webView !=null){
            webView.destroy();
        }
    }

    public void openJustKidding(){
        Intent intent = new Intent(this, JustKidding.class);
        startActivity(intent);
       // finish();
        //finishAffinity();
    }

    public void onBackPressed() {
        Intent intent = new Intent(this, SelectJokeType.class);
        startActivity(intent);
        finish();
        finishAffinity();
    }
}