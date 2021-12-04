package com.epam.jwd.service.validator;

import com.epam.jwd.service.dto.UserDTO;
import com.epam.jwd.service.exception.ValidatorException;

import java.util.Objects;
import java.util.regex.Pattern;

public class UserValidator implements Validator<UserDTO> {
    private static final String PASSWORD_PATTERN = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";
    private static final String USERNAME_PATTERN = "[A-Za-z0-9]+";
    private static final int PASSWORD_MAX_LENGTH = 50;
    private static final int PASSWORD_MIN_LENGTH = 8;
    private static final int NAME_MIN_LENGTH = 3;
    private static final int NAME_MAX_LENGTH = 50;
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
    private static final int EMAIL_MAX_LENGTH = 320;
    private static final int EMAIL_MIN_LENGTH = 3;

    public boolean emailIsNotValid(String email) {
        return Objects.nonNull(email) && (email.length() > EMAIL_MAX_LENGTH ||
                email.length() < EMAIL_MIN_LENGTH ||
                !Pattern.compile(EMAIL_PATTERN).matcher(email).find());
    }
    public boolean passwordIsNotValid(String password) {
        return Objects.nonNull(password) && (password.length() > PASSWORD_MAX_LENGTH ||
                password.length() < PASSWORD_MIN_LENGTH ||
                !Pattern.compile(PASSWORD_PATTERN).matcher(password).find());
    }

    public boolean passwordsMismatch(String password, String passwordRepeat) {
        if (Objects.nonNull(password) && Objects.nonNull(passwordRepeat)) {
            return !password.equals(passwordRepeat);
        } else {
            return true;
        }
    }

    public boolean changePasswordValidate(String password, String passwordRepeat){
            return !passwordIsNotValid(password) && !passwordsMismatch(password,passwordRepeat);
    }

    public boolean usernameIsNotValid(String username) {
        return Objects.nonNull(username) && (username.length() > NAME_MAX_LENGTH ||
                username.length() < NAME_MIN_LENGTH ||
                !Pattern.compile(USERNAME_PATTERN).matcher(username).find());
    }

    public boolean firstnameIsNotValid(String firstName){
        return Objects.nonNull(firstName) &&
                (firstName.length() > NAME_MAX_LENGTH || firstName.length() < NAME_MIN_LENGTH);
    }
    public boolean lastnameIsNotValid(String lastName){
        return Objects.nonNull(lastName) &&
                (lastName.length() > NAME_MAX_LENGTH || lastName.length() < NAME_MIN_LENGTH);
    }

    @Override
    public boolean isValid(UserDTO userDTO) throws ValidatorException {
        return !(emailIsNotValid(userDTO.getEmail()) ||
                passwordIsNotValid(userDTO.getPassword()) ||
                usernameIsNotValid(userDTO.getUsername())) ||
                firstnameIsNotValid(userDTO.getFirstName()) ||
                lastnameIsNotValid(userDTO.getLastName());
    }
}
