package com.epam.jwd.service.impl;

import com.epam.jwd.service.dto.AirportDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


class AirportServiceTest {
    final AirportService airportService = new AirportService();
    AirportDTO airportDTO = new AirportDTO();

    @BeforeEach
    void setUp() {
        airportDTO.setName("name");
        airportDTO.setCity("city");
        airportDTO.setIATACode("AAA");
        airportDTO.setCountry("country");
    }


    @Test
    @DisplayName("test all airportService dao methods")
    void testDaoMethods() {
        Assertions.assertAll(() -> airportDTO = Assertions.assertInstanceOf(AirportDTO.class, airportService.saveAirport(airportDTO)),
                () -> Assertions.assertEquals(airportDTO, airportService.findAirportById(airportDTO.getId())),
                () -> Assertions.assertTrue(airportService.findAllAirports().contains(airportDTO)),
                () -> airportDTO.setName("name2"),
                () -> Assertions.assertTrue(airportService.updateAirport(airportDTO)),
                () -> Assertions.assertEquals(airportDTO, airportService.findAirportById(airportDTO.getId())),
                () -> Assertions.assertTrue(airportService.deleteAirport(airportDTO.getId())));
    }
}