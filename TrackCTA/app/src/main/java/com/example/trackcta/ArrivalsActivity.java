package com.example.trackcta;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.os.PersistableBundle;
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
    private int POS, RES;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arrivals);

        intent = getIntent();
        //int position = intent.getIntExtra("POSITION", -1);
        POS = intent.getIntExtra("POSITION", -1);
        Log.d("POS", String.valueOf(POS));

        if(POS == -1)
            POS = RES;

        ArrivalsAPI.call(this, MainActivity.getID(POS));

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ap = new ArrivalsAdapter(this, alt);
        recyclerView.setAdapter(ap);
    }



@Override
public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
    outState.putInt("POSITION", POS);
    super.onSaveInstanceState(outState, outPersistentState);
    Log.d("POS", String.valueOf(POS));
}

@Override
protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
    super.onRestoreInstanceState(savedInstanceState);
    RES = savedInstanceState.getInt("POSITION");
    Log.d("RES", String.valueOf(RES));
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
