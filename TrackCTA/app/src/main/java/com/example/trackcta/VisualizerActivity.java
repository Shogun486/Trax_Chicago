package com.example.trackcta;

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
    private ImageView imageView, imageViewTrain, imageView2, imageView6,
            imageView7, imageView8, imageView9, imageView10, imageView12;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.visualizer_activity);

        imageView = findViewById(R.id.imageView);
        imageViewTrain = findViewById(R.id.imageViewTrain);
        imageView2 = findViewById(R.id.imageView2);
        imageView6 = findViewById(R.id.imageView6);
        imageView7 = findViewById(R.id.imageView7);
        imageView10 = findViewById(R.id.imageView10);
        imageView8 = findViewById(R.id.imageView8);
        imageView9 = findViewById(R.id.imageView9);
        imageView12 = findViewById(R.id.imageView12);
        textView = findViewById(R.id.textView);





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
        imageViewTrain.setImageResource(R.drawable.train);

        new CountDownTimer(seconds, countDownInterval)
        {
            int sum = 0;

            public void onTick(long millisUntilFinished)
            {

                textView.setText("seconds remaining: " + millisUntilFinished / 1000);
                sum += countDownInterval;
                if(sum == quarter) {
                    imageView7.setImageResource(R.drawable.check);

                    Log.d("QUARTER", String.valueOf(millisUntilFinished));
                    sum = 0;
                }
            }

            public void onFinish() {
                textView.setText("done!");
            }
        }.start();

        //convertFromMilitary(currentTime);

    }
}
