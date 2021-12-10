package com.epam.jwd.service.validator;

import com.epam.jwd.service.dto.BaseDTO;
import com.epam.jwd.service.exception.ValidatorException;

public interface Validator<T extends BaseDTO> {
    boolean isValid(T t) throws ValidatorException;
}
