package com.epam.jwd.service.validator;

import com.epam.jwd.service.dto.AircraftDTO;
import com.epam.jwd.service.exception.ValidatorException;

public class AircraftValidator implements Validator<AircraftDTO> {
    private static final int MAX_LENGTH = 50;
    private static final int MAX_REGCODE_LENGTH = 10;
    private static final int MIN_LENGTH = 1;

    @Override
    public boolean validate(AircraftDTO aircraftDTO) throws ValidatorException {
        if (aircraftDTO.getProducer().length() <= MAX_LENGTH
                && aircraftDTO.getModel().length() <= MAX_LENGTH
                && aircraftDTO.getRegistrationCode().length() <= MAX_REGCODE_LENGTH
                && aircraftDTO.getProducer().length() >= MIN_LENGTH
                && aircraftDTO.getModel().length() >= MIN_LENGTH &&
                aircraftDTO.getRegistrationCode().length() >= MIN_LENGTH) {
            return true;
        } else {
            throw new ValidatorException("Wrong length of parameter");
        }
    }
}
