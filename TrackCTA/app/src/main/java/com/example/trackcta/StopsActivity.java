package com.example.trackcta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.example.trackcta.NumbersViewAdapter;

public class StopsActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stops);
        Intent intent = getIntent();
        String stationName = intent.getStringExtra("stationName");
        ArrayList <String> stops = StopsAPI.stationToStops.get(stationName);

        final ArrayList<NumbersView> arrayList = new ArrayList<NumbersView>();

        // display all: belmont(kimball-linden), Arrival
        int max = 0;
        for(String station: StopsAPI.stationToStops.keySet())
        {
            for(String stop: new HashSet<String>(StopsAPI.stationToStops.get(station)))
            {
                max = Math.max(StopsAPI.stopToColors.get(stop).size(), max);
                String boundTest =  parseDestinationBound(stop);
                Log.d("boundTest", String.valueOf(station  + " --> "+ stop + " --> " + boundTest));
            }
        }

        //display all
        for(String stop: new HashSet<String>(stops))
        {
            String stopID = StopsAPI.stopIDs.get(stop);
            String bound = parseDestinationBound(stop);
            Log.d("bound", bound);

            arrayList.add(new NumbersView(R.drawable.train, stop, stopID));

            Log.d("stopColors", StopsAPI.stopToColors.get(stop).toString());

        }

Log.d("colorMax", String.valueOf(max));
        // create a arraylist of the type NumbersView

        // add all the values from 1 to 15 to the arrayList
        // the items are of the type NumbersView
       // arrayList.add(new NumbersView(R.drawable.train, stopID, stop1));
        /*
        arrayList.add(new NumbersView(R.drawable.train, "2", "Two"));
        arrayList.add(new NumbersView(R.drawable.train, "3", "Three"));
        arrayList.add(new NumbersView(R.drawable.train, "4", "Four"));
        arrayList.add(new NumbersView(R.drawable.train, "5", "Five"));
        arrayList.add(new NumbersView(R.drawable.train, "6", "Six"));
        arrayList.add(new NumbersView(R.drawable.train, "7", "Seven"));
        arrayList.add(new NumbersView(R.drawable.train, "8", "Eight"));
        arrayList.add(new NumbersView(R.drawable.train, "9", "Nine"));
        arrayList.add(new NumbersView(R.drawable.train, "10", "Ten"));
        arrayList.add(new NumbersView(R.drawable.train, "11", "Eleven"));
        arrayList.add(new NumbersView(R.drawable.train, "12", "Twelve"));
        arrayList.add(new NumbersView(R.drawable.train, "13", "Thirteen"));
        arrayList.add(new NumbersView(R.drawable.train, "14", "Fourteen"));
        arrayList.add(new NumbersView(R.drawable.train, "15", "Fifteen"));
*/
        // Now create the instance of the NumebrsViewAdapter and pass
        // the context and arrayList created above
        NumbersViewAdapter numbersArrayAdapter = new NumbersViewAdapter(this, arrayList);

        // create the instance of the ListView to set the numbersViewAdapter
        ListView numbersListView = findViewById(R.id.stopsList);

        numbersListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NumbersView o = arrayList.get(position);
                String s = o.getNumbersInText(); //As you are using Default String Adapter
                String t = o.getNumberInDigit();
                Log.d("listclick", t);

                Intent intent = new Intent(getApplicationContext(), ArrivalsActivity.class);
                intent.putExtra("stopID", s);
                startActivity(intent);
            }
        });

        // set the numbersViewAdapter for ListView
        numbersListView.setAdapter(numbersArrayAdapter);
    }

    public static String parseDestinationBound(String stop)
    {
        String [] stopDestInfo = stop.split(" [(]");
        String bound = "";
        for(String info: stopDestInfo)
        {
            if(info.contains("Inner Loop"))
            {
                return "Inner Loop";
            }
            else if(info.contains("Outer Loop"))
            {
                return "Outer Loop";
            }
            else if(info.contains("bound") || info.contains("Bound"))
            {
                info = info.substring(0, info.length() - 1);
                bound = info.split("-")[0];
                String [] infoArr = info.split("-");

                if(bound.equals("Forest Pk"))
                    return "Forest Park";

                String format = "";
                for(int i = 0; i < infoArr.length - 1; i++)
                {
                    format += infoArr[i];

                    if(i == infoArr.length - 2) {
                        continue;
                    }
                    format += "-";
                }
                return format;
            }
            else if(info.contains("arrival") || info.contains("Arrival"))
            {
                info = info.substring(0, info.length() - 1);
                bound = info.split(" ")[0];
                return bound;

            }


        }

        return bound;
    }





}
