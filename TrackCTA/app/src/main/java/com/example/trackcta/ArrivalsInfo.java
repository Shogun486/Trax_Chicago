package com.example.trackcta;

import java.io.Serializable;

public class ArrivalsInfo implements Serializable
{
    private String arrivalTime;
    private int run;


    public ArrivalsInfo(String arrivalTime, int run)
    {
        this.arrivalTime = arrivalTime;
        this.run = run;
    }


    public String getArrivalTime() { return arrivalTime; }


    public int getRun() { return run; }
}
