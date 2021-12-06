package com.epam.jwd.service.impl;

import com.epam.jwd.dao.exception.DAOException;
import com.epam.jwd.dao.impl.UserDaoImpl;
import com.epam.jwd.service.converter.UserConverter;
import com.epam.jwd.service.dto.UserDTO;
import com.epam.jwd.service.validator.UserValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;


public class ValidationService {
    private static final Logger logger = LogManager.getLogger(ValidationService.class);
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
        logger.debug("validateNewUser method");
        errors.clear();
        if (this.isUsernameInUse(userDTO.getUsername())) {
            logger.debug("error code: " + USERNAME_IS_USED_CODE);
            errors.add(USERNAME_IS_USED_CODE);
        } else if (userValidator.usernameIsNotValid(userDTO.getUsername())) {
            logger.debug("error code: " + USERNAME_IS_NOT_VALID_CODE);
            errors.add(USERNAME_IS_NOT_VALID_CODE);
        }

        if (this.emailInUse(userDTO.getEmail())) {
            logger.debug("error code: " + EMAIL_IN_USE_CODE);
            errors.add(EMAIL_IN_USE_CODE);
        } else if (userValidator.emailIsNotValid(userDTO.getEmail())) {
            logger.debug("error code: " + EMAIL_IS_NOT_VALID_CODE);
            errors.add(EMAIL_IS_NOT_VALID_CODE);
        }

        if (userValidator.passwordsMismatch(userDTO.getPassword(), passwordRepeat)) {
            logger.debug("error code: " + PASSWORD_MISMATCH_CODE);
            errors.add(PASSWORD_MISMATCH_CODE);
        } else if (userValidator.passwordIsNotValid(userDTO.getPassword())) {
            logger.debug("error code: " + PASSWORD_IS_NOT_VALID_CODE);
            errors.add(PASSWORD_IS_NOT_VALID_CODE);
        }

        if (userValidator.firstnameIsNotValid(userDTO.getFirstName())) {
            logger.debug("error code: " + FIRSTNAME_IS_NOT_VALID_CODE);
            errors.add(FIRSTNAME_IS_NOT_VALID_CODE);
        }

        if (userValidator.lastnameIsNotValid(userDTO.getLastName())) {
            logger.debug("error code: " + LASTNAME_IS_NOT_VALID_CODE);
            errors.add(LASTNAME_IS_NOT_VALID_CODE);
        }
        return errors.size() == 0;
    }

    public boolean validateNewPassword(UserDTO userDTO, String passwordRepeat, List<Integer> errors) throws DAOException {
        if (userValidator.passwordsMismatch(userDTO.getPassword(), passwordRepeat)) {
            logger.debug("error code: " + PASSWORD_MISMATCH_CODE);
            errors.add(PASSWORD_MISMATCH_CODE);
        } else if (userValidator.passwordIsNotValid(userDTO.getPassword())) {
            logger.debug("error code: " + PASSWORD_IS_NOT_VALID_CODE);
            errors.add(PASSWORD_IS_NOT_VALID_CODE);
        }
        return errors.size() == 0;
    }

    public boolean validateLogin(UserDTO userDTO, List<Integer> errors) throws DAOException {
        if (!this.isUsernameInUse(userDTO.getUsername())) {
            logger.debug("error code: " + USERNAME_IS_NOT_REGISTERED_CODE);
            errors.add(USERNAME_IS_NOT_REGISTERED_CODE);
        } else if (userValidator.usernameIsNotValid(userDTO.getUsername())) {
            logger.debug("error code: " + USERNAME_IS_NOT_VALID_CODE);
            errors.add(USERNAME_IS_NOT_VALID_CODE);
        }
        if (userValidator.passwordIsNotValid(userDTO.getPassword())){
            logger.debug("error code: " + PASSWORD_IS_NOT_VALID_CODE);
            errors.add(PASSWORD_IS_NOT_VALID_CODE);
        }
        return errors.size() == 0;
    }

    private boolean isUsernameInUse(String username) throws DAOException {
        logger.debug("isUsernameInUse method");
        return userDAO.usernameInUse(username);
    }

    private boolean emailInUse(String email) throws DAOException {
        logger.debug("emailInUse method");
        return userDAO.emailInUse(email);
    }


}
