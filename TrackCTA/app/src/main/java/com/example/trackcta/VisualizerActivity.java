package com.example.trackcta;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.util.Log;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


public class VisualizerActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.visualizer_activity);

        Intent intent = getIntent();

        int run = intent.getIntExtra("RUN", -1);

        // Arrival Time (military)
        String arrivalTime = intent.getStringExtra("ARRIVAL").split("T")[1];

        // Current Time (military)
        String currentTime = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));

        int minutesDifference = MainActivity.getMinutesDifference(currentTime, arrivalTime);
        int secondsDifference = MainActivity.getSecondsDifference(currentTime, arrivalTime);
        int seconds = (minutesDifference * 60) + secondsDifference;
        if(MainActivity.hourWait())
            seconds += 3600;

        Log.d("SECONDS", String.valueOf(seconds));
        //convertFromMilitary(currentTime);

    }
}
