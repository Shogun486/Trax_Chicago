package com.example.trackcta;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

/*
    Adapter for ArrivalsActivity recycler view
*/


public class ArrivalsAdapter extends RecyclerView.Adapter<ArrivalsViewHolder>
{
    private ArrivalsActivity arrivalsActivity;
    private ArrivalsInfo info;
    private List <ArrivalsInfo> alt;


    ArrivalsAdapter(ArrivalsActivity arrivalsActivity, List <ArrivalsInfo> alt)
    {
        this.arrivalsActivity = arrivalsActivity;
        this.alt = alt;
    }


    @NonNull
    @Override
    public ArrivalsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        // Recognize the entries to populate
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.arrivals_entry, parent, false);
        itemView.setOnClickListener(arrivalsActivity);
        return new ArrivalsViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull ArrivalsViewHolder holder, int position)
    {
        info = alt.get(position);

        String runDisplay = "RUN# ";
        int run = info.getRun();
        runDisplay += run;
        holder.textViewRunNumber.setText(runDisplay);

        String minutesDisplay = "";
        String currentTime = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        String arrivalTime = info.getArrivalTime().split("T")[1];
        minutesDisplay += StationsActivity.getTimeDifference(currentTime, arrivalTime);
        holder.textViewArrivalTime.setText(minutesDisplay);
        holder.textViewRunColor.setBackgroundColor(StationsAdapter.getColorInt(info.getRunColor()));
    }


    @Override
    public int getItemCount() { return alt.size(); }
}

