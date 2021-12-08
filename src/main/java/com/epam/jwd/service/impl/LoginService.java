package com.epam.jwd.service.impl;

import com.epam.jwd.dao.entity.User;
import com.epam.jwd.dao.exception.DAOException;
import com.epam.jwd.dao.impl.UserDaoImpl;
import com.epam.jwd.service.converter.UserConverter;
import com.epam.jwd.service.dto.UserDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class LoginService {
    private static final Logger logger = LogManager.getLogger(LoginService.class);
    private static final int INCORRECT_PASSWORD_MESSAGE_CODE = 121;
    UserConverter userConverter = new UserConverter();
    UserDaoImpl userDAO = new UserDaoImpl();

    public UserDTO authenticate(UserDTO userDTO, List<Integer> errors) throws DAOException {
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
            errors.add(INCORRECT_PASSWORD_MESSAGE_CODE);
            return null;
        }
    }
}
