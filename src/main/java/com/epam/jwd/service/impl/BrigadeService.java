package com.epam.jwd.service.impl;

import com.epam.jwd.dao.exception.DAOException;
import com.epam.jwd.dao.impl.BrigadeDaoImpl;
import com.epam.jwd.service.converter.impl.BrigadeConverter;
import com.epam.jwd.service.converter.impl.UserConverter;
import com.epam.jwd.service.dto.BrigadeDTO;
import com.epam.jwd.service.dto.BrigadeUserDTO;
import com.epam.jwd.service.dto.UserDTO;
import com.epam.jwd.service.exception.ValidatorException;
import com.epam.jwd.service.validator.impl.BrigadeValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class BrigadeService {
    private static final Logger logger = LogManager.getLogger(BrigadeService.class);
    private final  BrigadeDaoImpl brigadeDAO = new BrigadeDaoImpl();
    private final BrigadeConverter brigadeConverter = new BrigadeConverter();
    private final UserConverter userConverter = new UserConverter();
    private final BrigadeValidator brigadeValidator = new BrigadeValidator();


    /**
     * Allows adding user to brigade
     * @param userId user id
     * @param brigadeId brigade id
     * @return true if user is successfully added, otherwise execption
     * @throws DAOException
     */
    public boolean addUserToBrigade(long userId, long brigadeId) throws DAOException {
        logger.debug("addUserToBrigade method");
        return brigadeDAO.addUserToBrigade(userId, brigadeId);
    }


    /**
     * Allows removing user from brigade
     * @param userId user id
     * @param brigadeId brigade id
     * @return true if user is successfully removed, otherwise execption
     * @throws DAOException
     */
    public boolean removeUserFromBrigade(long userId, long brigadeId) throws DAOException {
        logger.debug("removeUserFromBrigade method");
        return brigadeDAO.removeUserFromBrigade(userId, brigadeId);
    }

    /**
     * Allows getting brigade userslist).
     * @param brigadeId brigade id
     * @return List of brigade users
     * @throws DAOException
     */
    public List<UserDTO> getBrigadeUsers(long brigadeId) throws DAOException {
        logger.debug("getBrigadeUsers method");
        return userConverter.convertWorkerUsersListToDTO(brigadeDAO.getBrigadeUsers(brigadeId));
    }

    /**
     * Allows saving brigade in DB.
     * @param brigadeDTO brigade
     * @return brigadeDTO with generated ID or exception
     * @throws DAOException
     * @throws ValidatorException
     */
    public BrigadeDTO saveBrigade(BrigadeDTO brigadeDTO) throws DAOException, ValidatorException {
        logger.debug("saveBrigade method");
        brigadeValidator.isValid(brigadeDTO);
        return brigadeConverter.convertToDTO(brigadeDAO.save(brigadeConverter.convertToDAO(brigadeDTO)));
    }

    /**
     * Allows updating brigade data in DB.
     * @param brigadeDTO new brigade
     * @return true if brigade is successfully updated, otherwise exception
     * @throws DAOException
     * @throws ValidatorException
     */
    public boolean updateBrigade(BrigadeDTO brigadeDTO) throws DAOException, ValidatorException {
        logger.debug("updateBrigade method");
        brigadeValidator.isValid(brigadeDTO);
        return brigadeDAO.update(brigadeConverter.convertToDAO(brigadeDTO));
    }

    /**
     * Allows deleting brigade by id.
     * @param brigadeId brigade id
     * @return true if brigade is successfully deleted, otherwise exception
     * @throws DAOException
     */
    public boolean deleteBrigade(long brigadeId) throws DAOException {
        logger.debug("deleteBrigade method");
        return brigadeDAO.deleteById(brigadeId);
    }


    /**
     * Allows finding brigade by id
     * @param brigadeId brigade id
     * @return return Brigade Id, otherwise exception
     * @throws DAOException
     */
    public BrigadeDTO findById(long brigadeId) throws DAOException {
        logger.debug("findById method");
        return brigadeConverter.convertToDTO(brigadeDAO.findById(brigadeId));
    }

    /**
     * Allows finding all brigades
     * @return List of brigades
     * @throws DAOException
     */
    public List<BrigadeDTO> findAllBrigade() throws DAOException {
        logger.debug("findAllBrigade method");
        return brigadeConverter.convertToDTOList(brigadeDAO.findAll());
    }

    /**
     * Allows getting brigade with userlist
     * @param brigadeDTO brigade
     * @return brigade with userDTOlist
     * @throws DAOException
     */
    public BrigadeUserDTO getBrigadeWithUsers(BrigadeDTO brigadeDTO) throws DAOException {
        logger.debug("getBrigadeWithUsers method");
        BrigadeUserDTO brigadeUserDTO = new BrigadeUserDTO();
        brigadeUserDTO.setBrigadeId(brigadeDTO.getBrigadeId());
        brigadeUserDTO.setBrigadeName(brigadeDTO.getBrigadeName());
        brigadeUserDTO.setIsArchived(brigadeDTO.getIsArchived());
        brigadeUserDTO.getUserDTOs().addAll(this.getBrigadeUsers(brigadeDTO.getBrigadeId()));
        return brigadeUserDTO;
    }



    /**
     *  Allows getting all brigades with userlist
     * @return Brigades list with userlist.
     * @throws DAOException
     */
    public List<BrigadeUserDTO> findAllBrigadeWithUsers() throws DAOException {
        logger.debug("findAllBrigadeWithUsers method");
        List<BrigadeUserDTO> brigadeUserDTOs = new ArrayList<>();
        List<BrigadeDTO> brigadeDTOs = brigadeConverter.convertToDTOList(brigadeDAO.findAll());
        for (BrigadeDTO brigadeDTO : brigadeDTOs) {
            brigadeUserDTOs.add(this.getBrigadeWithUsers(brigadeDTO));
        }
        return brigadeUserDTOs;
    }

    /**
     * Allows changing Archived status if brigade
     * @param brigadeId brigade id
     * @param isArchived Archive status
     * @return true if status is changed, otherwise exception
     * @throws DAOException
     */
    public boolean changeArchiveStatus(long brigadeId, boolean isArchived) throws DAOException {
        return brigadeDAO.changeArchiveStatus(brigadeId,isArchived);
    }
}
