package com.epam.jwd.service.validator;

import com.epam.jwd.dao.exception.DAOException;
import com.epam.jwd.service.dto.AirportDTO;
import com.epam.jwd.service.exception.ValidatorException;

public class AirportValidator implements Validator<AirportDTO> {

    private static final String VALIDATOR_EXCEPTION_MESSAGE = "Airport data is not valid.";
 //todo validate
    @Override
    public boolean validate(AirportDTO airportDTO) throws ValidatorException {
        if (airportDTO.getName().length() >= 0) {
            return true;
        }
        throw new ValidatorException(VALIDATOR_EXCEPTION_MESSAGE);
    }
}
