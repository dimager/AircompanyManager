package com.epam.jwd.service.impl;


import com.epam.jwd.dao.entity.Role;
import com.epam.jwd.dao.entity.User;
import com.epam.jwd.dao.exception.DAOException;
import com.epam.jwd.dao.impl.UserDaoImpl;
import com.epam.jwd.service.converter.BrigadeConverter;
import com.epam.jwd.service.converter.UserConverter;
import com.epam.jwd.service.dto.BrigadeDTO;
import com.epam.jwd.service.dto.FlightDTO;
import com.epam.jwd.service.dto.UserDTO;
import com.epam.jwd.service.exception.ServiceException;
import com.epam.jwd.service.validator.UserValidator;

import java.util.ArrayList;
import java.util.List;


public class UserService {
    UserDaoImpl userDao = new UserDaoImpl();
    FlightService flightService = new FlightService();
    UserConverter userConverter = new UserConverter();
    UserValidator userValidator = new UserValidator();
    BrigadeConverter brigadeConverter = new BrigadeConverter();

    public UserDTO save(UserDTO userDTO) throws DAOException {
        userDTO.setRole(Role.GUEST);
        User user = userConverter.convertToDAO(userDTO);
        BcryptService.crypt(user);
        return userConverter.convertToDTO(userDao.save(user));
    }

    public boolean update(UserDTO userDTO) throws DAOException {
        User user = userConverter.convertToDAO(userDTO);
        BcryptService.crypt(user);
        return userDao.update(user);
    }

    public UserDTO findById(UserDTO userDTO) throws DAOException {
        return this.findById(userDTO.getUserId());
    }

    public List<UserDTO> findAllWorkers() throws DAOException {
        return userConverter.convertWorkerUsersListToDTO(userDao.findAll());
    }

    public List<UserDTO> findFreeWorkers(long brigadeId) throws DAOException {
        return userConverter.convertWorkerUsersListToDTO(userDao.getBrigadeFreeUsers(brigadeId));
    }


    public List<UserDTO> findAll() throws DAOException {
        return userConverter.convertAllUsersListToDTO(userDao.findAll());
    }

    public UserDTO findById(long userId) throws DAOException {
        return userConverter.convertToDTO(userDao.findById(userId));
    }

    public boolean deleteById(long userId) throws DAOException {
        return userDao.deleteById(userId);
    }

    public boolean changUserRole(long userId, int roleId) throws DAOException {
        return userDao.updateRole(userId, roleId);
    }

    public List<FlightDTO> findUserFlights(Long userId) throws DAOException {
        List<Long> userFlightsId = userDao.getUserFlights(userId);
        List<FlightDTO> userFlightDTOList = new ArrayList<>();
        for (Long flightId : userFlightsId) {
            userFlightDTOList.add(flightService.findFlightById(flightId));
        }
        return userFlightDTOList;
    }

    public List<BrigadeDTO> findUserBrigades(Long userId) throws DAOException {
        return brigadeConverter.convertToDTOList(userDao.getUserBrigades(userId));
    }
}
