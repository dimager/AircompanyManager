package com.epam.jwd.service.impl;

import com.epam.jwd.dao.entity.Aircraft;
import com.epam.jwd.service.dto.AircraftDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


class AircraftServiceTest {
    final Aircraft aircraft = new Aircraft();
    final AircraftDTO aircraftDTO = new AircraftDTO();
    final List<AircraftDTO> aircraftDTOList = new ArrayList<>();
    final List<Aircraft> aircraftList = new ArrayList<>();
    final List<Aircraft> emptyAircraftList = new ArrayList<>();
    final AircraftService aircraftService = new AircraftService();
    AircraftDTO aircraftDTFromDB = new AircraftDTO();

    @BeforeEach
    void setUp() {
        aircraft.setId(-1);
        aircraft.setProducer("BOING");
        aircraft.setModel("B747");
        aircraft.setInOperation(true);
        aircraft.setRegistrationCode("TEST-222");
        aircraftDTO.setAircraftId(-1);
        aircraftDTO.setProducer("BOING");
        aircraftDTO.setModel("B747");
        aircraftDTO.setInOperation(true);
        aircraftDTO.setRegistrationCode("TEST-333");
        aircraftDTOList.add(aircraftDTO);
        aircraftList.add(aircraft);
    }

    @Test
    @DisplayName("convertToDTOList method test")
    void convertToDTOList() {
        Assertions.assertAll(() -> Assertions.assertNotNull(aircraftService.convertToDTOList(aircraftList)),
                () -> Assertions.assertEquals(aircraftDTOList, aircraftService.convertToDTOList(aircraftList)),
                () -> Assertions.assertTrue(aircraftService.convertToDTOList(emptyAircraftList).isEmpty()));
    }

    @Test
    @DisplayName("test all aircraftService dao methods")
    void testDaoMethods() {
        String newCode = "code-111";
        Assertions.assertAll(() ->   aircraftDTFromDB = aircraftService.saveAircraft(aircraftDTO),
                () -> Assertions.assertInstanceOf(AircraftDTO.class, aircraftService.findAircraftById(aircraftDTFromDB.getAircraftId())),
                () -> Assertions.assertTrue(aircraftService.changeInOperationStatus(aircraftDTFromDB.getAircraftId(), false)),
                () -> Assertions.assertFalse(aircraftService.findAircraftById(aircraftDTFromDB.getAircraftId()).getInOperation()),
                () -> aircraftDTFromDB.setRegistrationCode(newCode),
                () -> Assertions.assertTrue(aircraftService.updateAircraft(aircraftDTFromDB)),
                () -> Assertions.assertEquals(newCode,aircraftService.findAircraftById(aircraftDTFromDB.getAircraftId()).getRegistrationCode()),
                () -> Assertions.assertTrue(aircraftService.deleteAircraftById(aircraftDTFromDB.getAircraftId())));
    }
}