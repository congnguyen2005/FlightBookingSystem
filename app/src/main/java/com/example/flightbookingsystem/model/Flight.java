package com.example.flightbookingsystem.model;

public class Flight {
    private String flightId;         // Mã chuyến bay
    private String airline;          // Hãng hàng không
    private String departure;        // Điểm khởi hành
    private String destination;      // Điểm đến
    private String departureDate;    // Ngày khởi hành
    private String departureTime;    // Thời gian khởi hành
    private String arrivalTime;      // Thời gian đến
    private double price;            // Giá vé
    private String stopPoints;       // Điểm dừng (nếu có)
    private String flightDuration;   // Thời gian bay

    public Flight() {
    }

    public Flight(String flightId, String airline, String departure, String destination, String departureDate, String departureTime, String arrivalTime, double price, String stopPoints, String flightDuration) {
        this.flightId = flightId;
        this.airline = airline;
        this.departure = departure;
        this.destination = destination;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.price = price;
        this.stopPoints = stopPoints;
        this.flightDuration = flightDuration;
    }

    public String getFlightId() {
        return flightId;
    }

    public void setFlightId(String flightId) {
        this.flightId = flightId;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStopPoints() {
        return stopPoints;
    }

    public void setStopPoints(String stopPoints) {
        this.stopPoints = stopPoints;
    }

    public String getFlightDuration() {
        return flightDuration;
    }

    public void setFlightDuration(String flightDuration) {
        this.flightDuration = flightDuration;
    }
}
