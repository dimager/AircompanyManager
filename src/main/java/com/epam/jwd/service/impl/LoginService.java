package com.epam.jwd.service.impl;

import com.epam.jwd.dao.entity.User;
import com.epam.jwd.dao.exception.DAOException;
import com.epam.jwd.dao.impl.UserDaoImpl;
import com.epam.jwd.service.converter.impl.UserConverter;
import com.epam.jwd.service.dto.UserDTO;
import com.epam.jwd.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginService {
    private static final Logger logger = LogManager.getLogger(LoginService.class);
    private static final String INCORRECT_PASSWORD_MESSAGE_CODE = "10";
    private final UserConverter userConverter = new UserConverter();
    private final UserDaoImpl userDAO = new UserDaoImpl();

    /**
     * Allows authenticating user.
     * @param userDTO user
     * @return userDTO if authenticating is successful, otherwise null.
     * @throws DAOException
     */
    public UserDTO authenticate(UserDTO userDTO) throws DAOException, ServiceException {
        logger.debug("authenticate method");
        User loginUser = userConverter.convertToDAO(userDTO);
        User userFromDB = userDAO.findByUsername(loginUser.getUsername());
        loginUser.setSalt(userFromDB.getSalt());
        BcryptService.hashPassword(loginUser);
        if (loginUser.getPassword().equals(userFromDB.getPassword())) {
            loginUser.setUsername(userFromDB.getUsername());
            loginUser.setEmail(userFromDB.getEmail());
            loginUser.setId(userFromDB.getId());
            loginUser.setRoleId(userFromDB.getRoleId());
            loginUser.setFirstName(userFromDB.getFirstName());
            loginUser.setLastName(userFromDB.getLastName());
            return userConverter.convertToDTO(loginUser);
        } else {
            logger.debug("incorrect password");
            throw new ServiceException(INCORRECT_PASSWORD_MESSAGE_CODE);
        }
    }
}
