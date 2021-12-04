package com.epam.jwd.service.impl;

import com.epam.jwd.dao.entity.Role;
import com.epam.jwd.dao.entity.User;
import com.epam.jwd.dao.exception.DAOException;
import com.epam.jwd.dao.impl.UserDaoImpl;
import com.epam.jwd.service.converter.UserConverter;
import com.epam.jwd.service.dto.UserDTO;

public class SignupService {
    UserDaoImpl userDao = new UserDaoImpl();
    UserConverter userConverter = new UserConverter();
    public UserDTO saveNewUser(UserDTO userDTO) throws DAOException {
        User newUser = userConverter.convertToDAO(userDTO);
        newUser.setRoleId(Role.GUEST.getRoleId());
        BcryptService.crypt(newUser);
        return userConverter.convertToDTO(userDao.save(newUser));
    }
}
