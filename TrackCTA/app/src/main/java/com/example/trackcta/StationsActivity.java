package com.example.trackcta;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
    Allows user to select station, which will then start
    up a new activity to filter a specific platform/stop
*/

public class StationsActivity extends AppCompatActivity
{
    public static List<String> stops = new ArrayList<>();
    private static Map<String, String> stopColors = new HashMap<>();
    public static List <Integer> ids = new ArrayList<>();
    private static int differenceInHours, differenceInMinutes, differenceInSeconds;
    protected static RecyclerView recyclerViewMain;
    protected static ArrayList<String> al = new ArrayList<>();
    protected static StationsAdapter sa;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stations);

        Intent intent = getIntent();
        getWindow().getDecorView().setBackgroundColor(Color.BLACK);
        getSupportActionBar().setTitle("Which station are you using today?");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#000000")));

        stops.clear();
        stopColors.clear();

        recyclerViewMain = findViewById(R.id.recyclerViewMain);
        recyclerViewMain.setLayoutManager(new LinearLayoutManager(this));
        sa = new StationsAdapter(this, al);
        recyclerViewMain.setAdapter(sa);

        StopsAPI.stationToStopIDs.clear();
        StopsAPI sapi = new StopsAPI();
        sapi.call(this);
    }


    public void onClick(View view)
    {
        String stationName = al.get(recyclerViewMain.getChildLayoutPosition(view));
        Log.d("checking", stationName);

        stops.clear();
        stopColors.clear();
        ids.clear();

        Intent intent = new Intent(this, StopsActivity.class);
        intent.putExtra("stationName", stationName);
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
}
