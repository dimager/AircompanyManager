package com.epam.jwd.service.impl;

import com.epam.jwd.dao.entity.Role;
import com.epam.jwd.dao.entity.User;
import com.epam.jwd.dao.exception.DAOException;
import com.epam.jwd.dao.impl.UserDaoImpl;
import com.epam.jwd.service.converter.impl.UserConverter;
import com.epam.jwd.service.dto.UserDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SignupService {
    private static final Logger logger = LogManager.getLogger(SignupService.class);
    UserDaoImpl userDao = new UserDaoImpl();
    UserConverter userConverter = new UserConverter();
    public UserDTO saveNewUser(UserDTO userDTO) throws DAOException {
        logger.debug("saveNewUser method");
        userDTO.setRole(Role.GUEST);
        User newUser = userConverter.convertToDAO(userDTO);
        BcryptService.crypt(newUser);
        return userConverter.convertToDTO(userDao.save(newUser));
    }
}
