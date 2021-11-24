package com.epam.jwd.service.converter;

import com.epam.jwd.dao.entity.Entity;
import com.epam.jwd.service.dto.BaseDTO;

import java.util.List;

public interface Converter <T extends Entity, K extends BaseDTO> {
     T convertToDAO(K k);
     K convertToDTO(T t);
}
