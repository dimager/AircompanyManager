package com.epam.jwd.dao.impl;

import com.epam.jwd.dao.BaseDao;
import com.epam.jwd.dao.SQLQueries;
import com.epam.jwd.dao.connectionpool.ConnectionPool;
import com.epam.jwd.dao.connectionpool.impl.ConnectionPoolImpl;
import com.epam.jwd.dao.entity.Flight;
import com.epam.jwd.dao.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
    private static final String EXCEPTION_FINDALL_ERROR_MESSAGE = "Find all flights. ";
    private static final String EXCEPTION_FIND_BY_ID_ERROR_MESSAGE = "Flight wasn't  found. ";
    private static final String EXCEPTION_DELETE_BY_ID_ERROR_MESSAGE = "Flight wasn't deleted. ";
    private static final String EXCEPTION_SQL_MESSAGE = "SQL exception";
    private final static Logger logger = LogManager.getLogger(FlightDaoImpl.class);
    private final int ONE_UPDATED_ROW = 1;
    private final ConnectionPool connectionPool = ConnectionPoolImpl.getInstance();

    @Override
    public Flight save(Flight flight) throws DAOException {
        logger.debug("save method");
        Connection connection = connectionPool.requestConnection();
        ResultSet resultSet = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.SQL_FLIGHTS_INSERT, Statement.RETURN_GENERATED_KEYS)) {
            fillFlightStatement(flight, preparedStatement);
            if (preparedStatement.executeUpdate() == ONE_UPDATED_ROW) {
                resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
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
        logger.debug("update method");
        Connection connection = connectionPool.requestConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.SQL_FLIGHTS_UPDATE_BY_ID)) {
            fillFlightStatement(flight, preparedStatement);
            preparedStatement.setLong(7, flight.getId());
            if (preparedStatement.executeUpdate() == ONE_UPDATED_ROW) {
                return true;
            }
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
        logger.debug("findAll method");
        List<Flight> flights = new ArrayList<>();
        Connection connection = connectionPool.requestConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.SQL_FLIGHTS_SELECT_ALL);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Flight flight = new Flight();
                fillFlight(flight, resultSet);
                flights.add(flight);
            }
            return flights;
        } catch (SQLException e) {
            logger.error(EXCEPTION_FINDALL_ERROR_MESSAGE + EXCEPTION_SQL_MESSAGE, e);
            throw new DAOException(EXCEPTION_FINDALL_ERROR_MESSAGE + EXCEPTION_SQL_MESSAGE);
        } finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public Flight findById(Long id) throws DAOException {
        logger.debug("findById method");
        Flight flight = new Flight();
        ResultSet resultSet = null;
        Connection connection = ConnectionPoolImpl.getInstance().requestConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.SQL_FLIGHTS_SELECT_BY_ID)) {
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                fillFlight(flight, resultSet);
                return flight;
            }
            logger.error(EXCEPTION_FIND_BY_ID_ERROR_MESSAGE);
            throw new DAOException(EXCEPTION_FIND_BY_ID_ERROR_MESSAGE);
        } catch (SQLException e) {
            logger.error(EXCEPTION_FIND_BY_ID_ERROR_MESSAGE  + EXCEPTION_SQL_MESSAGE, e);
            throw new DAOException(EXCEPTION_FIND_BY_ID_ERROR_MESSAGE + EXCEPTION_SQL_MESSAGE);
        } finally {
            CloseResultSet(resultSet);
            ConnectionPoolImpl.getInstance().returnConnection(connection);
        }
    }

    @Override
    public boolean deleteById(Long id) throws DAOException {
        logger.debug("deleteById method");
        Connection connection = connectionPool.requestConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.SQL_FLIGHTS_DELETE_BY_ID)) {
            preparedStatement.setLong(1, id);
            if (preparedStatement.executeUpdate() == ONE_UPDATED_ROW) {
                return true;
            }
            logger.error(EXCEPTION_DELETE_BY_ID_ERROR_MESSAGE);
            throw new DAOException(EXCEPTION_DELETE_BY_ID_ERROR_MESSAGE);
        } catch (SQLException e) {
            logger.error(EXCEPTION_DELETE_BY_ID_ERROR_MESSAGE + EXCEPTION_SQL_MESSAGE, e);
            throw new DAOException(EXCEPTION_DELETE_BY_ID_ERROR_MESSAGE + EXCEPTION_SQL_MESSAGE);
        } finally {
            connectionPool.returnConnection(connection);
        }
    }

    public boolean updateBrigade(long flightId, long brigadeId) throws DAOException {
        logger.debug("updateBrigade method");
        Connection connection = connectionPool.requestConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.SQL_FLIGHTS_UPDATE_BRIGADE)) {
            preparedStatement.setLong(1, brigadeId);
            preparedStatement.setLong(2, flightId);
            if (preparedStatement.executeUpdate() == ONE_UPDATED_ROW) {
                return true;
            }
            logger.error(EXCEPTION_UPDATE_ERROR_MESSAGE);
            throw new DAOException(EXCEPTION_UPDATE_ERROR_MESSAGE);
        } catch (SQLException e) {
            logger.error(EXCEPTION_UPDATE_ERROR_MESSAGE + EXCEPTION_SQL_MESSAGE, e);
            throw new DAOException(EXCEPTION_UPDATE_ERROR_MESSAGE + EXCEPTION_SQL_MESSAGE);
        } finally {
            connectionPool.returnConnection(connection);
        }
    }




    private void fillFlight(Flight flight, ResultSet resultSet) throws SQLException {
        logger.debug("flightFill method");
        flight.setId(resultSet.getLong(1));
        flight.setFlightAircraftId(resultSet.getInt(2));
        flight.setBrigadeId(resultSet.getLong(3));
        flight.setDepartureAirportId(resultSet.getInt(4));
        flight.setDestinationAirportId(resultSet.getInt(5));
        flight.setFlightCallsign(resultSet.getString(6));
        flight.setDepartureDateTime(resultSet.getTimestamp(7));
    }

    private void fillFlightStatement(Flight flight, PreparedStatement preparedStatement) throws SQLException {
        logger.debug("statementFlightFill method");
        preparedStatement.setInt(1, flight.getFlightAircraftId());
        if (flight.getBrigadeId() == 0) {
            preparedStatement.setNull(2, Types.NULL);
        } else {
            preparedStatement.setLong(2, flight.getBrigadeId());
        }
        preparedStatement.setInt(3, flight.getDepartureAirportId());
        preparedStatement.setInt(4, flight.getDestinationAirportId());
        preparedStatement.setString(5, flight.getFlightCallsign());
        preparedStatement.setTimestamp(6, flight.getDepartureDateTime());
    }
}
