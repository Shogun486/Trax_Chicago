package com.example.trackcta;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
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
    Opening activity allows user to select station, which will then start
    up a new activity to filter a specific platform/stop at the location
*/

public class MainActivity extends AppCompatActivity
{
    private static List<String> stops = new ArrayList<>();
    private static List <Integer> ids = new ArrayList<>();
    private static ArrayAdapter<String> adapter;
    private static ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stops.clear();

        // API calls
        StopsAPI sapi = new StopsAPI();
        sapi.call(this);
        //LocationsAPI.call(this);

        listView = findViewById(R.id.listView);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, stops)
        {
            @Override
            public View getView(int position, View convertView, ViewGroup parent)
            {
                TextView textView = (TextView) super.getView(position, convertView, parent);
                String stationName = textView.getText().toString();
                String color = StopsAPI.getColor(stationName);
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
                int stopID = -1; // default value
                stops.clear();
                ids.clear();

                for(String info: StopsAPI.getInfo(stationName))
                {
                    // Separate ID numbers and stop_names into different Lists
                    try
                    {
                        stopID = Integer.parseInt(info);
                        ids.add(stopID);
                    }
                    catch (NumberFormatException e)
                    {
                        stops.add(info);
                    }
                }

                // Start new activity
                Intent intent = new Intent(getApplicationContext(), StopsActivity.class);
                startActivity(intent);
            }
        });
    }

    public static void updateStops()
    {
        for(String station: StopsAPI.getStations())
            adapter.add(station);
        listView.deferNotifyDataSetChanged();
    }

    public static ArrayList <String> getStops() { return new ArrayList<>(stops); }

    public static int getID(int index) { return ids.get(index); }
}
