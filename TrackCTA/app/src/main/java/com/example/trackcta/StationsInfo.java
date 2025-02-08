package com.example.trackcta;


public class StationsInfo
{
    private String color, stationName;

    public StationsInfo(String color, String stationName)
    {
        this.color = color;
        this.stationName = stationName;
    }

    public String getColor() { return color; }

    public String getStationName() { return stationName; }
}
