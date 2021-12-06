package com.epam.jwd.service.validator;

import com.epam.jwd.service.dto.FlightDTO;
import com.epam.jwd.service.exception.ValidatorException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FlightValidator implements Validator<FlightDTO> {
    private static final Logger logger = LogManager.getLogger(FlightValidator.class);
    private static final String VALIDATOR_EXCEPTION_MESSAGE = "Length of flight callsign is incorrect";
    private static final int MIN_LENGTH = 1;
    private static final int MAX_LENGTH = 10;

    @Override
    public boolean isValid(FlightDTO flightDTO) throws ValidatorException {
        logger.debug("isValid method");
        if (flightDTO.getFlightCallsign().length() >= MIN_LENGTH &&
            flightDTO.getFlightCallsign().length() <= MAX_LENGTH) {
            return true;
        } else {
            logger.error(VALIDATOR_EXCEPTION_MESSAGE);
            throw new ValidatorException(VALIDATOR_EXCEPTION_MESSAGE);
        }
    }
}
