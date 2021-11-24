package com.epam.jwd.service.converter;

import com.epam.jwd.dao.entity.Brigade;
import com.epam.jwd.service.dto.BrigadeDTO;

import java.util.ArrayList;
import java.util.List;

public class BrigadeConverter implements Converter<Brigade, BrigadeDTO> {
    @Override
    public Brigade convertToDAO(BrigadeDTO brigadeDTO) {
        Brigade brigade = new Brigade();
        brigade.setId(brigadeDTO.getBrigadeId());
        brigade.setBrigadeName(brigadeDTO.getBrigadeName());
        return brigade;
    }

    @Override
    public BrigadeDTO convertToDTO(Brigade brigade) {
        BrigadeDTO brigadeDTO = new BrigadeDTO();
        brigadeDTO.setBrigadeId(brigade.getId());
        brigadeDTO.setBrigadeName(brigade.getBrigadeName());
        return brigadeDTO;
    }

    public List<BrigadeDTO> convertToDTOList(List<Brigade> brigades) {
        List<BrigadeDTO> brigadeDTOs = new ArrayList<>();
        brigades.forEach(brigade -> brigadeDTOs.add(convertToDTO(brigade)));
        return brigadeDTOs;
    }

    public List<Brigade> convertToDAOList(List<BrigadeDTO> brigadeDTOs) {
        List<Brigade> brigades = new ArrayList<>();
        brigadeDTOs.forEach(brigade -> brigades.add(convertToDAO(brigade)));
        return brigades;
    }

}
