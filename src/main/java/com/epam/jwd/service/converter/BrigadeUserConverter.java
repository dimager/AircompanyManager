package com.epam.jwd.service.converter;

import com.epam.jwd.dao.entity.Brigade;
import com.epam.jwd.service.dto.BrigadeUserDTO;
import com.epam.jwd.service.dto.UserDTO;
import com.epam.jwd.service.impl.UserService;

import java.util.List;
//todo delete
public class BrigadeUserConverter implements Converter<Brigade, BrigadeUserDTO> {
    @Override
    public Brigade convertToDAO(BrigadeUserDTO brigadeUserDTO) {
        Brigade brigade = new Brigade();
        brigade.setId(brigadeUserDTO.getBrigadeId());
        brigade.setBrigadeName(brigadeUserDTO.getBrigadeName());
        List<UserDTO> userDTOList = brigadeUserDTO.getUserDTOs();
        return brigade;
    }

    @Override
    public BrigadeUserDTO convertToDTO(Brigade brigade) {
        BrigadeUserDTO brigadeUserDTO = new BrigadeUserDTO();
        brigadeUserDTO.setBrigadeId(brigade.getId());
        brigadeUserDTO.setBrigadeName(brigade.getBrigadeName());
        return brigadeUserDTO;
    }
}
