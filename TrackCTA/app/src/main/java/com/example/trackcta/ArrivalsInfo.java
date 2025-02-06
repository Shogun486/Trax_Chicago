package com.example.trackcta;

import java.io.Serializable;

public class ArrivalsInfo implements Serializable
{
    private String destination, serviceDesc;

    public ArrivalsInfo(String destination, String serviceDesc)
    {
        this.destination = destination;
        this.serviceDesc = serviceDesc;
    }

    public String getDestination() { return destination; }

    public String getServiceDesc() { return serviceDesc; }
}
