package com.epam.jwd.service.converter;

import com.epam.jwd.dao.entity.Airport;
import com.epam.jwd.service.dto.AircraftDTO;
import com.epam.jwd.service.dto.AirportDTO;

public class AirportConverter implements Converter<Airport, AirportDTO> {
    @Override
    public Airport convertToDAO(AirportDTO airportDTO) {
        Airport airport = new Airport();
        airport.setId(airportDTO.getId());
        airport.setName(airportDTO.getName());
        airport.setCity(airportDTO.getCity());
        airport.setCountry(airportDTO.getCountry());
        airport.setIATACode(airport.getIATACode());
        return airport;
    }

    @Override
    public AirportDTO convertToDTO(Airport airport) {
        AirportDTO airportDTO = new AirportDTO();
        airportDTO.setId(airport.getId());
        airportDTO.setCity(airport.getCity());
        airportDTO.setName(airport.getName());
        airportDTO.setCountry(airport.getCountry());
        airportDTO.setIATACode(airport.getIATACode());
        return airportDTO;
    }
}
