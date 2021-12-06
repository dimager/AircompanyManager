package com.epam.jwd.service.validator;

import com.epam.jwd.service.dto.AircraftDTO;
import com.epam.jwd.service.exception.ValidatorException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AircraftValidator implements Validator<AircraftDTO> {
    private static final Logger logger = LogManager.getLogger(AircraftValidator.class);
    private static final int MAX_LENGTH = 50;
    private static final int MAX_REGCODE_LENGTH = 10;
    private static final int MIN_LENGTH = 1;
    private static final String VALIDATOR_EXCEPTION_MESSAGE = "Wrong length of parameter";


    @Override
    public boolean isValid(AircraftDTO aircraftDTO) throws ValidatorException {
        logger.debug("isValid method");
        if (aircraftDTO.getProducer().length() <= MAX_LENGTH
                && aircraftDTO.getModel().length() <= MAX_LENGTH
                && aircraftDTO.getRegistrationCode().length() <= MAX_REGCODE_LENGTH
                && aircraftDTO.getProducer().length() >= MIN_LENGTH
                && aircraftDTO.getModel().length() >= MIN_LENGTH &&
                aircraftDTO.getRegistrationCode().length() >= MIN_LENGTH) {
            return true;
        } else {
            logger.error(VALIDATOR_EXCEPTION_MESSAGE);
            throw new ValidatorException(VALIDATOR_EXCEPTION_MESSAGE);
        }
    }
}
