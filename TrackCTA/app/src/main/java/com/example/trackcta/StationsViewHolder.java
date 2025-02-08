package com.example.trackcta;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class StationsViewHolder extends RecyclerView.ViewHolder
{
    // Protected variables since adapter will require these views to bind
    protected TextView textViewMainStationName, textViewLineColor1, textViewLineColor2, textViewLineColor3,
            textViewLineColor4, textViewLineColor5, textViewLineColor6;

    public StationsViewHolder(@NonNull View itemView)
    {
        super(itemView);
        textViewMainStationName = itemView.findViewById(R.id.textViewMainStationName);
        textViewLineColor1 = itemView.findViewById(R.id.textViewLineColor1);
        textViewLineColor2 = itemView.findViewById(R.id.textViewLineColor2);
        textViewLineColor3 = itemView.findViewById(R.id.textViewLineColor3);
        textViewLineColor4 = itemView.findViewById(R.id.textViewLineColor4);
        textViewLineColor5 = itemView.findViewById(R.id.textViewLineColor5);
        textViewLineColor6 = itemView.findViewById(R.id.textViewLineColor6);



    }
}
