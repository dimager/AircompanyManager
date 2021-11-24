package com.epam.jwd.view;

import com.epam.jwd.dao.entity.Role;
import com.epam.jwd.dao.exception.DAOException;
import com.epam.jwd.service.dto.FlightDTO;
import com.epam.jwd.service.impl.FlightService;

import java.sql.SQLException;
import java.util.Locale;

public class main {
    public static void main(String[] args) throws SQLException, InterruptedException {

        FlightService flightService =  new FlightService();
        try {
            FlightDTO flightDTO = flightService.findFlightById(10);

            System.out.println(flightDTO.toString());
        } catch (DAOException e) {
            e.printStackTrace();
        }


    }

}
