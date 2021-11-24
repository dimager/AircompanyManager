package com.epam.jwd.dao.impl;

import com.epam.jwd.dao.BaseDao;
import com.epam.jwd.dao.api.SQLQueries;
import com.epam.jwd.dao.connectionpool.ConnectionPool;
import com.epam.jwd.dao.connectionpool.impl.ConnectionPoolImpl;
import com.epam.jwd.dao.entity.Aircraft;
import com.epam.jwd.dao.entity.Airport;
import com.epam.jwd.dao.entity.Brigade;
import com.epam.jwd.dao.entity.Flight;
import com.epam.jwd.dao.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class FlightDaoImpl implements BaseDao<Long, Flight> {
    private static final String EXCEPTION_UPDATE_ERROR_MESSAGE = "Flight wasn't updated in db. ";
    private static final String EXCEPTION_SAVE_ERROR_MESSAGE = "New flight wasn't saved in db. ";
    private static final String EXCEPTION_FINDALL_SQL_ERROR_MESSAGE = "Find all flights. ";
    private static final String EXCEPTION_FIND_BY_ID_ERROR_MESSAGE = "Flight wasn't  found. ";
    private static final String EXCEPTION_FIND_BY_ID_SQL_ERROR_MESSAGE = "Flight wasn't  found. ";
    private static final String EXCEPTION_DELETE_BY_ID_ERROR_MESSAGE = "Flight wasn't deleted. ";
    private static final String EXCEPTION_DELETE_BY_ID_SQL_ERROR_MESSAGE = "Flight wasn't deleted. ";
    private static final String EXCEPTION_SQL_MESSAGE = "SQL exception";
    private final static Logger logger = LogManager.getLogger(FlightDaoImpl.class);
    private static final String EXCEPTION_FILL_THE_FLIGHT_ERROR_MESSAGE = "FillLight procedure error";
    private final int ONE_UPDATED_ROW = 1;
    private final ConnectionPool connectionPool = ConnectionPoolImpl.getInstance();

    @Override
    public Flight save(Flight flight) throws DAOException {
        Connection connection = connectionPool.requestConnection();
        ResultSet resultSet = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.SQL_FLIGHTS_INSERT, Statement.RETURN_GENERATED_KEYS)) {
            statementFlightFill(flight, preparedStatement);
            if (preparedStatement.executeUpdate() == ONE_UPDATED_ROW) {
                if (resultSet.next()) {
                    resultSet = preparedStatement.getGeneratedKeys();
                    flight.setId(resultSet.getLong(1));
                    return flight;
                }
            }
            logger.error(EXCEPTION_SAVE_ERROR_MESSAGE);
            throw new DAOException(EXCEPTION_SAVE_ERROR_MESSAGE);
        } catch (SQLException e) {
            logger.error(EXCEPTION_SAVE_ERROR_MESSAGE + EXCEPTION_SQL_MESSAGE, e);
            throw new DAOException(EXCEPTION_SAVE_ERROR_MESSAGE + EXCEPTION_SQL_MESSAGE);
        } finally {
            CloseResultSet(resultSet);
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public boolean update(Flight flight) throws DAOException {
        Connection connection = connectionPool.requestConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.SQL_FLIGHTS_UPDATE_BY_ID)) {
            statementFlightFill(flight, preparedStatement);
            preparedStatement.setLong(7, flight.getId());
            if (preparedStatement.executeUpdate() == ONE_UPDATED_ROW){
                return true;
            };
            logger.error(EXCEPTION_UPDATE_ERROR_MESSAGE);
            throw new DAOException(EXCEPTION_UPDATE_ERROR_MESSAGE);
        } catch (SQLException e) {
            logger.error(EXCEPTION_UPDATE_ERROR_MESSAGE + EXCEPTION_SQL_MESSAGE, e);
            throw new DAOException(EXCEPTION_UPDATE_ERROR_MESSAGE + EXCEPTION_SQL_MESSAGE);
        } finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public List<Flight> findAll() throws DAOException {
        List<Flight> flights = new ArrayList<>();
        Connection connection = connectionPool.requestConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.SQL_FLIGHTS_SELECT_ALL);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Flight flight = new Flight();
                flightFill(flight, resultSet);
                flights.add(flight);
            }
            return flights;
        } catch (SQLException e) {
            logger.error(EXCEPTION_FINDALL_SQL_ERROR_MESSAGE + EXCEPTION_SQL_MESSAGE, e);
            throw new DAOException(EXCEPTION_FINDALL_SQL_ERROR_MESSAGE + EXCEPTION_SQL_MESSAGE);
        } finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public Flight findById(Long id) throws DAOException {
        Flight flight = new Flight();
        ResultSet resultSet = null;
        Connection connection = ConnectionPoolImpl.getInstance().requestConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.SQL_FLIGHTS_SELECT_BY_ID)) {
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                flightFill(flight, resultSet);
                return flight;
            }
            logger.error(EXCEPTION_FIND_BY_ID_ERROR_MESSAGE);
            throw new DAOException(EXCEPTION_FIND_BY_ID_ERROR_MESSAGE);
        } catch (SQLException e) {
            logger.error(EXCEPTION_FIND_BY_ID_ERROR_MESSAGE);
            throw new DAOException(EXCEPTION_FIND_BY_ID_ERROR_MESSAGE);
        } finally {
            CloseResultSet(resultSet);
            ConnectionPoolImpl.getInstance().returnConnection(connection);
        }
    }

    @Override
    public boolean deleteById(Long id) throws DAOException {
        Connection connection = connectionPool.requestConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.SQL_FLIGHTS_DELETE_BY_ID)) {
            preparedStatement.setLong(1, id);
            if (preparedStatement.executeUpdate() == ONE_UPDATED_ROW){
                return true;
            }
            logger.error(EXCEPTION_DELETE_BY_ID_ERROR_MESSAGE);
            throw new DAOException(EXCEPTION_DELETE_BY_ID_ERROR_MESSAGE);
        } catch (SQLException e) {
            logger.error(EXCEPTION_DELETE_BY_ID_SQL_ERROR_MESSAGE + EXCEPTION_SQL_MESSAGE, e);
            throw new DAOException(EXCEPTION_DELETE_BY_ID_SQL_ERROR_MESSAGE + EXCEPTION_SQL_MESSAGE);
        } finally {
            connectionPool.returnConnection(connection);
        }
    }

   /* public boolean fillFlightWithDBData(Flight flight, Airport dep, Airport dest, Brigade brigade, Aircraft aircraft) throws DAOException {
        Connection connection = connectionPool.requestConnection();
        try (CallableStatement callableStatement = connection.prepareCall(SQLQueries.PROC2)) {
            callableStatement.setInt(1, flight.getDepartureAirportId());
            callableStatement.setInt(2, flight.getDestinationAirportId());
            callableStatement.setLong(3, flight.getBrigadeId());
            callableStatement.setInt(4, flight.getFlightAircraftId());
            callableStatement.registerOutParameter(5, Types.VARCHAR, 100);
            callableStatement.registerOutParameter(6, Types.VARCHAR, 100);
            callableStatement.registerOutParameter(7, Types.VARCHAR, 100);
            callableStatement.registerOutParameter(8, Types.VARCHAR, 3);
            callableStatement.registerOutParameter(9, Types.VARCHAR, 100);
            callableStatement.registerOutParameter(10, Types.VARCHAR, 100);
            callableStatement.registerOutParameter(11, Types.VARCHAR, 100);
            callableStatement.registerOutParameter(12, Types.VARCHAR, 3);
            callableStatement.registerOutParameter(13, Types.VARCHAR, 50);
            callableStatement.registerOutParameter(14, Types.VARCHAR, 50);
            callableStatement.registerOutParameter(15, Types.VARCHAR, 50);
            callableStatement.registerOutParameter(16, Types.VARCHAR, 10);
            ResultSet resultSet = callableStatement.executeQuery();
            if (resultSet.next()) {
                dep.setId(flight.getDepartureAirportId());
                dep.setName(resultSet.getString(5));
                dep.setCountry(resultSet.getString(6));
                dep.setCity(resultSet.getString(7));
                dep.setIATACode(resultSet.getString(8));
                dest.setId(flight.getDestinationAirportId());
                dest.setName(resultSet.getString(9));
                dest.setCountry(resultSet.getString(10));
                dest.setCity(resultSet.getString(11));
                dest.setIATACode(resultSet.getString(12));
                brigade.setId(flight.getBrigadeId());
                brigade.setBrigadeName(resultSet.getString(13));
                aircraft.setId(flight.getFlightAircraftId());
                aircraft.setProducer(resultSet.getString(14));
                aircraft.setModel(resultSet.getString(15));
                aircraft.setRegistrationCode(resultSet.getString(16));
                return true;
            }
            logger.error(EXCEPTION_FILL_THE_FLIGHT_ERROR_MESSAGE);
            throw new DAOException(EXCEPTION_FILL_THE_FLIGHT_ERROR_MESSAGE);
        } catch (SQLException e) {
            logger.error(EXCEPTION_FILL_THE_FLIGHT_ERROR_MESSAGE + EXCEPTION_SQL_MESSAGE, e);
            throw new DAOException(EXCEPTION_FILL_THE_FLIGHT_ERROR_MESSAGE + EXCEPTION_SQL_MESSAGE, e);
        } finally {
            connectionPool.returnConnection(connection);
        }
    }*/


    private void flightFill(Flight flight, ResultSet resultSet) throws SQLException {
        flight.setId(resultSet.getLong(1));
        flight.setFlightAircraftId(resultSet.getInt(2));
        flight.setBrigadeId(resultSet.getLong(3));
        flight.setDepartureAirportId(resultSet.getInt(4));
        flight.setDestinationAirportId(resultSet.getInt(5));
        flight.setFlightCallsign(resultSet.getString(6));
        flight.setDepartureDateTime(resultSet.getTimestamp(7));
    }

    private void statementFlightFill(Flight flight, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setInt(1, flight.getFlightAircraftId());
        preparedStatement.setLong(2, flight.getBrigadeId());
        preparedStatement.setInt(3, flight.getDepartureAirportId());
        preparedStatement.setInt(4, flight.getDestinationAirportId());
        preparedStatement.setString(5, flight.getFlightCallsign());
        preparedStatement.setTimestamp(6, flight.getDepartureDateTime());
    }
}
