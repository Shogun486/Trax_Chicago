package com.example.trackcta;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.content.Intent;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

/*
    Shows arrival times for a single stop
*/

public class ArrivalsActivity extends AppCompatActivity
{
    private Intent intent;
    protected RecyclerView recyclerView;
    private ArrivalsAdapter ap;
    protected static final ArrayList<ArrivalsInfo> alt = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arrivals);

        intent = getIntent();
        int position = intent.getIntExtra("POSITION", -1);
        ArrivalsAPI.call(this, MainActivity.getID(position));

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ap = new ArrivalsAdapter(this, alt);
        recyclerView.setAdapter(ap);
    }
}
