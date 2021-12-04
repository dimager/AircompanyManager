package com.epam.jwd.service.impl;

import com.epam.jwd.dao.exception.DAOException;
import com.epam.jwd.dao.impl.UserDaoImpl;
import com.epam.jwd.service.converter.UserConverter;
import com.epam.jwd.service.dto.UserDTO;
import com.epam.jwd.service.validator.UserValidator;

import java.util.List;


public class ValidationService {
    private static final Integer USERNAME_IS_NOT_VALID_CODE = 1;
    private static final Integer USERNAME_IS_USED_CODE = 2;
    private static final Integer EMAIL_IS_NOT_VALID_CODE = 3;
    private static final Integer EMAIL_IN_USE_CODE = 4;
    private static final Integer PASSWORD_MISMATCH_CODE = 5;
    private static final Integer PASSWORD_IS_NOT_VALID_CODE = 6;
    private static final Integer FIRSTNAME_IS_NOT_VALID_CODE = 7;
    private static final Integer LASTNAME_IS_NOT_VALID_CODE = 8;
    private static final Integer USERNAME_IS_NOT_REGISTERED_CODE = 9;

    UserDaoImpl userDAO = new UserDaoImpl();
    UserValidator userValidator = new UserValidator();


    public boolean validateNewUser(UserDTO userDTO, String passwordRepeat, List<Integer> errors) throws DAOException {
        errors.clear();
        if (this.isUsernameInUse(userDTO.getUsername())) {
            errors.add(USERNAME_IS_USED_CODE);
        } else if (userValidator.usernameIsNotValid(userDTO.getUsername())) {
            System.out.println("userDTO.getUsername() = " + userDTO.getUsername());
            errors.add(USERNAME_IS_NOT_VALID_CODE);
        }

        if (this.emailInUse(userDTO.getEmail())) {
            errors.add(EMAIL_IN_USE_CODE);
        } else if (userValidator.emailIsNotValid(userDTO.getEmail())) {
            errors.add(EMAIL_IS_NOT_VALID_CODE);
        }

        if (userValidator.passwordsMismatch(userDTO.getPassword(), passwordRepeat)) {
            errors.add(PASSWORD_MISMATCH_CODE);
        } else if (userValidator.passwordIsNotValid(userDTO.getPassword())) {
            errors.add(PASSWORD_IS_NOT_VALID_CODE);
        }

        if (userValidator.firstnameIsNotValid(userDTO.getFirstName())) {
            errors.add(FIRSTNAME_IS_NOT_VALID_CODE);
        }

        if (userValidator.lastnameIsNotValid(userDTO.getLastName())) {
            errors.add(LASTNAME_IS_NOT_VALID_CODE);
        }
        return errors.size() == 0;
    }

    public boolean validateNewPassword(UserDTO userDTO, String passwordRepeat, List<Integer> errors) throws DAOException {
        if (userValidator.passwordsMismatch(userDTO.getPassword(), passwordRepeat)) {
            errors.add(PASSWORD_MISMATCH_CODE);
        } else if (userValidator.passwordIsNotValid(userDTO.getPassword())) {
            errors.add(PASSWORD_IS_NOT_VALID_CODE);
        }
        return errors.size() == 0;
    }

    public boolean validateLogin(UserDTO userDTO, List<Integer> errors) throws DAOException {
        if (!this.isUsernameInUse(userDTO.getUsername())) {
            errors.add(USERNAME_IS_NOT_REGISTERED_CODE);
        } else if (userValidator.usernameIsNotValid(userDTO.getUsername())) {
            errors.add(USERNAME_IS_NOT_VALID_CODE);
        }
        if (userValidator.passwordIsNotValid(userDTO.getPassword())){
            errors.add(PASSWORD_IS_NOT_VALID_CODE);
        }
        return errors.size() == 0;
    }

    private boolean isUsernameInUse(String username) throws DAOException {
        return userDAO.usernameInUse(username);
    }

    private boolean emailInUse(String email) throws DAOException {
        return userDAO.emailInUse(email);
    }


}
