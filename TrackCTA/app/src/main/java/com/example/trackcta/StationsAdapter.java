package com.example.trackcta;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
    Adapter for RecyclerView in MainActivity
 */

public class StationsAdapter extends RecyclerView.Adapter<StationsViewHolder>

{
    private StationsActivity mainActivity;
    //private StationsInfo info;
    private List<String> al;
    private Set <String> colors = new HashSet<>();



    StationsAdapter(StationsActivity mainActivity, List <String> al)
    {
        this.mainActivity = mainActivity;
        this.al = al;
    }


    @Override
    public StationsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        // Recognize the entries to populate
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.stations_entry, parent, false);
        return new StationsViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull StationsViewHolder holder, int position) {
        //info = al.get(position);
        String stationName = al.get(position);

        holder.textViewMainStationName.setText(stationName);
        holder.textViewLineColor1.setVisibility(View.GONE);
        holder.textViewLineColor2.setVisibility(View.GONE);
        holder.textViewLineColor3.setVisibility(View.GONE);
        holder.textViewLineColor4.setVisibility(View.GONE);
        holder.textViewLineColor5.setVisibility(View.GONE);
        holder.textViewLineColor6.setVisibility(View.GONE);


        colors.clear();
        List<String> stops = StopsAPI.stationToStops.get(stationName);


        if (StopsAPI.stationToStops != null && StopsAPI.stopToColors != null)
        {
            Log.d("INSIDE", "");
            for (String stop : stops)
            {
                for (String color : StopsAPI.stopToColors.get(stop)) {
                    if (!colors.contains(color))
                        colors.add(color);

                }
            }

            ArrayList<String> temp = new ArrayList<>(colors);
            for (int i = 0; i < temp.size(); i++) {
                String color = temp.get(i);
                if (i == 0) {
                    holder.textViewLineColor1.setVisibility(View.VISIBLE);
                    holder.textViewLineColor1.setText(color);
                    holder.textViewLineColor1.setTextColor(Color.WHITE);
                    holder.textViewLineColor1.setBackgroundColor(getColorInt(color));
                } else if (i == 1) {
                    holder.textViewLineColor2.setVisibility(View.VISIBLE);
                    holder.textViewLineColor2.setText(color);
                    holder.textViewLineColor2.setTextColor(Color.WHITE);
                    holder.textViewLineColor2.setBackgroundColor(getColorInt(color));
                } else if (i == 2) {
                    holder.textViewLineColor3.setVisibility(View.VISIBLE);
                    holder.textViewLineColor3.setText(color);
                    holder.textViewLineColor3.setTextColor(Color.WHITE);
                    holder.textViewLineColor3.setBackgroundColor(getColorInt(color));
                } else if (i == 3) {
                    holder.textViewLineColor4.setVisibility(View.VISIBLE);
                    holder.textViewLineColor4.setText(color);
                    holder.textViewLineColor4.setTextColor(Color.WHITE);
                    holder.textViewLineColor4.setBackgroundColor(getColorInt(color));
                } else if (i == 4) {
                    holder.textViewLineColor5.setVisibility(View.VISIBLE);
                    holder.textViewLineColor5.setText(color);
                    holder.textViewLineColor5.setTextColor(Color.WHITE);
                    holder.textViewLineColor5.setBackgroundColor(getColorInt(color));
                } else if (i == 5) {
                    holder.textViewLineColor6.setVisibility(View.VISIBLE);
                    holder.textViewLineColor6.setText(color);
                    holder.textViewLineColor6.setTextColor(Color.WHITE);
                    holder.textViewLineColor6.setBackgroundColor(getColorInt(color));
                }
            }
            Log.d("lineColors", StopsAPI.lineColors.get(stationName).toString());
        }
    }


   public static int getColorInt(String color)
   {
       if(color.equals("Red"))
       {
           return Color.RED;
       }
       else if(color.equals("Blue"))
       {
           return Color.BLUE;
       }
       else if(color.equals("Green"))
       {
           return Color.rgb(55, 155, 50);
       }
       else if(color.equals("Brown"))
       {
           return Color.rgb(150, 75, 0);
       }
       else if(color.equals("Purple"))
       {
           return Color.rgb(90, 20, 150);
       }
       else if(color.equals("Yellow"))
       {
           return Color.rgb(180, 190, 5);
       }
       else if(color.equals("Pink"))
       {
           return Color.rgb(168, 74, 127);
       }
       else if(color.equals("Orange"))
       {
           return Color.rgb(209, 137, 4);

       }
       return -1;
   }


    @Override
    public int getItemCount() { return al.size(); }
}
