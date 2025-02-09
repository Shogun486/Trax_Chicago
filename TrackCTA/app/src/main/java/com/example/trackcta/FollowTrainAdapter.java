package com.example.trackcta;

import androidx.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/*
    Adapter for RecyclerView in FollowTrainActivity
*/

public class FollowTrainAdapter extends RecyclerView.Adapter<FollowTrainViewHolder>
{
    private FollowTrainActivity visualizerActivity;
    private FollowTrainInfo info;
    private List <FollowTrainInfo> alv;


    FollowTrainAdapter(FollowTrainActivity visualizerActivity, List <FollowTrainInfo> alv)
    {
        this.visualizerActivity = visualizerActivity;
        this.alv = alv;
    }


    @NonNull
    @Override
    public FollowTrainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.follow_train_entry, parent, false);
        return new FollowTrainViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull FollowTrainViewHolder holder, int position)
    {
        info = alv.get(position);

        String stopArrivalTime = info.getArrivalTime();
        String stationName = info.getStationName();
        holder.textViewStopArrivalTime.setText(stopArrivalTime);
        holder.textViewStationName.setText(stationName);

        String minutesDisplay = "";
        String currentTime = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        String arrivalTime = info.getArrivalTime().split("T")[1];
        minutesDisplay += StationsActivity.getTimeDifference(currentTime, arrivalTime);
        holder.textViewStopArrivalTime.setText(minutesDisplay);

    }


    @Override
    public int getItemCount() { return alv.size(); }
}
