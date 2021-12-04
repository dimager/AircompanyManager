package com.epam.jwd.service.validator;

import com.epam.jwd.service.dto.FlightDTO;
import com.epam.jwd.service.exception.ValidatorException;

public class FlightValidator implements Validator<FlightDTO> {
    @Override
    public boolean isValid(FlightDTO flightDTO) throws ValidatorException {
        return false;
    }
}
