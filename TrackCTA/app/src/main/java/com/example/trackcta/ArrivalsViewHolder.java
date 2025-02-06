package com.example.trackcta;

import androidx.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

public class ArrivalsViewHolder extends RecyclerView.ViewHolder
{
    // Protected variables since adapter will require these views to bind
    protected ImageView imageViewEntryCTA;
    protected TextView textViewStopDest, textViewArrivalTime;

    public ArrivalsViewHolder(@NonNull View itemView)
    {
        super(itemView);
        imageViewEntryCTA = itemView.findViewById(R.id.imageViewEntryCTA); // to-implement: stop-image
        textViewStopDest = itemView.findViewById(R.id.textViewStopDest);
        textViewArrivalTime = itemView.findViewById(R.id.textViewArrivalTime);
    }
}
