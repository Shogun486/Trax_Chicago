package com.example.trackcta;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.content.Intent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/*
    To-implement
 */

public class VisualizerActivity extends AppCompatActivity
{
    private int run;
    private String arrivalTime;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizer);

        Intent intent = getIntent();
        run = intent.getIntExtra("RUN", -1);

        // Arrival Time (military)
        arrivalTime = intent.getStringExtra("ARRIVAL").split("T")[1];
        Log.d("arrivalTime", arrivalTime);

        // Current Time (military)
        String currentTime = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        Log.d("currentMilitary", currentTime);
        convertFromMilitary(currentTime);

        getMinuteDifference(currentTime, arrivalTime);
        //FollowTrainAPI.call(this, run);

        Log.d("VISUALIZER", String.valueOf(run));
        Log.d("VISUALIZER", arrivalTime);
    }


    private static void convertFromMilitary(String time)
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

    private static void getMinuteDifference(String now, String future)
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

        long differenceInMilliSeconds = Math.abs(d2.getTime() - d1.getTime()),
                differenceInHours = (differenceInMilliSeconds / (60 * 60 * 1000)) % 24,
                differenceInMinutes = (differenceInMilliSeconds / (60 * 1000)) % 60,
                differenceInSeconds = (differenceInMilliSeconds / 1000) % 60;

        Log.d("DIFFERENCE",
                "Difference is " + differenceInHours + " hours "
                        + differenceInMinutes + " minutes "
                        + differenceInSeconds + " seconds. \n" + now + "\n" + future);
    }
}
