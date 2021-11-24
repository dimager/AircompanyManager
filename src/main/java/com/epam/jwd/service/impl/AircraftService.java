package com.epam.jwd.service.impl;

import com.epam.jwd.dao.entity.Aircraft;
import com.epam.jwd.dao.exception.DAOException;
import com.epam.jwd.dao.impl.AircraftDaoImpl;
import com.epam.jwd.service.converter.AircraftConverter;
import com.epam.jwd.service.dto.AircraftDTO;
import com.epam.jwd.service.exception.ValidatorException;
import com.epam.jwd.service.validator.AircraftValidator;

import java.util.ArrayList;
import java.util.List;

public class AircraftService  {
    private final AircraftConverter converter = new AircraftConverter();
    private final AircraftDaoImpl aircraftDAO = new AircraftDaoImpl();

    public List<AircraftDTO> convertToDTOList(List<Aircraft> aircrafts) {
        List<AircraftDTO> aircraftDTOList = new ArrayList<>();
        aircrafts.forEach(aircraft -> aircraftDTOList.add(converter.convertToDTO(aircraft)));
        return aircraftDTOList;
    }

    public List<AircraftDTO> findAllAircrafts() throws DAOException {
        return this.convertToDTOList(aircraftDAO.findAll());
    }

    public AircraftDTO findAircraftById(AircraftDTO aircraftDTO) throws DAOException {
        return this.findAircraftById(aircraftDTO.getAircraftId());
    }

    public AircraftDTO findAircraftById(int id) throws DAOException {
        return converter.convertToDTO(aircraftDAO.findById(id));
    }

    public AircraftDTO saveAircraft(AircraftDTO aircraftDTO) throws DAOException, ValidatorException {
        AircraftValidator aircraftValidator = new AircraftValidator();
        aircraftValidator.validate(aircraftDTO);
        return converter.convertToDTO(aircraftDAO.save(converter.convertToDAO(aircraftDTO)));
    }

    public boolean deleteAircraftById(AircraftDTO aircraftDTO) throws DAOException {
        return aircraftDAO.deleteById(aircraftDTO.getAircraftId());
    }
    public boolean deleteAircraftById(int id) throws DAOException {
        return aircraftDAO.deleteById(id);
    }

    public boolean updateAircraft(AircraftDTO aircraftDTO) throws DAOException, ValidatorException {
        AircraftValidator aircraftValidator = new AircraftValidator();
        aircraftValidator.validate(aircraftDTO);
        return aircraftDAO.update(converter.convertToDAO(aircraftDTO));
    }
}
