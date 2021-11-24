package com.epam.jwd.service.converter;

import com.epam.jwd.dao.entity.Flight;
import com.epam.jwd.service.dto.FlightDTO;

public class FlightConverter implements Converter<Flight, FlightDTO> {
    @Override
    public Flight convertToDAO(FlightDTO flightDTO) {
        Flight flight = new Flight();
        flight.setId(flightDTO.getId());
        flight.setFlightCallsign(flightDTO.getFlightCallsign());
        flight.setDestinationAirportId(flightDTO.getDestinationAirport().getId());
        flight.setDepartureAirportId(flightDTO.getDepartureAirport().getId());
        flight.setDepartureDateTime(flightDTO.getDepartureDateTime());
        flight.setBrigadeId(flightDTO.getBrigadeDTO().getBrigadeId());
        flight.setFlightAircraftId(flightDTO.getAircraftDTO().getAircraftId());
        return flight;
    }

    @Override
    public FlightDTO convertToDTO(Flight flight) {
        FlightDTO flightDTO = new FlightDTO();
        flightDTO.setId(flight.getId());
        flightDTO.setFlightCallsign(flight.getFlightCallsign());
        flightDTO.setDepartureDateTime(flight.getDepartureDateTime());
        return flightDTO;
    }

}



