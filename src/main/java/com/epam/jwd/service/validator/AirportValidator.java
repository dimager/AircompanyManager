package com.epam.jwd.service.validator;

import com.epam.jwd.service.dto.AirportDTO;
import com.epam.jwd.service.exception.ValidatorException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AirportValidator implements Validator<AirportDTO> {
    private static final Logger logger = LogManager.getLogger(AirportValidator.class);
    private static final int MIN_LENGTH = 1;
    private static final int MAX_LENGTH = 100;
    private static final int IATACODE_LENGTH = 3;
    private static final String VALIDATOR_EXCEPTION_MESSAGE = "Airport data is not valid.";

    @Override
    public boolean isValid(AirportDTO airportDTO) throws ValidatorException {
        logger.debug("isValid method");
        if (airportDTO.getName().length() >= MIN_LENGTH &&
                airportDTO.getName().length() <= MAX_LENGTH &&
                airportDTO.getCity().length() >= MIN_LENGTH &&
                airportDTO.getCity().length() <= MAX_LENGTH &&
                airportDTO.getCountry().length() >= MIN_LENGTH &&
                airportDTO.getCountry().length() <= MAX_LENGTH &&
                airportDTO.getIATACode().length() == IATACODE_LENGTH) {
            return true;
        }
        logger.error(VALIDATOR_EXCEPTION_MESSAGE);
        throw new ValidatorException(VALIDATOR_EXCEPTION_MESSAGE);
    }
}
