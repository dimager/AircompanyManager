package com.epam.jwd.service.impl;

import com.epam.jwd.dao.exception.DAOException;
import com.epam.jwd.dao.impl.BrigadeDaoImpl;
import com.epam.jwd.service.converter.BrigadeConverter;
import com.epam.jwd.service.converter.UserConverter;
import com.epam.jwd.service.dto.BrigadeDTO;
import com.epam.jwd.service.dto.BrigadeUserDTO;
import com.epam.jwd.service.dto.UserDTO;
import com.epam.jwd.service.exception.ValidatorException;
import com.epam.jwd.service.validator.BrigadeValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class BrigadeService {
    private static final Logger logger = LogManager.getLogger(BrigadeService.class);
    BrigadeDaoImpl brigadeDAO = new BrigadeDaoImpl();
    BrigadeConverter brigadeConverter = new BrigadeConverter();
    UserConverter userConverter = new UserConverter();
    BrigadeValidator brigadeValidator = new BrigadeValidator();

    public boolean addUserToBrigade(long userId, long brigadeId) throws DAOException {
        logger.debug("addUserToBrigade method");
        return brigadeDAO.addUserToBrigade(userId, brigadeId);
    }

    public boolean addUserToBrigade(UserDTO userDTO, BrigadeDTO brigadeDTO) throws DAOException {
        logger.debug("addUserToBrigade method");
        return this.addUserToBrigade(userDTO.getUserId(), brigadeDTO.getBrigadeId());
    }

    public void addUsersToBrigade(List<UserDTO> userDTOS, BrigadeDTO brigadeDTO) throws DAOException {
        logger.debug("addUsersToBrigade method");
        for (UserDTO userDTO : userDTOS) {
            this.addUserToBrigade(userDTO, brigadeDTO);
        }
    }

    public void addUsersToBrigade(List<Long> userDTOIds, long brigadeId) throws DAOException {
        logger.debug("addUsersToBrigade method");
        for (long userId : userDTOIds) {
            this.addUserToBrigade(userId, brigadeId);
        }
    }

    public boolean removeUserFromBrigade(long userId, long brigadeId) throws DAOException {
        logger.debug("removeUserFromBrigade method");
        return brigadeDAO.removeUserFromBrigade(userId, brigadeId);
    }

    public boolean removeUserFromBrigade(UserDTO userDTO, BrigadeDTO brigadeDTO) throws DAOException {
        logger.debug("removeUserFromBrigade method");
        return this.removeUserFromBrigade(userDTO.getUserId(), brigadeDTO.getBrigadeId());
    }

    public void removeUsersFromBrigade(List<UserDTO> userDTOS, BrigadeDTO brigadeDTO) throws DAOException {
        logger.debug("removeUsersFromBrigade method");
        for (UserDTO userDTO : userDTOS) {
            this.removeUserFromBrigade(userDTO.getUserId(), brigadeDTO.getBrigadeId());
        }
    }

    public void removeUsersFromBrigade(List<Long> userDTOIds, long brigadeId) throws DAOException {
        logger.debug("removeUsersFromBrigade method");
        for (Long userDTOId : userDTOIds) {
            this.removeUserFromBrigade(userDTOId, brigadeId);
        }
    }

    public List<UserDTO> getBrigadeUsers(long brigadeId) throws DAOException {
        logger.debug("getBrigadeUsers method");
        return userConverter.convertWorkerUsersListToDTO(brigadeDAO.getBrigadeUsers(brigadeId));
    }

    public List<UserDTO> getBrigadeUsers(BrigadeDTO brigadeDTO) throws DAOException {
        logger.debug("getBrigadeUsers method");
        return this.getBrigadeUsers(brigadeDTO.getBrigadeId());
    }

    public BrigadeDTO saveBrigade(BrigadeDTO brigadeDTO) throws DAOException, ValidatorException {
        logger.debug("saveBrigade method");
        brigadeValidator.isValid(brigadeDTO);
        return brigadeConverter.convertToDTO(brigadeDAO.save(brigadeConverter.convertToDAO(brigadeDTO)));
    }

    public boolean updateBrigade(BrigadeDTO brigadeDTO) throws DAOException, ValidatorException {
        brigadeValidator.isValid(brigadeDTO);
        return brigadeDAO.update(brigadeConverter.convertToDAO(brigadeDTO));
    }

    public boolean deleteBrigade(long brigadeId) throws DAOException {
        return brigadeDAO.deleteById(brigadeId);
    }

    public boolean deleteBrigade(BrigadeDTO brigadeDTO) throws DAOException {
        return this.deleteBrigade(brigadeDTO.getBrigadeId());
    }

    public BrigadeDTO findById(long brigadeId) throws DAOException {
        return brigadeConverter.convertToDTO(brigadeDAO.findById(brigadeId));
    }

    public BrigadeDTO findById(BrigadeDTO brigadeDTO) throws DAOException {
        return this.findById(brigadeDTO.getBrigadeId());
    }

    public List<BrigadeDTO> findAllBrigade() throws DAOException {
        return brigadeConverter.convertToDTOList(brigadeDAO.findAll());
    }

    public BrigadeUserDTO getBrigadeWithUsers(BrigadeDTO brigadeDTO) throws DAOException {
        BrigadeUserDTO brigadeUserDTO = new BrigadeUserDTO();
        brigadeUserDTO.setBrigadeId(brigadeDTO.getBrigadeId());
        brigadeUserDTO.setBrigadeName(brigadeDTO.getBrigadeName());
        brigadeUserDTO.getUserDTOs().addAll(this.getBrigadeUsers(brigadeDTO.getBrigadeId()));
        return brigadeUserDTO;
    }

    public List<BrigadeUserDTO> findAllBrigadeWithUsers() throws DAOException {
        List<BrigadeUserDTO> brigadeUserDTOs = new ArrayList<>();
        List<BrigadeDTO> brigadeDTOs = brigadeConverter.convertToDTOList(brigadeDAO.findAll());
        for (BrigadeDTO brigadeDTO : brigadeDTOs) {
            brigadeUserDTOs.add(this.getBrigadeWithUsers(brigadeDTO));
        }
        return brigadeUserDTOs;
    }
}
