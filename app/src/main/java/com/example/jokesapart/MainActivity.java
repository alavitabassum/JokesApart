package com.example.jokesapart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<Object> viewItems = new ArrayList<>();
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;


    private static final String TAG = "MainActivity";

    Boolean ScrollingAtStart = false;
    int currentItems,totalItems,scrollOutItems;
    ProgressBar progressBar;

    private RecyclerAdapter.RecyclerViewClickListener listener;

    TextView viewSelection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.grey)));
        getSupportActionBar().setTitle("Jokes Apart");


        //getSupportActionBar().hide();

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        setOnClickListener();
        mAdapter = new RecyclerAdapter(this,viewItems,listener);

        mRecyclerView.setAdapter(mAdapter);

        progressBar =findViewById(R.id.progress);
        viewSelection =findViewById(R.id.viewSelection);

        Intent intent_get = getIntent();
        String str = intent_get.getStringExtra("message");
        viewSelection.setText(str);

        //viewSelection.setText((getIntent().getStringExtra("selection")));

        try {

            JSONArray array = new JSONArray(LoadJokesFromAssests());
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                String jokeid = object.getString("id");
              //  String type_all = object.getString("type");
                String setup = object.getString("setup");
                String punchline = object.getString("punchline");
              //  Jokes jokes_all = new Jokes(jokeid, type_all, setup, punchline);


                //show only knock knock jokes
                if(viewSelection.getText().toString().equals("Knock-Knock")){
                    getSupportActionBar().setTitle("Knock-Knock Jokes");

                    String type_kk = String.valueOf(object.getString("type").equals("knock-knock"));
                    Jokes jokes_kk = new Jokes(jokeid, type_kk, setup, punchline);
                    if(object.getString("type").equals("knock-knock")){
                        viewItems.add(jokes_kk);
                    }else{
                        //Toast.makeText(MainActivity.this,"ERROR ERROR!",Toast.LENGTH_SHORT).show();
                    }

                }
                //show only programming jokes
                else if(viewSelection.getText().toString().equals("Programming")){

                    getSupportActionBar().setTitle("Programming Jokes");
                    String type_prog = String.valueOf(object.getString("type").equals("programming"));
                    Jokes jokes_p = new Jokes(jokeid, type_prog, setup, punchline);
                    if(object.getString("type").equals("programming")){
                        viewItems.add(jokes_p);
                    }else{
                        //Toast.makeText(MainActivity.this,"ERROR ERROR!",Toast.LENGTH_SHORT).show();
                    }

                }
                //show only general jokes
                else if(viewSelection.getText().toString().equals("General")){

                    getSupportActionBar().setTitle("Jokes in General");
                    String type_general = String.valueOf(object.getString("type").equals("general"));
                    Jokes jokes_g = new Jokes(jokeid, type_general, setup, punchline);
                    if(object.getString("type").equals("general")){
                        viewItems.add(jokes_g);
                    }else{
                        //Toast.makeText(MainActivity.this,"ERROR ERROR!",Toast.LENGTH_SHORT).show();
                    }

                }else{
                    getSupportActionBar().setTitle("Jokes");
                    //show all jokes
                    String type_all = object.getString("type");
                    Jokes jokes_all = new Jokes(jokeid, type_all, setup, punchline);
                    viewItems.add(jokes_all);
                    //Toast.makeText(MainActivity.this,"ERROR!",Toast.LENGTH_SHORT).show();
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        //ONSCROLL LISTENER- progress bar to mimic data loading
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                //check if state changed
                if(newState== AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                    ScrollingAtStart = true;
                    progressBar.setVisibility(View.VISIBLE);
                }else{
                    ScrollingAtStart = false;

                    //adding delay
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setVisibility(View.GONE);
                        }
                    },3000);

                }
            }
        });

        setOnClickListener();

    }

//on clicking a joke got to new screen
    private void setOnClickListener() {
        listener = new RecyclerAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {

                Intent intent = new Intent(getApplicationContext(), RandomImages.class);
               /* intent.putExtra("Itemid", viewItems.get(position).getClass());
                intent.putExtra("id", viewItems.toString());*/
                startActivity(intent);
               // finish();
                //finishAffinity();

            }
        };
    }


    //fetching the file
    public String LoadJokesFromAssests(){
        String json=null;

        try {
            InputStream in=this.getAssets().open("jokes.json");//loading the file
            int size= in.available();//getting th size
            byte[] bbuffer=new byte[size];
            in.read(bbuffer);
            json=new String(bbuffer,"utf-8");//store in the json string
        }catch(IOException e){
            e.printStackTrace();
            return null;

        }
        return json;
    }

    public void onBackPressed() {

        Intent intent = new Intent(getApplicationContext(), SelectJokeType.class);
        startActivity(intent);
        finish();
        finishAffinity();//remove back stack
    }
}