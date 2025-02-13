package com.example.trackcta;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.content.Intent;
import android.os.PersistableBundle;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

/*
    Shows arrival times for the next hour at a single stop
*/

public class ArrivalsActivity extends AppCompatActivity implements View.OnClickListener
{
    private Intent intent;
    protected RecyclerView recyclerView;
    private ArrivalsAdapter ap;
    protected static ArrayList<ArrivalsInfo> alt = new ArrayList<>();
    private int ID, RES;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arrivals);

        intent = getIntent();
        String stopID = intent.getStringExtra("stopID"),
               stopName = intent.getStringExtra("stopName"),
               stationName = intent.getStringExtra("stationName");

        getWindow().getDecorView().setBackgroundColor(Color.BLACK);
        getSupportActionBar().setTitle(stopName + " (" + stationName + ")");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#000000")));

        ID= -1;
        try
        {
            ID = Integer.parseInt(stopID);
        } catch (NumberFormatException e)
        {
            throw new RuntimeException(e);
        }
        if(ID == -1)
            ID = RES;

        ArrivalsAPI.call(this, ID);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ap = new ArrivalsAdapter(this, alt);
        recyclerView.setAdapter(ap);
    }


    @Override
    public void onClick(View view)
    {
        ArrivalsInfo info = alt.get(recyclerView.getChildLayoutPosition(view));
        String showTitle = getSupportActionBar().getTitle().toString();

        Intent intent = new Intent(this, VisualizerActivity.class);
        intent.putExtra("RUN", info.getRun());
        intent.putExtra("ARRIVAL", info.getArrivalTime());
        intent.putExtra("stopName", showTitle);
        startActivity(intent);
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState)
    {
        outState.putInt("POSITION", ID);
        super.onSaveInstanceState(outState, outPersistentState);
    }


    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState)
    {
        super.onRestoreInstanceState(savedInstanceState);
        RES = savedInstanceState.getInt("POSITION");
    }
}
