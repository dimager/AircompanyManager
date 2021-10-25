package com.epam.jwd.dao.impl;

import com.epam.jwd.connectioonpool.ConnectionPollImpl;
import com.epam.jwd.dao.BaseDao;
import com.epam.jwd.entity.Flight;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class FlightDaoImpl implements BaseDao<Long, Flight> {
    private static final String SELECT_FLIGHT_BY_ID_SQL = "SELECT brigades.brigade_id, brigades.brigade_name FROM aircompany_manager_db.brigades WHERE brigade_id = ?";
    private static final String SELECT_ALL_FLIGHTS_SQL = "SELECT flights.flight_id, flights.aircraft_id, flights.flight_callsign, flights.departure_airport, flights.destination_airpor, flights.departure_datetime FROM aircompany_manager_db.flights;";
    private static final String INSERT_FLIGHT_SQL = "INSERT INTO aircompany_manager_db.brigades (brigade_name) VALUES (?)";
    private static final String DELETE_FLIGHT_BY_ID_SQL = "DELETE FROM aircompany_manager_db.brigades WHERE brigade_id = ?";


    @Override
    public boolean save(Flight entity) {
        return false;
    }

    @Override
    public List<Flight> findAll() {
        List<Flight> flights = new ArrayList<>();
        Connection connection = ConnectionPollImpl.getInstance().requestConnection();
        try (Statement statement = connection.createStatement();) {
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_FLIGHTS_SQL);
            while (resultSet.next()) {
                Flight flight = new Flight();
                flight.setId(resultSet.getLong(1));
                flight.setFlightAircraft(new AircraftDaoImpl().findById(resultSet.getLong(2)));
                flight.setFlightCallsign(resultSet.getString(3));
                flight.setDepartureAirport(resultSet.getString(4));
                flight.setDestinationAirport(resultSet.getString(5));
                flight.setDepartureDateTime(LocalDateTime.parse(resultSet.getString(6), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                flights.add(flight);
            }
        } catch (Exception throwables) {
            System.out.println("eerror" + throwables);
            throwables.printStackTrace();
        } finally {
            ConnectionPollImpl.getInstance().returnConnection(connection);
            return flights;
        }

    }

    @Override
    public Flight findById(Long id) {
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }


}
