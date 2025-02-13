package com.example.trackcta;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;

/*
    Opening/welcoming page will lead user to select a station
*/

public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("Chicago 'L' Tracker & Visualizer");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#000000")));
    }


    public void startStationsActivity(View view)
    {
        Intent intent = new Intent(this, StationsActivity.class);
        startActivity(intent);
        finish();
    }
}
