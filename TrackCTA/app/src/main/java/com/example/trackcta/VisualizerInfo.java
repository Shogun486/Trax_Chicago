package com.example.trackcta;

import java.io.Serializable;

public class VisualizerInfo implements Serializable
{
    private String stationName, arrivalTime;

    public VisualizerInfo(String stationName, String arrivalTime)
    {
        this.stationName = stationName;
        this.arrivalTime = arrivalTime;
    }

    public String getArrivalTime() { return arrivalTime; }

    public String getStationName() { return stationName; }
}
