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
    private final AirportDaoImpl airportDao = new AirportDaoImpl();
    private final AirportConverter airportConverter = new AirportConverter();
    private final AirportValidator airportValidator  = new AirportValidator();

    /**
     * Allows saving airport entity in DB.
     * @param airportDTO entity which should be saved
     * @return airportDTO with generated id.
     * @throws DAOException throws if entity wasn't saved or sql exception
     * @throws ValidatorException throws if data validation is failed
     */
    public AirportDTO saveAirport (AirportDTO airportDTO) throws DAOException, ValidatorException {
        logger.debug("saveAirport method");
        airportValidator.isValid(airportDTO);
        return airportConverter.convertToDTO(airportDao.save(airportConverter.convertToDAO(airportDTO)));
    }

    /**
     * Allows update airport data in DB.
     * @param airportDTO  entity which should be updated
     * @return true if data was successfully updated, otherwise exception
     * @throws ValidatorException throws if data validation is failed
     * @throws DAOException throws if entity wasn't updated or sql exception
     */
    public boolean updateAirport (AirportDTO airportDTO) throws ValidatorException, DAOException {
        logger.debug("updateAirport method");
        airportValidator.isValid(airportDTO);
        return airportDao.update(airportConverter.convertToDAO(airportDTO));
    }

    /**
     * Allows finding airport by id.
     * @param id airport id which should be found in DB.
     * @return if entity was found return AirportDTO, otherwise exception
     * @throws DAOException
     */
    public AirportDTO findAirportById (int id) throws DAOException {
        logger.debug("findAirportById method");
        return airportConverter.convertToDTO(airportDao.findById(id));
    }

    /**
     * Allows finding list of all airports in DB
     * @return List of AirportDTO
     * @throws DAOException if some sql exception
     */
    public List<AirportDTO> findAllAirports() throws DAOException {
        logger.debug("findAllAirports method");
        List<AirportDTO> airportDTOList = new ArrayList<>();
        for (Airport airport : airportDao.findAll()) {
            airportDTOList.add(airportConverter.convertToDTO(airport));
        }
        return airportDTOList;
    }

    /**
     * Allows deleting airport entity in db by id
     * @param id of airport
     * @return true if airport was deleted, otherwise exception
     * * @throws DAOException
     */
    public boolean deleteAirport (int id) throws DAOException {
        logger.debug("deleteAirport method");
        return airportDao.deleteById(id);
    }

}
