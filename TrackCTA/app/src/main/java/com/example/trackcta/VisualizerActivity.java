package com.example.trackcta;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.content.Intent;

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
        arrivalTime = intent.getStringExtra("ARRIVAL");

        Log.d("VISUALIZER", String.valueOf(run));
        Log.d("VISUALIZER", arrivalTime);
    }
}
