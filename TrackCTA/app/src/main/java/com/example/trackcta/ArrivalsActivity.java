package com.example.trackcta;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.content.Intent;


public class ArrivalsActivity extends AppCompatActivity
{
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arrivals);

        intent = getIntent();
        //int ID_index = intent.getIntExtra("ID", -1);
    }

    public static void updateName()
    {
        Log.d("TESTING", ArrivalsAPI.name);
    }

}
