package com.example.trackcta;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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

        itemView.setOnClickListener(arrivalsActivity);
        return new ArrivalsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ArrivalsViewHolder holder, int position)
    {
        info = alt.get(position);

        String runDisplay = "RUN# ";
        int run = info.getRun();
        runDisplay += run;
        holder.textViewRunNumber.setText(runDisplay);

        String minutesDisplay = "";
        String currentTime = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        String arrivalTime = info.getArrivalTime().split("T")[1];
        minutesDisplay += getTimeDifference(currentTime, arrivalTime);
        holder.textViewArrivalTime.setText(minutesDisplay);
    }

    private static void convertFromMilitary(String time)
    {
        int h1 = (int) time.charAt(0) - '0', h2 = (int) time.charAt(1)- '0', hour = h1 * 10 + h2;
        String meridiem = (hour < 12 ? "AM" : "PM");
        StringBuilder display = new StringBuilder("");
        hour %= 12;

        if (hour == 0)
        {
            display.append("12");
            for (int i = 2; i < 8; i++)
                display.append(time.charAt(i));
        }
        else
        {
            display.append(hour);
            for (int i = 2; i < 8; i++)
                display.append(time.charAt(i));
        }

        display.append(" ");
        display.append(meridiem);
        Log.d("currentCST", display.toString());
    }

    private static String getTimeDifference(String now, String future)
    {
        if(now.substring(0,2).equals("23") && future.substring(0,2).equals("00")) // traveling at midnight
        {
            StringBuilder sb = new StringBuilder(now);
            sb.setCharAt(0, '1');
            sb.setCharAt(1,'0');
            now = sb.toString();

            sb = new StringBuilder(future);
            sb.setCharAt(0, '1');
            sb.setCharAt(1,'1');
            future = sb.toString();
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        Date d1, d2;

        try
        {
            d1 = simpleDateFormat.parse(now);
            d2 = simpleDateFormat.parse(future);
        } catch (ParseException e)
        {
            throw new RuntimeException(e);
        }

        long differenceInMilliSeconds = Math.abs(d2.getTime() - d1.getTime()),
                differenceInHours = (differenceInMilliSeconds / (60 * 60 * 1000)) % 24,
                differenceInMinutes = (differenceInMilliSeconds / (60 * 1000)) % 60,
                differenceInSeconds = (differenceInMilliSeconds / 1000) % 60;

        String hoursDisplay = differenceInHours == 0 ? "" : differenceInHours + " hour, ";

        String minutesDisplay = "";
        if(differenceInMinutes == 0)
        {
            minutesDisplay = "";
        }
        else if (differenceInMinutes == 1)
        {
            minutesDisplay = differenceInMinutes + " minute, ";
        }
        else
        {
            minutesDisplay = differenceInMinutes + " minutes, ";
        }

        String secondsDisplay = "";
        if (differenceInSeconds == 1)
        {
            secondsDisplay = differenceInSeconds + " second";
        }
        else
        {
            secondsDisplay = differenceInSeconds + " seconds";
        }

        return hoursDisplay + minutesDisplay + secondsDisplay;
    }

    @Override
    public int getItemCount() { return alt.size(); }
}

