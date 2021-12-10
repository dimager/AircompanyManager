package com.epam.jwd.service.impl;

import com.epam.jwd.dao.entity.Airport;
import com.epam.jwd.dao.exception.DAOException;
import com.epam.jwd.dao.impl.AirportDaoImpl;
import com.epam.jwd.service.converter.impl.AirportConverter;
import com.epam.jwd.service.dto.AirportDTO;
import com.epam.jwd.service.exception.ValidatorException;
import com.epam.jwd.service.validator.impl.AirportValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class AirportService {
    private static final Logger logger = LogManager.getLogger(AirportService.class);
    AirportDaoImpl airportDao = new AirportDaoImpl();
    AirportConverter airportConverter = new AirportConverter();
    AirportValidator airportValidator  = new AirportValidator();
    public AirportDTO saveAirport (AirportDTO airportDTO) throws DAOException, ValidatorException {
        logger.debug("saveAirport method");
        airportValidator.isValid(airportDTO);
        return airportConverter.convertToDTO(airportDao.save(airportConverter.convertToDAO(airportDTO)));
    }

    public boolean updateAirport (AirportDTO airportDTO) throws ValidatorException, DAOException {
        logger.debug("updateAirport method");
        airportValidator.isValid(airportDTO);
        return airportDao.update(airportConverter.convertToDAO(airportDTO));
    }

    public AirportDTO findAirportById (int id) throws DAOException {
        logger.debug("findAirportById method");
        return airportConverter.convertToDTO(airportDao.findById(id));
    }

    public List<AirportDTO> findAllAirports() throws DAOException {
        logger.debug("findAllAirports method");
        List<AirportDTO> airportDTOList = new ArrayList<>();
        for (Airport airport : airportDao.findAll()) {
            airportDTOList.add(airportConverter.convertToDTO(airport));
        }
        return airportDTOList;
    }

    public boolean deleteAirport (int id) throws DAOException {
        logger.debug("deleteAirport method");
        return airportDao.deleteById(id);
    }

}
