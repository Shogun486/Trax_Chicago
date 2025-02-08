package com.example.trackcta;

import android.graphics.Color;
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
        holder.textViewLineColor.setTextColor(Color.WHITE);

        if(color.equals("Red"))
        {
            holder.textViewLineColor.setBackgroundColor(Color.RED);
        }
        else if(color.equals("Blue"))
        {
            holder.textViewLineColor.setBackgroundColor(Color.BLUE);
        }
        else if(color.equals("Green"))
        {
            holder.textViewLineColor.setBackgroundColor(Color.rgb(55, 155, 50));
        }
        else if(color.equals("Brown"))
        {
            holder.textViewLineColor.setBackgroundColor(Color.rgb(150, 75, 0));
        }
        else if(color.equals("Purple"))
        {
            holder.textViewLineColor.setBackgroundColor(Color.rgb(90, 20, 150));
        }
        else if(color.equals("Yellow"))
        {
            holder.textViewLineColor.setBackgroundColor(Color.rgb(180, 190, 5));
        }
        else if(color.equals("Pink"))
        {
            holder.textViewLineColor.setBackgroundColor(Color.rgb(168, 74, 127));
        }
        else if(color.equals("Orange"))
        {
            holder.textViewLineColor.setBackgroundColor(Color.rgb(209, 137, 4));

        }
    }

    @Override
    public int getItemCount() { return al.size(); }
}
