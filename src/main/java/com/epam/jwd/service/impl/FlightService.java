package com.epam.jwd.service.impl;

import com.epam.jwd.dao.impl.FlightDaoImpl;
import com.epam.jwd.entity.Flight;
import com.epam.jwd.service.Service;

import java.util.List;

public class FlightService implements Service<Flight> {
    @Override
    public List<Flight> findAll() {
        FlightDaoImpl flightDao = new FlightDaoImpl();
        return flightDao.findAll();
    }
}
