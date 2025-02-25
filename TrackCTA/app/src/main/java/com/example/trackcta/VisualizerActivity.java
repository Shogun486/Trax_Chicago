package com.example.trackcta;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.CountDownTimer;
import android.util.Log;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import android.widget.ImageView;
import android.widget.TextView;

/*
    Shows live-tracking feature based on estimated time of arrival
*/


public class VisualizerActivity extends AppCompatActivity
{
    private ImageView imageViewTrain, imageViewS1, imageViewS2, imageViewS3,
            imageViewS4, imageViewS5, imageViewS6, imageViewS7, imageViewS8;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.visualizer_activity);

        imageViewTrain = findViewById(R.id.imageViewTrain);
        textView = findViewById(R.id.textView);
        imageViewS1 = findViewById(R.id.imageViewS1);
        imageViewS2 = findViewById(R.id.imageViewS2);
        imageViewS3 = findViewById(R.id.imageViewS3);
        imageViewS4 = findViewById(R.id.imageViewS4);
        imageViewS5 = findViewById(R.id.imageViewS5);
        imageViewS6 = findViewById(R.id.imageViewS6);
        imageViewS7 = findViewById(R.id.imageViewS7);
        imageViewS8 = findViewById(R.id.imageViewS8);

        Intent intent = getIntent();
        int run = intent.getIntExtra("RUN", -1);
        String stopName = intent.getStringExtra("stopName");

        getWindow().getDecorView().setBackgroundColor(Color.BLACK);
        getSupportActionBar().setTitle("RUN# " + run + " - " + stopName);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#000000")));

        // Arrival Time (military)
        String arrivalTime = intent.getStringExtra("ARRIVAL").split("T")[1];

        // Current Time (military)
        String currentTime = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));

        int minutesDifference = StationsActivity.getMinutesDifference(currentTime, arrivalTime);
        int secondsDifference = StationsActivity.getSecondsDifference(currentTime, arrivalTime);
        int seconds = (minutesDifference * 60) + secondsDifference;
        if(StationsActivity.hourWait())
            seconds += 3600;

        Log.d("SECONDS", String.valueOf(seconds * 1000));


        seconds *= 1000;
        int eighth = (seconds) / 8;
        Log.d("eighth", String.valueOf(eighth));
        int countDownInterval = 1000;
        imageViewTrain.setImageResource(R.drawable.train);

        new CountDownTimer(seconds, countDownInterval)
        {
            int sum = 0;
            public void onTick(long millisUntilFinished)
            {

                textView.setText("Arriving in " + millisUntilFinished / 1000 + " seconds" );
                sum += countDownInterval;
                Log.d("sum", String.valueOf(sum));

                if(sum >= eighth * 7)
                {
                    imageViewS7.setImageResource(R.drawable.check);
                }
                else if(sum >= eighth * 6)
                {
                    imageViewS6.setImageResource(R.drawable.check);
                }
                else if(sum >= eighth * 5)
                {
                    imageViewS5.setImageResource(R.drawable.check);
                 }
                else if(sum >= eighth * 4)
                {
                    imageViewS4.setImageResource(R.drawable.check);
                }
                else if(sum >= eighth * 3)
                {
                    imageViewS3.setImageResource(R.drawable.check);
                }
                else if(sum >= eighth * 2)
                {
                    imageViewS2.setImageResource(R.drawable.check);
                }
                else if(sum >= eighth * 1)
                {
                    imageViewS1.setImageResource(R.drawable.check);
                }
            }

            public void onFinish()
            {
                imageViewS8.setImageResource(R.drawable.check);
                textView.setText("Train is due!");
            }
        }.start();
    }
}
