package com.epam.jwd.service.impl;

import com.epam.jwd.dao.entity.User;
import com.epam.jwd.dao.exception.DAOException;
import com.epam.jwd.dao.impl.UserDaoImpl;
import com.epam.jwd.service.converter.UserConverter;
import com.epam.jwd.service.dto.UserDTO;

import java.util.List;

public class LoginService {
    private static final int INCORRECT_PASSWORD_MESSAGE_CODE = 121;
    UserConverter userConverter = new UserConverter();
    UserDaoImpl userDAO = new UserDaoImpl();

    public UserDTO authenticate(UserDTO userDTO, List<Integer> errors) throws DAOException {
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
            errors.add(INCORRECT_PASSWORD_MESSAGE_CODE);
            return null;
        }
    }
}
