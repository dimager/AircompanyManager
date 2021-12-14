package com.epam.jwd.service.impl;

import com.epam.jwd.service.dto.UserDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class LoginServiceTest {
    LoginService loginService = new LoginService();
    UserService userService = new UserService();
    UserDTO userDTO = new UserDTO();
    UserDTO userDTFromDB = new UserDTO();

    @BeforeEach
    void setUp() {
        userDTO.setUserId(1);
        userDTO.setUsername("Admin");
        userDTO.setPassword("Passw0rd");
    }

    @Test
    void authenticate() {
        Assertions.assertAll(() -> userDTFromDB = userService.findById(userDTO.getUserId()),
                () -> Assertions.assertEquals(userDTFromDB,loginService.authenticate(userDTO)),
                () -> userDTO.setPassword("WrongPassword"),
                () -> Assertions.assertNull(loginService.authenticate(userDTO)));
    }
}