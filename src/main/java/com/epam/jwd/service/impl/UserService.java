package com.epam.jwd.service.impl;


import com.epam.jwd.dao.entity.Role;
import com.epam.jwd.dao.entity.User;
import com.epam.jwd.dao.exception.DAOException;
import com.epam.jwd.dao.impl.UserDaoImpl;
import com.epam.jwd.service.converter.impl.BrigadeConverter;
import com.epam.jwd.service.converter.impl.UserConverter;
import com.epam.jwd.service.dto.BrigadeDTO;
import com.epam.jwd.service.dto.FlightDTO;
import com.epam.jwd.service.dto.UserDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class UserService {
    private static final Logger logger = LogManager.getLogger(UserService.class);
    UserDaoImpl userDao = new UserDaoImpl();
    FlightService flightService = new FlightService();
    UserConverter userConverter = new UserConverter();
    BrigadeConverter brigadeConverter = new BrigadeConverter();

    public UserDTO save(UserDTO userDTO) throws DAOException {
        logger.debug("save method");
        userDTO.setRole(Role.GUEST);
        User user = userConverter.convertToDAO(userDTO);
        BcryptService.crypt(user);
        return userConverter.convertToDTO(userDao.save(user));
    }

    public Role getCurrentUserRole (long id) throws DAOException {
        logger.debug("getCurrentUserRole method");
        UserDTO userDTO = this.findById(id);
        return userDTO.getRole();

    }

    public boolean update(UserDTO userDTO) throws DAOException {
        logger.debug("update method");
        User user = userConverter.convertToDAO(userDTO);
        BcryptService.crypt(user);
        return userDao.update(user);
    }

    public UserDTO findById(UserDTO userDTO) throws DAOException {
        logger.debug("findById method");
        return this.findById(userDTO.getUserId());
    }

    public List<UserDTO> findAllWorkers() throws DAOException {
        logger.debug("findAllWorkers method");
        return userConverter.convertWorkerUsersListToDTO(userDao.findAll());
    }

    public List<UserDTO> findFreeWorkers(long brigadeId) throws DAOException {
        logger.debug("findFreeWorkers method");
        return userConverter.convertWorkerUsersListToDTO(userDao.getBrigadeFreeUsers(brigadeId));
    }


    public List<UserDTO> findAll() throws DAOException {
        logger.debug("findAll method");
        return userConverter.convertAllUsersListToDTO(userDao.findAll());
    }

    public UserDTO findById(long userId) throws DAOException {
        logger.debug("findById method");
        return userConverter.convertToDTO(userDao.findById(userId));
    }

    public boolean deleteById(long userId) throws DAOException {
        logger.debug("deleteById method");
        return userDao.deleteById(userId);
    }

    public boolean changUserRole(long userId, int roleId) throws DAOException {
        logger.debug("changUserRole method");
        return userDao.updateRole(userId, roleId);
    }

    public List<FlightDTO> findUserFlights(Long userId) throws DAOException {
        logger.debug("findUserFlights method");
        List<Long> userFlightsId = userDao.getUserFlights(userId);
        List<FlightDTO> userFlightDTOList = new ArrayList<>();
        for (Long flightId : userFlightsId) {
            userFlightDTOList.add(flightService.findFlightById(flightId));
        }
        return userFlightDTOList.stream()
                .filter(flightDTO -> flightDTO.getIsArchived()==false)
                .sorted(Comparator.comparing(FlightDTO::getDepartureDateTime))
                .collect(Collectors.toList());
    }
    public List<FlightDTO> findUserFlightsHistory(Long userId) throws DAOException {
        logger.debug("findUserFlightsHistory method");
        List<Long> userFlightsId = userDao.getUserFlights(userId);
        List<FlightDTO> userFlightDTOList = new ArrayList<>();
        for (Long flightId : userFlightsId) {
            userFlightDTOList.add(flightService.findFlightById(flightId));
        }
        return userFlightDTOList.stream()
                .filter(flightDTO -> flightDTO.getIsArchived()==true)
                .sorted(Comparator.comparing(FlightDTO::getDepartureDateTime))
                .collect(Collectors.toList());
    }

    public List<BrigadeDTO> findArchivedUserBrigades(Long userId) throws DAOException {
        logger.debug("findUserBrigades method");
        return brigadeConverter.convertToDTOList(userDao.getUserBrigades(userId)).stream()
                .filter(brigadeDTO -> brigadeDTO.getIsArchived() == true)
                .collect(Collectors.toList());
    }

    public List<BrigadeDTO> findCurrentUserBrigades(Long userId) throws DAOException {
        logger.debug("findUserBrigades method");
        return findUserFlights(userId).stream()
                .filter(flightDTO -> flightDTO.getIsArchived() == false)
                .map(flightDTO -> flightDTO.getBrigadeDTO())
                .distinct()
                .collect(Collectors.toList());
    }
}
