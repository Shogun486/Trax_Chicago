package com.example.trackcta;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class VisualizerAdapter extends RecyclerView.Adapter<VisualizerViewHolder>
{
    private VisualizerActivity visualizerActivity;
    private VisualizerInfo info;
    private List <VisualizerInfo> alv;

    VisualizerAdapter(VisualizerActivity visualizerActivity, List <VisualizerInfo> alv)
    {
        this.visualizerActivity = visualizerActivity;
        this.alv = alv;
    }

    @NonNull
    @Override
    public VisualizerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.visualizer_entry, parent, false);

        //itemView.setOnClickListener(arrivalsActivity);
        return new VisualizerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull VisualizerViewHolder holder, int position)
    {
        info = alv.get(position);

        String stopArrivalTime = info.getArrivalTime();
        String stationName = info.getStationName();
        holder.textViewStopArrivalTime.setText(stopArrivalTime);
        holder.textViewStationName.setText(stationName);

    }

    @Override
    public int getItemCount() { return alv.size(); }
}
