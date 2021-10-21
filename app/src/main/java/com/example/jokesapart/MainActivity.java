package com.example.jokesapart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        mAdapter = new RecyclerAdapter(this,viewItems);
        mRecyclerView.setAdapter(mAdapter);

        progressBar =findViewById(R.id.progress);

        try {

            JSONArray array = new JSONArray(LoadJokesFromAssests());
            for (int i = 0; i < array.length(); i++){
                JSONObject object = array.getJSONObject(i);
                String jokeid = object.getString("id");
                String type = object.getString("type");
                String setup = object.getString("setup");
                String punchline = object.getString("punchline");

                Jokes jokes = new Jokes(jokeid,type,setup,punchline);
                viewItems.add(jokes);

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
}