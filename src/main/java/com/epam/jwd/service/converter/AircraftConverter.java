package com.epam.jwd.service.converter;

import com.epam.jwd.dao.entity.Aircraft;
import com.epam.jwd.service.dto.AircraftDTO;

public class AircraftConverter implements Converter<Aircraft, AircraftDTO> {
    @Override
    public Aircraft convertToDAO(AircraftDTO aircraftDTO) {
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
        AircraftDTO aircraftDTO = new AircraftDTO();
        aircraftDTO.setAircraftId(aircraft.getId());
        aircraftDTO.setProducer(aircraft.getProducer());
        aircraftDTO.setModel(aircraft.getModel());
        aircraftDTO.setRegistrationCode(aircraft.getRegistrationCode());
        return aircraftDTO;
    }
}
