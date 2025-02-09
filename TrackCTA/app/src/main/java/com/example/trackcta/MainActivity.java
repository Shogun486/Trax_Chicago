package com.example.trackcta;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

/*
    Opening activity allows user to select station, which will then start
    up a new activity to filter a specific platform/stop at the location
*/

public class MainActivity extends AppCompatActivity
{


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    public void startStationsActivity(View view)
    {
        Intent intent = new Intent(this, StationsActivity.class);
        startActivity(intent);
    }

}
