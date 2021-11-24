package com.epam.jwd.service.dto;


import java.util.ArrayList;
import java.util.List;

public class BrigadeUserDTO extends BrigadeDTO {
    private List<UserDTO> userDTOs = new ArrayList<>();

    public List<UserDTO> getUserDTOs() {
        return userDTOs;
    }

    public void setUserDTOs(List<UserDTO> userDTOs) {
        this.userDTOs = userDTOs;
    }


    @Override
    public String toString() {
        return "BrigadeUserDTO{" +
                "brigadeId=" + super.getBrigadeId() +
                ", brigadeName='" + super.getBrigadeName() + '\'' +
                ", userDTOs=" + userDTOs +
                '}';
    }
}
