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

        ArrayList <String> stopIDs = StopsAPI.stationToStopIDs.get(stationName);

        final ArrayList<NumbersView> arrayList = new ArrayList<NumbersView>();

        // display all: belmont(kimball-linden), Arrival

        //int max = 0;
        for(String station: StopsAPI.stationToStopIDs.keySet())
        {
            for(String stopID: StopsAPI.stationToStopIDs.get(station))
            {
                String stop = StopsAPI.stopIDtoName.get(stopID);
                //max = Math.max(StopsAPI.stopToColors.get(stop).size(), max);
                int len = station.length();


                if(station.equals("Harlem/Lake"))
                {
                    len = 6;
                }
                else if(stop.contains("H.W. Library"))
                {
                    String s = "H.W. Library";
                    len = s.length();
                }
                else if (stop.contains("South Blvd"))
                {
                    String s = "South Blvd";
                    len = s.length();
                }
                else if (stop.contains("35-Bronzeville-IIT"))
                {
                    String s = "35-Bronzeville-IIT";
                    len = s.length();
                }
                else if(!stop.contains(station))
                {
                    len = 0;

                }
                else {
                    String temp = stop.substring(len, stop.length());
                    if(temp.charAt(0) == ('/'))
                    {
                       int length = temp.split(" ")[0].length();
                       len += length;

                    }

                }
                String display = stop.substring(len, stop.length());
                String show = "";
                if(display.contains("branch") || display.contains("Branch")) {
                    show = display;
                }
                else{
                    show = parseDestinationBound(station, display);
                }

                Log.d("allStops", station + "---->" + show);
            }

        }




        //display all
        for(String ID: stopIDs)
        {
            String stop = StopsAPI.stopIDtoName.get(ID);
            //String bound = parseDestinationBound(stop);
            //Log.d("bound", bound);

            arrayList.add(new NumbersView(R.drawable.train, stop, ID));

            //Log.d("stopColors", StopsAPI.stopToColors.get(stop).toString());

        }

//Log.d("colorMax", String.valueOf(max));
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

    public String parseDisplay(String display)
    {
        String [] arr = display.split("[(].*[)] ");
        String toReturn = "";
        for(int i = 0; i < arr.length; i++)
        {
            String s = arr[i];
            if (!s.matches(".*[a-z].*"))
                continue;
            toReturn += " ITEM: " + s;

            /*
            toReturn += s.substring(1, s.length());
            if(i != arr.length-1)
                toReturn += "\n";
                */

        }
        Log.d("parseDisplay", toReturn);
        return toReturn;
    }

    public static String parseDestinationBound(String station, String stop)
    {
        if(stop.equals("Harlem (Forest Pk Branch) (O'Hare-bound)"))
        {//|| stop.equals("Harlem (O'Hare Branch) (O'Hare-bound)")) {
            return "O'Hare\n(Forest Park Branch)";

        }
        else if (stop.equals("Harlem (O'Hare Branch) (O'Hare-bound)"))
        {
            return "O'Hare\n(O'Hare Branch)";
        }


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
            /*
            else if((stop.contains("branch") || stop.contains("Branch")) && (stop.contains("bound") || stop.contains("Bound")))
            {
                String [] arr = stop.split("[(]");
                for(String s: arr)
                {
                    String branch = "", dest = "";
                    Log.d("separate", station + "--->" + s + " " + stop);
                    if(s.contains("branch") || s.contains("Branch"))
                    {
                        branch = "BRANCH: " + s.split(" ")[0];
                        Log.d("BRANCH", s);
                        Log.d("BRANCH", branch);
                    }

                    if(s.contains("bound") || s.contains("Bound"))
                    {
                        dest = s.split("-")[0];
                        Log.d("BOUND", s);
                        Log.d("BOUND", dest);
                    }
                    bound = branch + "\n" + dest;
                }
            }*/
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
