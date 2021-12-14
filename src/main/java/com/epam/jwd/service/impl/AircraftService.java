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
    private final AircraftValidator aircraftValidator = new AircraftValidator();

    /**
     * Convert  AircraftDAO entity List to  AircraftDTO entity List
     * @param aircrafts Aircraft DAO List
     * @return AircraftDTOList
     */
    public List<AircraftDTO> convertToDTOList(List<Aircraft> aircrafts) {
        logger.debug("convertToDTOList method");
        List<AircraftDTO> aircraftDTOList = new ArrayList<>();
        aircrafts.forEach(aircraft -> aircraftDTOList.add(converter.convertToDTO(aircraft)));
        return aircraftDTOList;
    }

    /**
     * Find all aircrafts in DB.
     * @return AircraftDAOList
     * @throws DAOException -  throws if SQL exception.
     */
    public List<AircraftDTO> findAllAircrafts() throws DAOException {
        logger.debug("findAllAircrafts method");
        return this.convertToDTOList(aircraftDAO.findAll());
    }

    /**
     * Allows changing operation status of aircraft.
     * @param aircraftId - Aircraft ID in DB.
     * @param inOperation - Operation status of aircraft. true - aircraft could be used in fligths. false - set aircraft in service.
     * @return true if status was changed, otherwise exception.
     * @throws DAOException - throws if status wasn't changed because of SQL exception.
     */
    public boolean changeInOperationStatus(int aircraftId, boolean inOperation) throws DAOException {
        return aircraftDAO.ChangeInOperationStatus(aircraftId,inOperation);
    }

    /**
     * Allows finding aircraft in DB by id.
     * @param id - Aircraft id which should be found in DB.
     * @return AircraftDTO.
     * @throws DAOException throws if aircraft wasn't found in DB or because of some SQL exception.
     */
    public AircraftDTO findAircraftById(int id) throws DAOException {
        logger.debug("findAircraftById method");
        return converter.convertToDTO(aircraftDAO.findById(id));
    }

    /**
     * Allows saving new aircraft in DB.
     * @param aircraftDTO entity which should be saved in DB.
     * @return AircraftDTO with DB generated id.
     * @throws DAOException throws if status wasn't change because of SQL exception.
     * @throws ValidatorException throws if  aircraft validation was failed.
     */
    public AircraftDTO saveAircraft(AircraftDTO aircraftDTO) throws DAOException, ValidatorException {
        logger.debug("saveAircraft method");
        aircraftValidator.isValid(aircraftDTO);
        return converter.convertToDTO(aircraftDAO.save(converter.convertToDAO(aircraftDTO)));
    }

    /**
     * Allows deleting aircraft from DB
     * @param id  aircraft id which should be removed.
     * @return true if aircraft was deleted, otherwise exception.
     * @throws DAOException throws if aircraft wasn't removed from DB because of SQL exception.
     */
    public boolean deleteAircraftById(int id) throws DAOException {
        logger.debug("deleteAircraftById method");
        return aircraftDAO.deleteById(id);
    }

    /**
     * Allows updating aircraft data in DB.
     * @param aircraftDTO new aircraft data.
     * @return true if data was updated, otherwise exception.
     * @throws DAOException throws if aircraft wasn't updated in DB because of SQL exception.
     * @throws ValidatorException throws if aircraft aircraft validation was failed.
     */
    public boolean updateAircraft(AircraftDTO aircraftDTO) throws DAOException, ValidatorException {
        logger.debug("updateAircraft method");
        aircraftValidator.isValid(aircraftDTO);
        return aircraftDAO.update(converter.convertToDAO(aircraftDTO));
    }
}
