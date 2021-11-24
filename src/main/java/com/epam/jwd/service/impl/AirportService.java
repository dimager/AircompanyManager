package com.epam.jwd.service.impl;

import com.epam.jwd.dao.entity.Airport;
import com.epam.jwd.dao.exception.DAOException;
import com.epam.jwd.dao.impl.AirportDaoImpl;
import com.epam.jwd.service.converter.AirportConverter;
import com.epam.jwd.service.dto.AirportDTO;
import com.epam.jwd.service.exception.ValidatorException;
import com.epam.jwd.service.validator.AirportValidator;

import java.util.ArrayList;
import java.util.List;

public class AirportService {
    AirportDaoImpl airportDao = new AirportDaoImpl();
    AirportConverter airportConverter = new AirportConverter();
    AirportValidator airportValidator  = new AirportValidator();
    public AirportDTO saveAirport (AirportDTO airportDTO) throws DAOException, ValidatorException {
        airportValidator.validate(airportDTO);
        return airportConverter.convertToDTO(airportDao.save(airportConverter.convertToDAO(airportDTO)));
    }

    public boolean updateAirport (AirportDTO airportDTO) throws ValidatorException, DAOException {
        airportValidator.validate(airportDTO);
        return airportDao.update(airportConverter.convertToDAO(airportDTO));
    }

    public AirportDTO findAirportById (int id) throws DAOException {
        return airportConverter.convertToDTO(airportDao.findById(id));
    }

    public List<AirportDTO> findAllAirports() throws DAOException {
        List<AirportDTO> airportDTOList = new ArrayList<>();
        for (Airport airport : airportDao.findAll()) {
            airportDTOList.add(airportConverter.convertToDTO(airport));
        }
        return airportDTOList;
    }

    public boolean deleteAirport (int id) throws DAOException {
        return airportDao.deleteById(id);
    }

}
