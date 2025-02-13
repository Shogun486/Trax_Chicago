package com.example.trackcta;

import java.io.Serializable;

/*

    This class contains information that will be displayed in ArrivalsActivity

*/

public class ArrivalsInfo implements Serializable
{
    private String arrivalTime;
    private int run;
    private String runColor;


    public ArrivalsInfo(String arrivalTime, int run, String runColor)
    {
        this.arrivalTime = arrivalTime;
        this.run = run;
        this.runColor = runColor;
    }


    public String getArrivalTime() { return arrivalTime; }


    public int getRun() { return run; }

    public String getRunColor() { return runColor; }
}
