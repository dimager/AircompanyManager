package com.epam.jwd.service.validator;

import com.epam.jwd.service.dto.BrigadeDTO;
import com.epam.jwd.service.exception.ValidatorException;

public class BrigadeValidator implements Validator<BrigadeDTO> {
    private static final int MAX_LENGTH = 50;
    private static final int MIN_LENGTH = 1;

    @Override
    public boolean isValid(BrigadeDTO brigadeDTO) throws ValidatorException {
         if(brigadeDTO.getBrigadeName().length() >= MIN_LENGTH
                && brigadeDTO.getBrigadeName().length() <= MAX_LENGTH){
             return true;
         }
         else {
             throw new ValidatorException("Incorrect length");
         }
    }
}
