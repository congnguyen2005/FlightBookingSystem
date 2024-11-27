package com.example.flightbookingsystem.model;

public class GoiYMain {
    private String flightName;
    private String flightTime;
    private String flightPrice;

    public GoiYMain(String flightName, String flightTime, String flightPrice) {
        this.flightName = flightName;
        this.flightTime = flightTime;
        this.flightPrice = flightPrice;
    }

    public String getFlightName() {
        return flightName;
    }

    public String getFlightTime() {
        return flightTime;
    }

    public String getFlightPrice() {
        return flightPrice;
    }
}
