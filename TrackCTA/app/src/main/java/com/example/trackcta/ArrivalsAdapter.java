package com.example.trackcta;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
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

        //itemView.setOnClickListener(mainActivity);
        return new ArrivalsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ArrivalsViewHolder holder, int position)
    {
        info = alt.get(position);

        holder.textViewStopDest.setText(info.getDestination());
        holder.textViewArrivalTime.setText(info.getServiceDesc());
    }

    @Override
    public int getItemCount() { return alt.size(); }
}

