package com.epam.jwd.service.impl;

import com.epam.jwd.dao.entity.Role;
import com.epam.jwd.dao.entity.User;
import com.epam.jwd.dao.exception.DAOException;
import com.epam.jwd.service.dto.UserDTO;

import java.util.Objects;

public class RegistrationService {
    public boolean registerNewUser(UserDTO userDTO) throws DAOException {
        UserService userService = new UserService();
        return Objects.nonNull(userService.save(userDTO));
    }
}
