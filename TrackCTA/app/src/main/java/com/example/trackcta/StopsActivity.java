package com.example.trackcta;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.ArrayList;

/*

    Shows all stops (and their colors) for a given station

 */

public class StopsActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stops);

        Intent intent = getIntent();
        String stationName = intent.getStringExtra("stationName");

        getWindow().getDecorView().setBackgroundColor(Color.BLACK);
        getSupportActionBar().setTitle(stationName);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#000000")));

        String showTitle = getSupportActionBar().getTitle().toString();
        ArrayList <String> stopIDs = StopsAPI.stationToStopIDs.get(stationName);
        final ArrayList<StopDisplayInfo> arrayList = new ArrayList<StopDisplayInfo>();

        for(String ID: stopIDs)
        {
            String stop = StopsAPI.stopIDtoName.get(ID);
            arrayList.add(new StopDisplayInfo(stop, ID));
        }

        StopsViewAdapter numbersArrayAdapter = new StopsViewAdapter(this, arrayList);
        ListView numbersListView = findViewById(R.id.stopsList);

        numbersListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                StopDisplayInfo info = arrayList.get(position);
                String stopID = info.getStopID();
                String stopName = info.getStopName();

                Intent intent = new Intent(getApplicationContext(), ArrivalsActivity.class);
                intent.putExtra("stopID", stopID);
                intent.putExtra("stopName", getProperStopDisplay(stopName));
                intent.putExtra("stationName", showTitle);
                startActivity(intent);
            }
        });

        numbersListView.setAdapter(numbersArrayAdapter);
    }


    public static String getProperStopDisplay(String text)
    {
        StringBuilder sb = new StringBuilder(text);
        String park = "", bound = "";
        for(int i = 0; i < sb.length(); i++)
        {
            park += sb.charAt(i);
            Log.d("park", park);
            if(park.contains("Pk"))
            {
                sb.replace(i-2,i+1," Park");
                park = "";
            }

            bound += sb.charAt(i);
            if(bound.contains("bound")) {
                sb.delete(i - 5, i + 1);
                sb.deleteCharAt(sb.length() - 1);
                break;
            }

            if(i+1 != sb.length())
            {
                if (sb.charAt(i + 1) == '(')
                {

                    sb.setCharAt(i, 'X');
                    sb.deleteCharAt(i + 1);

                }
                else if(sb.charAt(i + 1) == ')')
                {
                    sb.deleteCharAt(i+1);
                }
            }
        }

        text = "";
        String toSplit = sb.toString();
        String [] arr = toSplit.split("X");
        for(int c  = 0; c < arr.length; c++)
        {
            if(c == arr.length - 1 && arr.length != 2)
                text += "\nTo ";
            if(c != 0)
                text += arr[c];
        }

        return text;
    }
}
