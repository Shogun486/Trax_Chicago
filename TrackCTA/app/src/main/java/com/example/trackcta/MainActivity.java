package com.example.trackcta;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.graphics.Color;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/*

    Opening activity

*/

public class MainActivity extends AppCompatActivity
{
    private static ArrayList<String> stops = new ArrayList<>();
    private static ArrayAdapter <String> adapter;
    private static ListView listView;




    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);

        stops.add("red");
        stops.add("blue");

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
                //Log.d("Clicked", String.valueOf(position));
                String stationName =((TextView)view).getText().toString();
                //Log.d("stationName", stationName);

                List<String> stops = new ArrayList<>();
                for(String stopName: StopsAPI.stationInfo.get(stationName))
                {
                    //Log.d("stopName", s);
                    stops.add(stopName);
                }

                adapter.clear();
                adapter.addAll(stops);
                listView.deferNotifyDataSetChanged();

            }
        });

        StopsAPI sapi = new StopsAPI();
        sapi.call(this);
        //LocationsAPI.call(this);
    }

    public static void updateStops()
    {
        for(String station: StopsAPI.line.keySet())
            adapter.add(station);
        listView.deferNotifyDataSetChanged();
    }
}
