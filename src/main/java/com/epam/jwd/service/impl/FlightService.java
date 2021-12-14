package com.epam.jwd.service.impl;

import com.epam.jwd.dao.entity.Flight;
import com.epam.jwd.dao.exception.DAOException;
import com.epam.jwd.dao.impl.FlightDaoImpl;
import com.epam.jwd.service.converter.impl.FlightConverter;
import com.epam.jwd.service.dto.FlightDTO;
import com.epam.jwd.service.exception.ValidatorException;
import com.epam.jwd.service.validator.impl.FlightValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class FlightService {
    private static final Logger logger = LogManager.getLogger(FlightService.class);
    private final  FlightDaoImpl flightDao = new FlightDaoImpl();
    private final FlightConverter flightConverter = new FlightConverter();
    private final FlightValidator flightValidator = new FlightValidator();


    /**
     * Allows saving flight in DB.
     * @param flight flight entity
     * @return FlightDTO with generated id
     * @throws DAOException
     * @throws ValidatorException
     */
    public FlightDTO saveFlight(Flight flight) throws DAOException, ValidatorException {
        flightValidator.isValid(flightConverter.convertToDTO(flight));
        logger.debug("saveFlight method");
        return this.convertToDTO(flightDao.save(flight));
    }

    /**
     * Allows updating flight data
     * @param flight flight with new data
     * @return true if flight is successfully updated
     * @throws ValidatorException
     * @throws DAOException
     */
    public boolean updateFlight(Flight flight) throws ValidatorException, DAOException {
        flightValidator.isValid(flightConverter.convertToDTO(flight));
        logger.debug("updateFlight method");
        return flightDao.update(flight);
    }


    /**
     * Allows changing acrhive status of flight.
     * @param flightId flight id
     * @param isArchived status
     * @return true or exception
     * @throws DAOException
     */
    public boolean changeArchiveStatus(long flightId, boolean isArchived) throws DAOException {
        logger.debug("changeArchiveStatus method");
        return flightDao.changeArchiveStatus(flightId, isArchived);
    }

    /**
     * Allows setting brigade for flight
     * @param flightId flight id
     * @param brigadeId brigade id
     * @return true or exception.
     * @throws DAOException
     */
    public boolean updateFlightBrigade(long flightId, long brigadeId) throws DAOException {
        logger.debug("updateFlight method");
        return flightDao.updateFlightBrigade(flightId, brigadeId);
    }

    /**
     * Allows finding all flights
     * @return List of FlightDTO
     * @throws DAOException
     */
    public List<FlightDTO> findAllFlights() throws DAOException {
        logger.debug("findAllFlights method");
        List<FlightDTO> flightDTOs = new ArrayList<>();
        for (Flight flight : flightDao.findAll()) {
            flightDTOs.add(this.convertToDTO(flight));
        }
        return flightDTOs;
    }

    /**
     * Allows finding flight by id
     * @param id flight id
     * @return FlightDTO or exception
     * @throws DAOException
     */
    public FlightDTO findFlightById(long id) throws DAOException {
        logger.debug("findFlightById method");
        return this.convertToDTO(flightDao.findById(id));
    }

    /**
     * Allows deleting flight by id
     * @param id flight id
     * @return true if flight was removed, otherwise exception
     * @throws DAOException
     */
    public boolean deleteFlightById(long id) throws DAOException {
        logger.debug("deleteFlightById method");
        return flightDao.deleteById(id);
    }

    /**
     * Convert flight to flightDTO
     * @param flight Flight
     * @return FLightDTO
     */
    public FlightDTO convertToDTO(Flight flight) {
        logger.debug("convertToDTO method");
        BrigadeService brigadeService = new BrigadeService();
        AircraftService aircraftService = new AircraftService();
        AirportService airportService = new AirportService();
        FlightDTO flightDTO = flightConverter.convertToDTO(flight);
        try {
            flightDTO.setBrigadeDTO(brigadeService.findById(flight.getBrigadeId()));
        } catch (DAOException e) {
            logger.debug("flight without brigade");
            flightDTO.setBrigadeDTO(null);
        }

        try {
            flightDTO.setAircraftDTO(aircraftService.findAircraftById(flight.getFlightAircraftId()));
        } catch (DAOException e) {
            logger.debug("flight without aircraft");
            flightDTO.setAircraftDTO(null);
        }

        try {
            flightDTO.setDepartureAirport(airportService.findAirportById(flight.getDepartureAirportId()));
        } catch (DAOException e) {
            logger.debug("flight without dep airport");
            flightDTO.setDepartureAirport(null);
        }

        try {
            flightDTO.setDestinationAirport(airportService.findAirportById(flight.getDestinationAirportId()));
        } catch (DAOException e) {
            logger.debug("flight without dest airport");
            flightDTO.setDestinationAirport(null);
        }
        return flightDTO;
    }

}
