package com.example.trackcta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.graphics.Color;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import java.util.ArrayList;
import java.util.List;

/*

    Opening activity

*/

public class MainActivity extends AppCompatActivity
{
    public static List<String> stops = new ArrayList<>();
    private static ArrayAdapter<String> adapter;
    private static ListView listView;
    private int ctr = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        stops.clear();

        StopsAPI sapi = new StopsAPI();
        sapi.call(this);

        listView = findViewById(R.id.listView);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, stops)
        {
            @Override
            public View getView(int position, View convertView, ViewGroup parent)
            {
                TextView textView = (TextView) super.getView(position, convertView, parent);
                String stationName = textView.getText().toString();
                String color = StopsAPI.line.get(stationName);
                if(color != null && color.equals("red"))
                {
                    textView.setBackgroundColor(Color.RED);
                }
                else if(color != null && color.equals("blue"))
                {
                    textView.setBackgroundColor(Color.BLUE);
                }
                else if(color != null && color.equals("green"))
                {
                    textView.setBackgroundColor(Color.GREEN);
                }
                else if(color != null && color.equals("brown"))
                {
                    textView.setBackgroundColor(Color.rgb(150, 75, 0));
                }
                else if(color != null && color.equals("purple"))
                {
                    textView.setBackgroundColor(getResources().getColor(R.color.purple_700));
                }
                else if(color != null && color.equals("yellow"))
                {
                    textView.setBackgroundColor(Color.YELLOW);
                }
                else if(color != null && color.equals("pink"))
                {
                    textView.setBackgroundColor(getResources().getColor(R.color.purple_200));
                }
                else if(color != null && color.equals("orange"))
                {
                    textView.setBackgroundColor(Color.rgb(255, 165, 0));
                }

                return textView;
            }
        };
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                String stationName =((TextView)view).getText().toString();

                stops.clear();
                for(String stopName: StopsAPI.stationInfo.get(stationName))
                {
                    stops.add(stopName);
                }
                Log.d("stops", stops.toString());
                Intent intent = new Intent(getApplicationContext(), StopsDisplayActivity.class);
                intent.putExtra("stationName", ((TextView)view).getText().toString());
                startActivity(intent);
            }
        });

        //LocationsAPI.call(this);
    }

    public static void updateStops()
    {
        for(String station: StopsAPI.line.keySet())
            adapter.add(station);
        listView.deferNotifyDataSetChanged();
    }
}
