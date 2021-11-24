package com.epam.jwd.service.validator;

import com.epam.jwd.dao.entity.User;
import com.epam.jwd.service.exception.ValidatorException;

import java.util.Objects;
import java.util.regex.Pattern;

public class UserValidator {
    private static final String PASSWORD_PATTERN = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\\s).*$";
    private static final String USERNAME_PATTERN = "[A-Za-z0-9]+";
    private static final int MAX_LENGTH = 50;
    private static final int MIN_LENGTH = 8;
    private static final String PASSWORDS_DONT_MATCH = "Passwords do not match! ";
    private static final String LOGINANDPASSWORD_LENGTH_ERROR = "Login and password length error";

    public static boolean newUserPasswordValidate(String password, String passwordRepeat) throws ValidatorException {
        if (Objects.nonNull(passwordRepeat)
                && passwordValidate(password)
                && password.equals(passwordRepeat)) {
            return true;
        } else {
            throw new ValidatorException(PASSWORDS_DONT_MATCH);
        }

    }

    public static boolean loginValidate(User user) throws ValidatorException {
        if (passwordValidate(user.getPassword())
                && usernameValidate(user.getUsername())) {
            return true;
        } else {
            throw new ValidatorException(LOGINANDPASSWORD_LENGTH_ERROR);
        }

    }

    private static boolean passwordValidate(String password) {
        return Objects.nonNull(password)
                && password.length() >= MIN_LENGTH
                && password.length() <= MAX_LENGTH
                && Pattern.compile(PASSWORD_PATTERN).matcher(password).find();
    }

    private static boolean usernameValidate(String username) {
        return Objects.nonNull(username)
                && username.length() >= MIN_LENGTH
                && username.length() <= MAX_LENGTH
                && Pattern.compile(USERNAME_PATTERN).matcher(username).find();
    }

}
