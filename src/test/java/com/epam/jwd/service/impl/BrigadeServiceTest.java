package com.epam.jwd.service.impl;

import com.epam.jwd.dao.exception.DAOException;
import com.epam.jwd.service.dto.BrigadeDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


class BrigadeServiceTest {
    BrigadeService brigadeService = new BrigadeService();
    BrigadeDTO brigadeDTO;

    @BeforeEach
    void setUp() {
        brigadeDTO = new BrigadeDTO();
        brigadeDTO.setBrigadeName("testBrigade");
    }

    @Test
    @DisplayName("Chech brigade dao service  methods")
    void checkDaoMethod() {
        Assertions.assertAll(() -> brigadeDTO = brigadeService.saveBrigade(brigadeDTO),
                () -> Assertions.assertEquals(brigadeDTO,brigadeService.findById(brigadeDTO.getBrigadeId())),
                () ->  brigadeDTO.setBrigadeName("newName"),
                () -> Assertions.assertTrue(brigadeService.updateBrigade(brigadeDTO)),
                () -> Assertions.assertEquals(brigadeDTO,brigadeService.findById(brigadeDTO.getBrigadeId())),
                () -> Assertions.assertTrue(brigadeService.changeArchiveStatus(brigadeDTO.getBrigadeId(),true)),
                () -> Assertions.assertEquals(true,brigadeService.findById(brigadeDTO.getBrigadeId()).getIsArchived()),
                () -> Assertions.assertTrue(brigadeService.deleteBrigade(brigadeDTO.getBrigadeId())),
                () -> Assertions.assertThrows(DAOException.class,() -> brigadeService.findById(brigadeDTO.getBrigadeId())));
    }
}