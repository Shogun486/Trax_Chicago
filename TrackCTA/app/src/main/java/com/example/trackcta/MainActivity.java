package com.example.trackcta;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import java.util.ArrayList;
import java.util.Arrays;
import android.widget.ListView;


public class MainActivity extends AppCompatActivity
{

    private static ListView listView;
    private static ArrayList<String> stops = new ArrayList<>();

    private static ArrayAdapter <String> adapter;




    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        listView = findViewById(R.id.listView);
        stops.add("red");
        stops.add("blue");

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, stops);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Log.d("Clicked", String.valueOf(position));
            }
        });

        StopsAPI sapi = new StopsAPI();
        sapi.call(this);
        //LocationsAPI.call(this);
    }


    public static void updateStops(String [] arr)
    {
        Log.d("cloned", Arrays.toString(arr));
        StringBuilder sb = new StringBuilder("");

        for(String s: arr)
        {
            sb.append(s);
            sb.append("\n");
        }

        for(String s: arr)
            adapter.add(s);
        listView.deferNotifyDataSetChanged();

    }
}
