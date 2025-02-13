package com.example.trackcta;

/*

    Contains information about each stop that
    will be useful when displaying name and colors
    in StopsActivity

 */

public class StopDisplayInfo
{
    private String stopName, stopID;

    public StopDisplayInfo(String stopName, String stopID) {
        this.stopName = stopName;
        this.stopID = stopID;
    }


    public String getStopName() { return stopName; }

    public String getStopID() { return stopID; }
}
