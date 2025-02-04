package com.example.trackcta;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

/*

    Shows all stops for a given station

*/

public class StopsDisplayActivity extends AppCompatActivity
{
    private ListView stopsList;
    private Intent intent;
    private ArrayAdapter<String> adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_stops);

        intent = getIntent();
        String stationName = intent.getStringExtra("stationName");
        stopsList = findViewById(R.id.stopsList);


        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, MainActivity.stops)
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

    }
}
