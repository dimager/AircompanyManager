package com.epam.jwd.service.impl;

import com.epam.jwd.dao.entity.Flight;
import com.epam.jwd.dao.exception.DAOException;
import com.epam.jwd.dao.impl.FlightDaoImpl;
import com.epam.jwd.service.converter.FlightConverter;
import com.epam.jwd.service.dto.FlightDTO;
import com.epam.jwd.service.exception.ValidatorException;
import com.epam.jwd.service.validator.FlightValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class FlightService {
    private static final Logger logger = LogManager.getLogger(FlightService.class);
    FlightDaoImpl flightDao = new FlightDaoImpl();
    FlightConverter flightConverter = new FlightConverter();
    FlightValidator flightValidator = new FlightValidator();


    public FlightDTO saveFlight(FlightDTO flightDTO) throws DAOException, ValidatorException {
        logger.debug("saveFlight method");
        flightValidator.validate(flightDTO);
        return flightConverter.convertToDTO(flightDao.save(flightConverter.convertToDAO(flightDTO)));
    }

    public boolean updateFlight(FlightDTO flightDTO) throws ValidatorException, DAOException {
        logger.debug("updateFlight method");
        flightValidator.validate(flightDTO);
        return flightDao.update(flightConverter.convertToDAO(flightDTO));
    }

    public List<FlightDTO> findAllFlights() throws DAOException {
        logger.debug("findAllFlights method");
        List<FlightDTO> flightDTOs = new ArrayList<>();
        for (Flight flight : flightDao.findAll()) {
            flightDTOs.add(this.convertToDTO(flight));
        }
        return flightDTOs;
    }

    public FlightDTO findFlightById(long id) throws DAOException {
        logger.debug("findFlightById method");
        return this.convertToDTO(flightDao.findById(id));
    }

    public boolean deleteFlightById(long id) throws DAOException {
        logger.debug("deleteFlightById method");
        return flightDao.deleteById(id);
    }

    public FlightDTO convertToDTO(Flight flight) {
        logger.debug("convertToDTO method");
        BrigadeService brigadeService = new BrigadeService();
        AircraftService aircraftService = new AircraftService();
        AirportService airportService = new AirportService();
        FlightDTO flightDTO = flightConverter.convertToDTO(flight);
        try {
            flightDTO.setBrigadeDTO(brigadeService.findById(flight.getBrigadeId()));

        } catch (DAOException e) {
            flightDTO.setBrigadeDTO(null);
        }

        try {
            flightDTO.setAircraftDTO(aircraftService.findAircraftById(flight.getFlightAircraftId()));

        } catch (DAOException e) {
            flightDTO.setAircraftDTO(null);
        }

        try {
            flightDTO.setDepartureAirport(airportService.findAirportById(flight.getDepartureAirportId()));
        } catch (DAOException e) {
            flightDTO.setDepartureAirport(null);
        }

        try {
            flightDTO.setDestinationAirport(airportService.findAirportById(flight.getDepartureAirportId()));
        } catch (DAOException e) {
            flightDTO.setDestinationAirport(null);
        }

        return flightDTO;
    }

}
