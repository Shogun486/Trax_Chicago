package com.example.trackcta;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
    Opening activity allows user to select station, which will then start
    up a new activity to filter a specific platform/stop at the location
*/

public class MainActivity extends AppCompatActivity
{
    private static List<String> stops = new ArrayList<>();
    private static List <Integer> ids = new ArrayList<>();
    private static int differenceInHours, differenceInMinutes, differenceInSeconds;
    protected RecyclerView recyclerViewMain;
    protected static ArrayList<StationsInfo> al = new ArrayList<>();
    private StationsAdapter sa;





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

        recyclerViewMain = findViewById(R.id.recyclerViewMain);
        recyclerViewMain.setLayoutManager(new LinearLayoutManager(this));
        sa = new StationsAdapter(this, al);
        recyclerViewMain.setAdapter(sa);



        /*
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
            */



/*
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
        });*/

    }


    public void onClick(View view)
    {
        StationsInfo i = al.get(recyclerViewMain.getChildLayoutPosition(view));
        Log.d("checking", i.getColor());

        String stationName =i.getStationName();

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


        Intent intent = new Intent(this, StopsActivity.class);
        //intent.putExtra("POLITICIAN", p);;
        startActivity(intent);


    }


    public static void convertFromMilitary(String time)
    {
        int h1 = (int) time.charAt(0) - '0', h2 = (int) time.charAt(1)- '0', hour = h1 * 10 + h2;
        String meridiem = (hour < 12 ? "AM" : "PM");
        StringBuilder display = new StringBuilder("");
        hour %= 12;

        if (hour == 0)
        {
            display.append("12");
            for (int i = 2; i < 8; i++)
                display.append(time.charAt(i));
        }
        else
        {
            display.append(hour);
            for (int i = 2; i < 8; i++)
                display.append(time.charAt(i));
        }

        display.append(" ");
        display.append(meridiem);
        Log.d("currentCST", display.toString());
    }

    public static String getTimeDifference(String now, String future)
    {
        if(now.substring(0,2).equals("23") && future.substring(0,2).equals("00")) // traveling at midnight
        {
            StringBuilder sb = new StringBuilder(now);
            sb.setCharAt(0, '1');
            sb.setCharAt(1,'0');
            now = sb.toString();

            sb = new StringBuilder(future);
            sb.setCharAt(0, '1');
            sb.setCharAt(1,'1');
            future = sb.toString();
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        Date d1, d2;

        try
        {
            d1 = simpleDateFormat.parse(now);
            d2 = simpleDateFormat.parse(future);
        } catch (ParseException e)
        {
            throw new RuntimeException(e);
        }

        long differenceInMilliSeconds = Math.abs(d2.getTime() - d1.getTime());
        differenceInHours = (int) (differenceInMilliSeconds / (60 * 60 * 1000)) % 24;
        differenceInMinutes = (int) (differenceInMilliSeconds / (60 * 1000)) % 60;
        differenceInSeconds = (int) (differenceInMilliSeconds / 1000) % 60;


        String hoursDisplay = differenceInHours == 0 ? "" : differenceInHours + " hour, ";
        String minutesDisplay = "";
        if(differenceInMinutes == 0)
        {
            minutesDisplay = "";
        }
        else if (differenceInMinutes == 1)
        {
            minutesDisplay = differenceInMinutes + " minute, ";
        }
        else
        {
            minutesDisplay = differenceInMinutes + " minutes, ";
        }

        String secondsDisplay = "";
        if (differenceInSeconds == 1)
        {
            secondsDisplay = differenceInSeconds + " second";
        }
        else
        {
            secondsDisplay = differenceInSeconds + " seconds";
        }

        return hoursDisplay + minutesDisplay + secondsDisplay;
    }

    public static int getMinutesDifference(String now, String future)
    {
        getTimeDifference(now, future);
        return differenceInMinutes;
    }

    public static int getSecondsDifference(String now, String future)
    {
        getTimeDifference(now, future);
        return differenceInSeconds;
    }

    public static boolean hourWait() { return differenceInHours == 1; }

    /*
    public static void updateStops()
    {
        for(String station: StopsAPI.getStations())
            sa.add(station);
        listView.deferNotifyDataSetChanged();
    }*/

    public static ArrayList <String> getStops() { return new ArrayList<>(stops); }

    public static int getID(int index) { return ids.get(index); }


}
