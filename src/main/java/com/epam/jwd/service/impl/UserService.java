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
    private final  UserDaoImpl userDao = new UserDaoImpl();
    private final FlightService flightService = new FlightService();
    private final UserConverter userConverter = new UserConverter();
    private final  BrigadeConverter brigadeConverter = new BrigadeConverter();


    /**
     * Allows saving user to DB
     * @param userDTO new userDTO
     * @return UserDTO with generated id or exception
     * @throws DAOException
     */
    public UserDTO save(UserDTO userDTO) throws DAOException {
        logger.debug("save method");
        userDTO.setRole(Role.GUEST);
        User user = userConverter.convertToDAO(userDTO);
        BcryptService.crypt(user);
        return userConverter.convertToDTO(userDao.save(user));
    }

    /**
     * Allows getting user`s role by id
     * @param id user id
     * @return User Role.
     * @throws DAOException
     */
    public Role getCurrentUserRole (long id) throws DAOException {
        logger.debug("getCurrentUserRole method");
        UserDTO userDTO = this.findById(id);
        return userDTO.getRole();

    }

    /**
     * Allows updating user in DB.
     * @param userDTO userDTO with new data.
     * @return true if user was updated or exception
     * @throws DAOException
     */
    public boolean update(UserDTO userDTO) throws DAOException {
        logger.debug("update method");
        User user = userConverter.convertToDAO(userDTO);
        BcryptService.crypt(user);
        return userDao.update(user);
    }

    /**
     * Allows getting list of users which are not in brigade.
     * @param brigadeId brigade id
     * @return list of users which are not in brigade.
     * @throws DAOException
     */
    public List<UserDTO> findFreeUsers(long brigadeId) throws DAOException {
        logger.debug("findFreeUsers method");
        return userConverter.convertWorkerUsersListToDTO(userDao.getBrigadeFreeUsers(brigadeId));
    }


    /**
     * Allows getting all users in DB.
     * @return List of UserDTO
     * @throws DAOException
     */
    public List<UserDTO> findAll() throws DAOException {
        logger.debug("findAll method");
        return userConverter.convertAllUsersListToDTO(userDao.findAll());
    }


    /**
     * Allows finding user by id
     * @param userId user id
     * @return userDTO or if user wasn't found exception
     * @throws DAOException
     */
    public UserDTO findById(long userId) throws DAOException {
        logger.debug("findById method");
        return userConverter.convertToDTO(userDao.findById(userId));
    }

    /**
     * Allows deleting user from DB by id.
     * @param userId user id
     * @return true or if user wasn't deleted exception
     * @throws DAOException
     */
    public boolean deleteById(long userId) throws DAOException {
        logger.debug("deleteById method");
        return userDao.deleteById(userId);
    }

    /**
     * Allows changing user role
     * @param userId user id
     * @param roleId new role
     * @return true or if role wasn't changed exception
     * @throws DAOException
     */
    public boolean changUserRole(long userId, int roleId) throws DAOException {
        logger.debug("changUserRole method");
        return userDao.updateRole(userId, roleId);
    }

    /**
     * Allows finding user`s flights
     * @param userId
     * @return List of user flights
     * @throws DAOException
     */
    public List<FlightDTO> findUserFlights(Long userId) throws DAOException {
        logger.debug("findUserFlights method");
        List<Long> userFlightsId = userDao.getUserFlights(userId);
        List<FlightDTO> userFlightDTOList = new ArrayList<>();
        for (Long flightId : userFlightsId) {
            userFlightDTOList.add(flightService.findFlightById(flightId));
        }
        return userFlightDTOList.stream()
                .filter(flightDTO -> !flightDTO.getIsArchived())
                .sorted(Comparator.comparing(FlightDTO::getDepartureDateTime))
                .collect(Collectors.toList());
    }

    /**
     * Allows find user history flights.
     * @param userId user id
     * @return List of history flights
     * @throws DAOException
     */
    public List<FlightDTO> findUserFlightsHistory(Long userId) throws DAOException {
        logger.debug("findUserFlightsHistory method");
        List<Long> userFlightsId = userDao.getUserFlights(userId);
        List<FlightDTO> userFlightDTOList = new ArrayList<>();
        for (Long flightId : userFlightsId) {
            userFlightDTOList.add(flightService.findFlightById(flightId));
        }
        return userFlightDTOList.stream()
                .filter(flightDTO -> flightDTO.getIsArchived())
                .sorted(Comparator.comparing(FlightDTO::getDepartureDateTime))
                .collect(Collectors.toList());
    }


    /**
     * Allows finding archived user brigades.
     * @param userId user id
     * @return List of  archived brigades.
     * @throws DAOException
     */
    public List<BrigadeDTO> findArchivedUserBrigades(Long userId) throws DAOException {
        logger.debug("findUserBrigades method");
        return brigadeConverter.convertToDTOList(userDao.getUserBrigades(userId)).stream()
                .filter(brigadeDTO -> brigadeDTO.getIsArchived())
                .collect(Collectors.toList());
    }

    /**
     * Allows finding current user brigades.
     * @param userId user id
     * @return List of brigades.
     * @throws DAOException
     */
    public List<BrigadeDTO> findCurrentUserBrigades(Long userId) throws DAOException {
        logger.debug("findUserBrigades method");
        return findUserFlights(userId).stream()
                .filter(flightDTO -> !flightDTO.getIsArchived())
                .map(FlightDTO::getBrigadeDTO)
                .distinct()
                .collect(Collectors.toList());
    }
}
