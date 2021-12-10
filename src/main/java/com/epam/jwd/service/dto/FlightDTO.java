package com.epam.jwd.service.dto;

import java.sql.Timestamp;
import java.util.Objects;

public class FlightDTO extends BaseDTO {
    private long id;
    private AircraftDTO aircraftDTO = new AircraftDTO();
    private BrigadeDTO brigadeDTO = new BrigadeUserDTO();
    private AirportDTO departureAirport = new AirportDTO();
    private AirportDTO destinationAirport = new AirportDTO();
    private String flightCallsign;
    private Timestamp departureDateTime;
    private boolean isArchived;

    public boolean getIsArchived() {
        return isArchived;
    }

    public void setIsArchived(boolean archived) {
        isArchived = archived;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public AircraftDTO getAircraftDTO() {
        return aircraftDTO;
    }

    public void setAircraftDTO(AircraftDTO aircraftDTO) {
        this.aircraftDTO = aircraftDTO;
    }

    public BrigadeDTO getBrigadeDTO() {
        return brigadeDTO;
    }

    public void setBrigadeDTO(BrigadeDTO brigadeDTO) {
        this.brigadeDTO = brigadeDTO;
    }

    public AirportDTO getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(AirportDTO departureAirport) {
        this.departureAirport = departureAirport;
    }

    public AirportDTO getDestinationAirport() {
        return destinationAirport;
    }

    public void setDestinationAirport(AirportDTO destinationAirport) {
        this.destinationAirport = destinationAirport;
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
        FlightDTO flightDTO = (FlightDTO) o;
        return id == flightDTO.id && isArchived == flightDTO.isArchived && Objects.equals(aircraftDTO, flightDTO.aircraftDTO) && Objects.equals(brigadeDTO, flightDTO.brigadeDTO) && Objects.equals(departureAirport, flightDTO.departureAirport) && Objects.equals(destinationAirport, flightDTO.destinationAirport) && Objects.equals(flightCallsign, flightDTO.flightCallsign) && Objects.equals(departureDateTime, flightDTO.departureDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, aircraftDTO, brigadeDTO, departureAirport, destinationAirport, flightCallsign, departureDateTime, isArchived);
    }

    @Override
    public String toString() {
        return "FlightDTO{" +
                "id=" + id +
                ", aircraftDTO=" + aircraftDTO +
                ", brigadeDTO=" + brigadeDTO +
                ", departureAirport=" + departureAirport +
                ", destinationAirport=" + destinationAirport +
                ", flightCallsign='" + flightCallsign + '\'' +
                ", departureDateTime=" + departureDateTime +
                '}';
    }
}
