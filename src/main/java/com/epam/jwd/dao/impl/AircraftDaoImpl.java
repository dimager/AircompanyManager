package com.epam.jwd.dao.impl;

import com.epam.jwd.dao.BaseDao;
import com.epam.jwd.connectioonpool.ConnectionPollImpl;
import com.epam.jwd.entity.Aircraft;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AircraftDaoImpl implements BaseDao<Long, Aircraft> {
    private static final String SELECT_AIRCRAFT_BY_ID_SQL = "SELECT aircraft_id, aircraft_producer, aircraft_model FROM aircompany_manager_db.aircrafts where aircraft_id=?";
    private static final String SELECT_ALL_AIRCRAFTS_SQL = "SELECT aircraft_id, aircraft_producer, aircraft_model FROM aircompany_manager_db.aircrafts";
    private static final String INSERT_AIRCRAFT_SQL = "INSERT INTO aircompany_manager_db.aircrafts (aircraft_producer,aircraft_model) VALUES (?,?)";
    private static final String DELETE_AIRCRAFT_BY_ID_SQL = "DELETE FROM aircompany_manager_db.aircrafts WHERE aircraft_id = ?";

    @Override
    public boolean save(Aircraft entity) {
        Connection connection = ConnectionPollImpl.getInstance().requestConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_AIRCRAFT_SQL);
            preparedStatement.setString(1, entity.getProducer());
            preparedStatement.setString(2, entity.getModel());
            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        } finally {
            ConnectionPollImpl.getInstance().returnConnection(connection);
        }
    }

    @Override
    public List<Aircraft> findAll() {
        List<Aircraft> aircrafts = new ArrayList<>();
        Connection connection = ConnectionPollImpl.getInstance().requestConnection();
        try (Statement statement = connection.createStatement();) {
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_AIRCRAFTS_SQL);
            while (resultSet.next()) {
                Aircraft aircraft = new Aircraft();
                aircraft.setId(resultSet.getInt(1));
                aircraft.setProducer(resultSet.getString(2));
                aircraft.setModel(resultSet.getString(3));
                aircrafts.add(aircraft);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            ConnectionPollImpl.getInstance().returnConnection(connection);
            return aircrafts;
        }
    }

    @Override
    public Aircraft findById(Long id) {
        Aircraft aircraft = new Aircraft();
        Connection connection = ConnectionPollImpl.getInstance().requestConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_AIRCRAFT_BY_ID_SQL);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                aircraft.setId(resultSet.getInt(1));
                aircraft.setProducer(resultSet.getString(2));
                aircraft.setModel(resultSet.getString(3));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            ConnectionPollImpl.getInstance().returnConnection(connection);
            return aircraft;
        }
    }

    @Override
    public boolean deleteById(Long id) {
        Connection connection = ConnectionPollImpl.getInstance().requestConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_AIRCRAFT_BY_ID_SQL);
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            ConnectionPollImpl.getInstance().returnConnection(connection);
            return false;
        }
    }
}
