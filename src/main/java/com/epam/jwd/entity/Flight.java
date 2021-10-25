package com.epam.jwd.entity;

import java.time.LocalDateTime;

public class Flight implements Entity {
    private long id;
    private Aircraft flightAircraft;
    private String flightCallsign;
    private String departureAirport;
    private String destinationAirport;
    private LocalDateTime departureDateTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Aircraft getFlightAircraft() {
        return flightAircraft;
    }

    public void setFlightAircraft(Aircraft flightAircraft) {
        this.flightAircraft = flightAircraft;
    }

    public String getFlightCallsign() {
        return flightCallsign;
    }

    public void setFlightCallsign(String flightCallsign) {
        this.flightCallsign = flightCallsign;
    }

    public String getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(String departureAirport) {
        this.departureAirport = departureAirport;
    }

    public String getDestinationAirport() {
        return destinationAirport;
    }

    public void setDestinationAirport(String destinationAirport) {
        this.destinationAirport = destinationAirport;
    }

    public LocalDateTime getDepartureDateTime() {
        return departureDateTime;
    }

    public void setDepartureDateTime(LocalDateTime departureDateTime) {
        this.departureDateTime = departureDateTime;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", flightAircraft=" + flightAircraft +
                ", flightCallsign='" + flightCallsign + '\'' +
                ", departureAirport='" + departureAirport + '\'' +
                ", destinationAirport='" + destinationAirport + '\'' +
                ", departureDateTime=" + departureDateTime +
                '}';
    }
}
