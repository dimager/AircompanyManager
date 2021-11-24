package com.epam.jwd.service.converter;

import com.epam.jwd.dao.entity.Role;
import com.epam.jwd.dao.entity.User;
import com.epam.jwd.service.dto.UserDTO;

import java.util.ArrayList;
import java.util.List;

public class UserConverter implements Converter<User, UserDTO> {
    @Override
    public User convertToDAO(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getUserId());
        user.setRoleId(userDTO.getRole().getRoleId());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        return user;
    }

    @Override
    public UserDTO convertToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user.getId());
        userDTO.setRole(Role.getRoleName(user.getRoleId()));
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmail(user.getEmail());
        userDTO.setUsername(user.getUsername());
        return userDTO;
    }

    public List<UserDTO> convertWorkerUsersListToDTO(List<User> users) {
        List<UserDTO> userDTOs = new ArrayList<>();
            users.stream().filter(user -> user.getRoleId() > 2).forEach(user -> userDTOs.add(convertToDTO(user)));
        return userDTOs;
    }

    public List<UserDTO> convertAllUsersListToDTO(List<User> users) {
        List<UserDTO> userDTOs = new ArrayList<>();
        users.stream().forEach(user -> userDTOs.add(convertToDTO(user)));
        return userDTOs;
    }




    public List<User> convertUserDTOListToDao(List<UserDTO> userDTOs) {
        List<User> users = new ArrayList<>();
        userDTOs.forEach(userDTO -> users.add(convertToDAO(userDTO)));
        return users;
    }
}
