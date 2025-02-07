package com.example.trackcta;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.CountDownTimer;
import android.util.Log;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import android.widget.TextView;

/*
    Shows live-tracking feature based on estimated time of arrival
*/


public class VisualizerActivity extends AppCompatActivity
{
    private TextView textView50, textView75;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.visualizer_activity);

        textView50 = findViewById(R.id.textView50);
        textView75 = findViewById(R.id.textView75);


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


        //test countdown
        seconds = 30000;
        int quarter = seconds / 3;
        int countDownInterval = 1000;

        new CountDownTimer(seconds, countDownInterval)
        {
            int sum = 0;

            public void onTick(long millisUntilFinished) {
                textView50.setText("seconds remaining: " + millisUntilFinished / 1000);
                sum += countDownInterval;
                if(sum == quarter) {
                    Log.d("QUARTER", String.valueOf(millisUntilFinished));
                    sum = 0;
                }
            }

            public void onFinish() {
                textView75.setText("done!");
            }
        }.start();

        //convertFromMilitary(currentTime);

    }
}
