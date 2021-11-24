package com.epam.jwd.service.validator;

import com.epam.jwd.dao.exception.DAOException;
import com.epam.jwd.service.dto.BaseDTO;
import com.epam.jwd.service.exception.ValidatorException;

public interface Validator<T extends BaseDTO> {
    boolean validate(T t) throws ValidatorException;
}
