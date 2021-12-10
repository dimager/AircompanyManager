package com.epam.jwd.service.validator.impl;

import com.epam.jwd.service.dto.FlightDTO;
import com.epam.jwd.service.exception.ValidatorException;
import com.epam.jwd.service.validator.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Timestamp;
import java.time.ZonedDateTime;

public class FlightValidator implements Validator<FlightDTO> {
    private static final Logger logger = LogManager.getLogger(FlightValidator.class);
    private static final int MIN_LENGTH = 1;
    private static final int MAX_LENGTH = 10;
    private static final String DATE_TIME_IS_NOT_VALID_ERROR_MESSAGE_CODE = "241";
    private static final String ERROR_MESSAGE_CODE = "242";

    public boolean dateTimeIsValid(Timestamp departureTimestamp) throws ValidatorException {
        logger.debug("dateTimeIsValid method");
        Timestamp timestamp = Timestamp.from(ZonedDateTime.now().toInstant());
        if (departureTimestamp.getTime() > timestamp.getTime()) {
            return true;
        } else {
            throw new ValidatorException(DATE_TIME_IS_NOT_VALID_ERROR_MESSAGE_CODE);
        }
    }

    @Override
    public boolean isValid(FlightDTO flightDTO) throws ValidatorException {
        logger.debug("isValid method");
        if (flightDTO.getFlightCallsign().length() >= MIN_LENGTH &&
                flightDTO.getFlightCallsign().length() <= MAX_LENGTH) {
            return true;
        } else {
            logger.error(ERROR_MESSAGE_CODE);
            throw new ValidatorException(ERROR_MESSAGE_CODE);
        }
    }

}
