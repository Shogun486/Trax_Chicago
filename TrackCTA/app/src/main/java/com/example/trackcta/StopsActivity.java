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
        stopsList = findViewById(R.id.stopsList);
        stops = MainActivity.getStops();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, stops)
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
                //to get stop name: ((TextView)view).getText().toString();
                Intent intent = new Intent(getApplicationContext(), ArrivalsActivity.class);
                intent.putExtra("POSITION", position);
                startActivity(intent);
            }
        });
    }
}
