package com.example.trackcta;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class ArrivalsActivity extends AppCompatActivity
{
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arrivals);
        intent = getIntent();
        int x = intent.getIntExtra("ID", -1);
        Log.d("REC", String.valueOf(x));

    }


}
