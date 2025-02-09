package com.example.trackcta;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import android.widget.ListView;
import android.widget.TextView;

/*
    Shows all stops for a given station
*/

public class StopsActivity extends AppCompatActivity
{
    private ArrayAdapter<String> adapter;
    private ArrayList<String> stops;
    private ListView stopsList;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stops);

        intent = getIntent();
        String stationName = intent.getStringExtra("stationName");
        stopsList = findViewById(R.id.stopsList);

        //Log.d("stationToStops", StopsAPI.stationToStops.get(stationName).toString());
        stops = StopsAPI.stationToStops.get(stationName);
        ArrayList <String> l = new ArrayList(new HashSet(stops));
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, l)
        {
            @Override
            public View getView(int position, View convertView, ViewGroup parent)
            {
                TextView textView = (TextView) super.getView(position, convertView, parent);
                return textView;
            }
        };
        stopsList.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        stopsList.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                String stopName = ((TextView)view).getText().toString();
                //Log.d("stopclick", stationName);
                intent = new Intent(getApplicationContext(), ArrivalsActivity.class);
                intent.putExtra("stopID", StopsAPI.stopIDs.get(stopName));
                startActivity(intent);
            }
        });
    }
}
