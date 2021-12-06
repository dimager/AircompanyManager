package com.epam.jwd.service.dto;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BrigadeUserDTO extends BrigadeDTO {
    private List<UserDTO> userDTOs = new ArrayList<>();

    public List<UserDTO> getUserDTOs() {
        return userDTOs;
    }

    public void setUserDTOs(List<UserDTO> userDTOs) {
        this.userDTOs = userDTOs;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        BrigadeUserDTO that = (BrigadeUserDTO) o;
        return Objects.equals(userDTOs, that.userDTOs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), userDTOs);
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
