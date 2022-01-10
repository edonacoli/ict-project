package com.example.test.Models;


public class Reservation {
    private String flightNumber;
    private String passengerId;
    private String passengerName;
    private String seatNumber;

    public Reservation(String flightNumber, String passengerId, String passengerName, String seatNumber) {
        this.flightNumber = flightNumber;
        this.passengerId = passengerId;
        this.passengerName = passengerName;
        this.seatNumber = seatNumber;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flight) {
        flightNumber = flight;
    }

    public String getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(String passengerId) {
        this.passengerId = passengerId;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }
}
