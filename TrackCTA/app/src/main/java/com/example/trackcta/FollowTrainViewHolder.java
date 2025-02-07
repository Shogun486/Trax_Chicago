package com.example.trackcta;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FollowTrainViewHolder extends RecyclerView.ViewHolder
{
    protected ImageView imageViewArrivalStatus;
    protected TextView textViewStationName, textViewStopArrivalTime;

    public FollowTrainViewHolder(@NonNull View itemView)
    {
        super(itemView);
        imageViewArrivalStatus = itemView.findViewById(R.id.imageViewArrivalStatus); // to-implement: status symbol
        textViewStationName = itemView.findViewById(R.id.textViewStationName);
        textViewStopArrivalTime = itemView.findViewById(R.id.textViewStopArrivalTime);
    }
}
