package com.epam.jwd.service.impl;

import com.epam.jwd.dao.entity.Aircraft;
import com.epam.jwd.dao.exception.DAOException;
import com.epam.jwd.dao.impl.AircraftDaoImpl;
import com.epam.jwd.service.converter.impl.AircraftConverter;
import com.epam.jwd.service.dto.AircraftDTO;
import com.epam.jwd.service.exception.ValidatorException;
import com.epam.jwd.service.validator.impl.AircraftValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class AircraftService  {
    private static final Logger logger = LogManager.getLogger(AircraftService.class);
    private final AircraftConverter converter = new AircraftConverter();
    private final AircraftDaoImpl aircraftDAO = new AircraftDaoImpl();

    public List<AircraftDTO> convertToDTOList(List<Aircraft> aircrafts) {
        logger.debug("convertToDTOList method");
        List<AircraftDTO> aircraftDTOList = new ArrayList<>();
        aircrafts.forEach(aircraft -> aircraftDTOList.add(converter.convertToDTO(aircraft)));
        return aircraftDTOList;
    }

    public List<AircraftDTO> findAllAircrafts() throws DAOException {
        logger.debug("findAllAircrafts method");
        return this.convertToDTOList(aircraftDAO.findAll());
    }

    public boolean changeInOperationStatus(int aircraftId, boolean inOperation) throws DAOException {
        return aircraftDAO.ChangeInOperationStatus(aircraftId,inOperation);
    }

    public AircraftDTO findAircraftById(AircraftDTO aircraftDTO) throws DAOException {
        logger.debug("findAircraftById method");
        return this.findAircraftById(aircraftDTO.getAircraftId());
    }

    public AircraftDTO findAircraftById(int id) throws DAOException {
        logger.debug("findAircraftById method");
        return converter.convertToDTO(aircraftDAO.findById(id));
    }

    public AircraftDTO saveAircraft(AircraftDTO aircraftDTO) throws DAOException, ValidatorException {
        logger.debug("saveAircraft method");
        AircraftValidator aircraftValidator = new AircraftValidator();
        aircraftValidator.isValid(aircraftDTO);
        return converter.convertToDTO(aircraftDAO.save(converter.convertToDAO(aircraftDTO)));
    }

    public boolean deleteAircraftById(AircraftDTO aircraftDTO) throws DAOException {
        logger.debug("deleteAircraftById method");
        return aircraftDAO.deleteById(aircraftDTO.getAircraftId());
    }

    public boolean deleteAircraftById(int id) throws DAOException {
        logger.debug("deleteAircraftById method");
        return aircraftDAO.deleteById(id);
    }

    public boolean updateAircraft(AircraftDTO aircraftDTO) throws DAOException, ValidatorException {
        logger.debug("updateAircraft method");
        AircraftValidator aircraftValidator = new AircraftValidator();
        aircraftValidator.isValid(aircraftDTO);
        return aircraftDAO.update(converter.convertToDAO(aircraftDTO));
    }
}
