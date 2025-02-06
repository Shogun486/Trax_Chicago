package com.example.trackcta;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

/*
    Shows arrival times for a single stop
*/

public class ArrivalsActivity extends AppCompatActivity implements View.OnClickListener
{
    private Intent intent;
    protected RecyclerView recyclerView;
    private ArrivalsAdapter ap;
    protected static ArrayList<ArrivalsInfo> alt = new ArrayList<>();


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


    @Override
    public void onClick(View view)
    {
        ArrivalsInfo info = alt.get(recyclerView.getChildLayoutPosition(view));
        Intent intent = new Intent(this, VisualizerActivity.class);
        intent.putExtra("RUN", info.getRun());
        intent.putExtra("ARRIVAL", info.getArrivalTime());
        startActivity(intent);
    }
}
