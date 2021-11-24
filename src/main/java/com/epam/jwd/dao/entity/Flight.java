package com.epam.jwd.dao.entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

public class Flight extends Entity {
    private long id;
    private int  flightAircraftId;
    private long brigadeId;
    private int departureAirportId;
    private int destinationAirportId;
    private String flightCallsign;
    private Timestamp departureDateTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getFlightAircraftId() {
        return flightAircraftId;
    }

    public void setFlightAircraftId(int flightAircraftId) {
        this.flightAircraftId = flightAircraftId;
    }

    public long getBrigadeId() {
        return brigadeId;
    }

    public void setBrigadeId(long brigadeId) {
        this.brigadeId = brigadeId;
    }

    public int getDepartureAirportId() {
        return departureAirportId;
    }

    public void setDepartureAirportId(int departureAirportId) {
        this.departureAirportId = departureAirportId;
    }

    public int getDestinationAirportId() {
        return destinationAirportId;
    }

    public void setDestinationAirportId(int destinationAirportId) {
        this.destinationAirportId = destinationAirportId;
    }

    public String getFlightCallsign() {
        return flightCallsign;
    }

    public void setFlightCallsign(String flightCallsign) {
        this.flightCallsign = flightCallsign;
    }

    public Timestamp getDepartureDateTime() {
        return departureDateTime;
    }

    public void setDepartureDateTime(Timestamp departureDateTime) {
        this.departureDateTime = departureDateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return id == flight.id && flightAircraftId == flight.flightAircraftId && brigadeId == flight.brigadeId && departureAirportId == flight.departureAirportId && destinationAirportId == flight.destinationAirportId && flightCallsign.equals(flight.flightCallsign) && departureDateTime.equals(flight.departureDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, flightAircraftId, brigadeId, departureAirportId, destinationAirportId, flightCallsign, departureDateTime);
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", flightAircraftId=" + flightAircraftId +
                ", brigadeId=" + brigadeId +
                ", departureAirportId=" + departureAirportId +
                ", destinationAirportId=" + destinationAirportId +
                ", flightCallsign='" + flightCallsign + '\'' +
                ", departureDateTime=" + departureDateTime +
                '}';
    }
}
