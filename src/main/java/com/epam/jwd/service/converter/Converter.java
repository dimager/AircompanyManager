package com.epam.jwd.service.converter;

import com.epam.jwd.dao.entity.Entity;
import com.epam.jwd.service.dto.BaseDTO;

public interface Converter <T extends Entity, K extends BaseDTO> {
     /**
      * convert from DTO to DAO
      */
     T convertToDAO(K k);

     /**
      * convert from DAO to DTO
      */
     K convertToDTO(T t);
}
