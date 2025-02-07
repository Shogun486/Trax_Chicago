package com.example.trackcta;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.content.Intent;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/*
    To-implement
 */

public class FollowTrainActivity extends AppCompatActivity
{
    private int run;
    private String arrivalTime;
    private FollowTrainAdapter va;
    protected RecyclerView recyclerViewVisualizer;
    protected static ArrayList<FollowTrainInfo> alv = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_follow_train);

        Intent intent = getIntent();
        run = intent.getIntExtra("RUN", -1);

        // Arrival Time (military)
        arrivalTime = intent.getStringExtra("ARRIVAL").split("T")[1];
        Log.d("arrivalTime", arrivalTime);

        // Current Time (military)
        String currentTime = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        Log.d("currentMilitary", currentTime);
        //convertFromMilitary(currentTime);

        //getMinuteDifference(currentTime, arrivalTime);
        FollowTrainAPI.call(this, run);

        recyclerViewVisualizer = findViewById(R.id.recyclerViewVisualizer);
        recyclerViewVisualizer.setLayoutManager(new LinearLayoutManager(this));
        va = new FollowTrainAdapter(this, alv);
        recyclerViewVisualizer.setAdapter(va);

        Log.d("VISUALIZER", String.valueOf(run));
        Log.d("VISUALIZER", arrivalTime);
    }
}
