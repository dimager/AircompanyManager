package com.epam.jwd.service.converter;

import com.epam.jwd.dao.entity.Aircraft;
import com.epam.jwd.service.dto.AircraftDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AircraftConverter implements Converter<Aircraft, AircraftDTO> {
    private static final Logger logger = LogManager.getLogger(AircraftConverter.class);
    @Override
    public Aircraft convertToDAO(AircraftDTO aircraftDTO) {
        logger.debug("convertToDAO method");
        Aircraft aircraft = new Aircraft();
        aircraft.setId(aircraftDTO.getAircraftId());
        aircraft.setId(aircraftDTO.getAircraftId());
        aircraft.setProducer(aircraftDTO.getProducer());
        aircraft.setModel(aircraftDTO.getModel());
        aircraft.setRegistrationCode(aircraftDTO.getRegistrationCode());
        return aircraft;
    }

    @Override
    public AircraftDTO convertToDTO(Aircraft aircraft) {
        logger.debug("convertToDTO method");
        AircraftDTO aircraftDTO = new AircraftDTO();
        aircraftDTO.setAircraftId(aircraft.getId());
        aircraftDTO.setProducer(aircraft.getProducer());
        aircraftDTO.setModel(aircraft.getModel());
        aircraftDTO.setRegistrationCode(aircraft.getRegistrationCode());
        return aircraftDTO;
    }
}
