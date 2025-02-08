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
import java.util.List;
import java.util.Set;

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

        holder.textViewLineColor1.setVisibility(View.GONE);
        holder.textViewLineColor2.setVisibility(View.GONE);
        holder.textViewLineColor3.setVisibility(View.GONE);
        holder.textViewLineColor4.setVisibility(View.GONE);
        holder.textViewLineColor5.setVisibility(View.GONE);
        holder.textViewLineColor6.setVisibility(View.GONE);



        // holder.textViewLineColor.setVisibility(View.INVISIBLE);
        ArrayList <String> temp = new ArrayList<>(StopsAPI.lineColors.get(stationName));
        int colorAmount = temp.size();
        String v = stationName + ": " + temp.toString() + " " + colorAmount;
        Log.d("verify", v);
        for(int i = 0; i < colorAmount; i++)
        {
            Log.d("checkAmount", String.valueOf(stationName + " " + colorAmount));
            String color = temp.get(i);
            if(i == 0)
            {
                holder.textViewLineColor1.setVisibility(View.VISIBLE);
                holder.textViewLineColor1.setText(color);
                holder.textViewLineColor1.setTextColor(Color.WHITE);
                holder.textViewLineColor1.setBackgroundColor(getColorInt(color));

            }
            else if(i == 1)
            {
                holder.textViewLineColor2.setVisibility(View.VISIBLE);

                holder.textViewLineColor2.setText(color);
                holder.textViewLineColor2.setTextColor(Color.WHITE);
                holder.textViewLineColor2.setBackgroundColor(getColorInt(color));
            }
            else if(i == 2)
            {
                holder.textViewLineColor3.setVisibility(View.VISIBLE);

                holder.textViewLineColor3.setText(color);
                holder.textViewLineColor3.setTextColor(Color.WHITE);
                holder.textViewLineColor3.setBackgroundColor(getColorInt(color));
            }
            else if(i == 3)
            {
                holder.textViewLineColor4.setVisibility(View.VISIBLE);

                holder.textViewLineColor4.setText(color);
                holder.textViewLineColor4.setTextColor(Color.WHITE);
                holder.textViewLineColor4.setBackgroundColor(getColorInt(color));
            }
            else if(i == 4)
            {
                holder.textViewLineColor5.setVisibility(View.VISIBLE);

                holder.textViewLineColor5.setText(color);
                holder.textViewLineColor5.setTextColor(Color.WHITE);
                holder.textViewLineColor5.setBackgroundColor(getColorInt(color));
            }
            else if(i == 5)
            {
                holder.textViewLineColor6.setVisibility(View.VISIBLE);

                holder.textViewLineColor6.setText(color);
                holder.textViewLineColor6.setTextColor(Color.WHITE);
                holder.textViewLineColor6.setBackgroundColor(getColorInt(color));
            }
        }
        //return;
        Log.d("lineColors", StopsAPI.lineColors.get(stationName).toString());


        //holder.textViewLineColor2.setText(color);


/*
        String color = info.getColor();
        holder.textViewLineColor1.setText(color);
        holder.textViewLineColor1.setTextColor(Color.WHITE);
        holder.textViewMainStationName.setTextColor(Color.rgb(63, 141, 145));

        if(color.equals("Red"))
        {
            holder.textViewLineColor1.setBackgroundColor(Color.RED);
        }
        else if(color.equals("Blue"))
        {
            holder.textViewLineColor1.setBackgroundColor(Color.BLUE);
        }
        else if(color.equals("Green"))
        {
            holder.textViewLineColor1.setBackgroundColor(Color.rgb(55, 155, 50));
        }
        else if(color.equals("Brown"))
        {
            holder.textViewLineColor1.setBackgroundColor(Color.rgb(150, 75, 0));
        }
        else if(color.equals("Purple"))
        {
            holder.textViewLineColor1.setBackgroundColor(Color.rgb(90, 20, 150));
        }
        else if(color.equals("Yellow"))
        {
            holder.textViewLineColor1.setBackgroundColor(Color.rgb(180, 190, 5));
        }
        else if(color.equals("Pink"))
        {
            holder.textViewLineColor1.setBackgroundColor(Color.rgb(168, 74, 127));
        }
        else if(color.equals("Orange"))
        {
            holder.textViewLineColor1.setBackgroundColor(Color.rgb(209, 137, 4));

        }*/
    }

   private int getColorInt(String color)
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
