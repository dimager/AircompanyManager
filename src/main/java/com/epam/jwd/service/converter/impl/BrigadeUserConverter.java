package com.epam.jwd.service.converter.impl;

import com.epam.jwd.dao.entity.Brigade;
import com.epam.jwd.service.converter.Converter;
import com.epam.jwd.service.dto.BrigadeUserDTO;
import com.epam.jwd.service.dto.UserDTO;

import java.util.List;
public class BrigadeUserConverter implements Converter<Brigade, BrigadeUserDTO> {
    @Override
    public Brigade convertToDAO(BrigadeUserDTO brigadeUserDTO) {
        Brigade brigade = new Brigade();
        brigade.setId(brigadeUserDTO.getBrigadeId());
        brigade.setBrigadeName(brigadeUserDTO.getBrigadeName());
        brigade.setIsArchived(brigadeUserDTO.getIsArchived());
        return brigade;
    }

    @Override
    public BrigadeUserDTO convertToDTO(Brigade brigade) {
        BrigadeUserDTO brigadeUserDTO = new BrigadeUserDTO();
        brigadeUserDTO.setBrigadeId(brigade.getId());
        brigadeUserDTO.setBrigadeName(brigade.getBrigadeName());
        brigadeUserDTO.setIsArchived(brigade.getIsArchived());
        return brigadeUserDTO;
    }
}
