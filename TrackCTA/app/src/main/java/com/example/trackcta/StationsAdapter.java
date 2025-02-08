package com.example.trackcta;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class StationsAdapter extends RecyclerView.Adapter<StationsViewHolder>

{
    private MainActivity mainActivity;
    private StationsInfo info;
    private List<StationsInfo> al;

    StationsAdapter(MainActivity mainActivity, List <StationsInfo> al)
    {
        this.mainActivity = mainActivity;
        this.al = al;
    }

    @Override
    public StationsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        // Recognize the entries to populate
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.stations_entry, parent, false);

        //itemView.setOnClickListener((View.OnClickListener) mainActivity);
        return new StationsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull StationsViewHolder holder, int position)
    {
        info = al.get(position);

        String stationName = info.getStationName();
        holder.textViewMainStationName.setText(stationName);

        String color = info.getColor();
        holder.textViewLineColor.setText(color);
    }

    @Override
    public int getItemCount() { return al.size(); }
}
