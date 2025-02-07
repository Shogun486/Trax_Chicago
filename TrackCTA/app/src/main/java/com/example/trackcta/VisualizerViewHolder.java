package com.example.trackcta;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class VisualizerViewHolder extends RecyclerView.ViewHolder
{
    protected ImageView imageViewArrivalStatus;
    protected TextView textViewStationName, textViewStopArrivalTime;

    public VisualizerViewHolder(@NonNull View itemView)
    {
        super(itemView);
        imageViewArrivalStatus = itemView.findViewById(R.id.imageViewArrivalStatus); // to-implement: status symbol
        textViewStationName = itemView.findViewById(R.id.textViewStationName);
        textViewStopArrivalTime = itemView.findViewById(R.id.textViewStopArrivalTime);
    }
}
