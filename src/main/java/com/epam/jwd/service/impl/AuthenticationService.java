package com.epam.jwd.service.impl;

import com.epam.jwd.dao.entity.User;
import com.epam.jwd.dao.exception.DAOException;
import com.epam.jwd.dao.impl.UserDaoImpl;
import com.epam.jwd.service.dto.UserDTO;


public class AuthenticationService {
    static UserDaoImpl userDAO = new UserDaoImpl();

    public static boolean usernameInUse(UserDTO userDTO) throws DAOException {
        return userDAO.usernameInUse(userDTO.getUsername());
    }

    public static boolean usernameInUse(String username) throws DAOException {
        return userDAO.usernameInUse(username);
    }

    public static boolean emailInUse(UserDTO userDTO) throws DAOException {
        return userDAO.emailInUse(userDTO.getEmail());
    }

    public static boolean emailInUse(String email) throws DAOException {
        return userDAO.emailInUse(email);
    }


    public static boolean authorized(User user) throws DAOException {
        User userFromDB = userDAO.findByUsername(user.getUsername());
        user.setSalt(userFromDB.getSalt());
        BcryptService.hashPassword(user);
        if (user.getPassword().equals(userFromDB.getPassword())){
            user.setUsername(userFromDB.getUsername());
            user.setEmail(userFromDB.getEmail());
            user.setId(userFromDB.getId());
            user.setRoleId(userFromDB.getRoleId());
            user.setFirstName(userFromDB.getFirstName());
            user.setLastName(userFromDB.getLastName());
            return true;
        }
        else {
            return false;
        }
    }

}
