package com.example.jokesapart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listview=findViewById(R.id.listview);

        try {
            JSONArray array = new JSONArray(LoadJokesFromAssests());
            HashMap<String,String> list;
            ArrayList<HashMap<String,String>> arrayList=new ArrayList<>();
            for (int i = 0; i < array.length(); i++){
                JSONObject object = array.getJSONObject(i);
                String jokeid = object.getString("id");
                String type = object.getString("type");
                String setup = object.getString("setup");
                String punchline = object.getString("punchline");
                list= new HashMap<>();
                list.put("JokeID",jokeid);
                list.put("JokeType",type);
                list.put("JokeInfo",setup);
                list.put("Punchline",punchline);
                arrayList.add(list);


            }
            ListAdapter adapter=new SimpleAdapter(this,arrayList,R.layout.listview_design,new String[]{"JokeID","JokeType","JokeInfo","Punchline"},new int[]{R.id.design,R.id.url,R.id.des,R.id.ans});
            //ListAdapter adapter=new SimpleAdapter(this,arrayList,R.layout.listview_design,new String[]{"JokeInfo","Punchline"},new int[]{R.id.des,R.id.ans});
            listview.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public String LoadJokesFromAssests(){
        String json=null;

        try {
            InputStream in=this.getAssets().open("jokes.json");//loading the file
            int size= in.available();//getting th size
            byte[] bbuffer=new byte[size];
            in.read(bbuffer);
            json=new String(bbuffer,"UTF-8");//store in the json string
        }catch(IOException e){
            e.printStackTrace();
            return null;

        }
        return json;
    }
}