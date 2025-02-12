package com.example.trackcta;

import androidx.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

public class ArrivalsViewHolder extends RecyclerView.ViewHolder
{
    // Protected variables since adapter will require these views to bind
    protected TextView textViewRunColor;
    protected TextView textViewRunNumber, textViewArrivalTime;


    public ArrivalsViewHolder(@NonNull View itemView)
    {
        super(itemView);
        textViewRunColor = itemView.findViewById(R.id.textViewRunColor);
        textViewRunNumber = itemView.findViewById(R.id.textViewRunNumber);
        textViewArrivalTime = itemView.findViewById(R.id.textViewArrivalTime);
    }
}
