package com.example.trackcta;

import java.io.Serializable;

public class FollowTrainInfo implements Serializable
{
    private String stationName, arrivalTime;

    public FollowTrainInfo(String stationName, String arrivalTime)
    {
        this.stationName = stationName;
        this.arrivalTime = arrivalTime;
    }

    public String getArrivalTime() { return arrivalTime; }

    public String getStationName() { return stationName; }
}
