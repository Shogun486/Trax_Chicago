package com.example.trackcta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.ArrayList;
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

        for(String stop: stops)
        {
            String stopID = StopsAPI.stopIDs.get(stop);
            arrayList.add(new NumbersView(R.drawable.train, stop, stopID));

        }

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





}
