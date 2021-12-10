package com.epam.jwd.service.validator.impl;

import com.epam.jwd.service.dto.BrigadeDTO;
import com.epam.jwd.service.exception.ValidatorException;
import com.epam.jwd.service.validator.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BrigadeValidator implements Validator<BrigadeDTO> {
    private static final Logger logger = LogManager.getLogger(BrigadeValidator.class);
    private static final int MAX_LENGTH = 50;
    private static final int MIN_LENGTH = 1;
    private static final String VALIDATOR_EXCEPTION_MESSAGE = "240";

    @Override
    public boolean isValid(BrigadeDTO brigadeDTO) throws ValidatorException {
        logger.debug("isValid method");
         if(brigadeDTO.getBrigadeName().length() >= MIN_LENGTH
                && brigadeDTO.getBrigadeName().length() <= MAX_LENGTH){
             return true;
         }
         else {
             logger.error(VALIDATOR_EXCEPTION_MESSAGE);
             throw new ValidatorException(VALIDATOR_EXCEPTION_MESSAGE);
         }
    }
}
