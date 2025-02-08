package com.example.trackcta;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class StationsViewHolder extends RecyclerView.ViewHolder
{
    // Protected variables since adapter will require these views to bind
    protected ImageView imageViewLineColor;
    protected TextView textViewMainStationName, textViewLineColor;

    public StationsViewHolder(@NonNull View itemView)
    {
        super(itemView);
        imageViewLineColor = itemView.findViewById(R.id.imageViewLineColor); // to-implement: physical stop image
        textViewMainStationName = itemView.findViewById(R.id.textViewMainStationName);
        textViewLineColor = itemView.findViewById(R.id.textViewLineColor);
    }
}
