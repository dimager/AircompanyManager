package com.epam.jwd.dao.impl;

import com.epam.jwd.dao.BaseDao;
import com.epam.jwd.dao.SQLQueries;
import com.epam.jwd.dao.connectionpool.ConnectionPool;
import com.epam.jwd.dao.connectionpool.impl.ConnectionPoolImpl;
import com.epam.jwd.dao.entity.Airport;
import com.epam.jwd.dao.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AirportDaoImpl implements BaseDao<Integer, Airport> {

    private static final String EXCEPTION_UPDATE_ERROR_MESSAGE = "208";
    private static final String EXCEPTION_SAVE_ERROR_MESSAGE = "209";
    private static final String EXCEPTION_FINDALL_SQL_ERROR_MESSAGE = "210";
    private static final String EXCEPTION_FIND_BY_ID_ERROR_MESSAGE = "211";
    private static final String EXCEPTION_DELETE_BY_ID_ERROR_MESSAGE = "213";
    private static final String EXCEPTION_SQL_MESSAGE = "200";


    private final static Logger logger = LogManager.getLogger(AircraftDaoImpl.class);
    private final int ONE_UPDATED_ROW = 1;
    private final ConnectionPool connectionPool = ConnectionPoolImpl.getInstance();

    @Override
    public Airport save(Airport airport) throws DAOException {
        logger.debug("save method");
        Connection connection = connectionPool.requestConnection();
        ResultSet resultSet = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.SQL_AIRPORTS_INSERT, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, airport.getName());
            preparedStatement.setString(2, airport.getCountry());
            preparedStatement.setString(3, airport.getCity());
            preparedStatement.setString(4, airport.getIATACode());
            if (preparedStatement.executeUpdate() == ONE_UPDATED_ROW) {
                resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    airport.setId(resultSet.getInt(1));
                    return airport;
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
    public boolean update(Airport airport) throws DAOException {
        logger.debug("update method");
        Connection connection = connectionPool.requestConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.SQL_AIRPORTS_UPDATE_BY_ID)) {
            preparedStatement.setString(1, airport.getName());
            preparedStatement.setString(2, airport.getCountry());
            preparedStatement.setString(3, airport.getCity());
            preparedStatement.setString(4, airport.getIATACode());
            preparedStatement.setInt(5, airport.getId());
            if (preparedStatement.executeUpdate() == ONE_UPDATED_ROW) {
                return true;
            }
            logger.error(EXCEPTION_UPDATE_ERROR_MESSAGE);
            throw new DAOException(EXCEPTION_UPDATE_ERROR_MESSAGE);
        } catch (SQLException e) {
            logger.error(EXCEPTION_UPDATE_ERROR_MESSAGE + EXCEPTION_SQL_MESSAGE, e);
            throw new DAOException(EXCEPTION_UPDATE_ERROR_MESSAGE  + EXCEPTION_SQL_MESSAGE);
        } finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public List<Airport> findAll() throws DAOException {
        logger.debug("findAll method");
        List<Airport> airports = new ArrayList<>();
        Connection connection = connectionPool.requestConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.SQL_AIRPORTS_SELECT_ALL);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Airport airport = new Airport();
                airport.setId(resultSet.getInt(1));
                airport.setName(resultSet.getString(2));
                airport.setCountry(resultSet.getString(3));
                airport.setCity(resultSet.getString(4));
                airport.setIATACode(resultSet.getString(5));
                airports.add(airport);
            }
            return airports;
        } catch (SQLException e) {
            logger.error(EXCEPTION_FINDALL_SQL_ERROR_MESSAGE + EXCEPTION_SQL_MESSAGE, e);
            throw new DAOException(EXCEPTION_FINDALL_SQL_ERROR_MESSAGE + EXCEPTION_SQL_MESSAGE);
        } finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public Airport findById(Integer id) throws DAOException {
        logger.debug("findById method");
        Airport airport = new Airport();
        ResultSet resultSet = null;
        Connection connection = connectionPool.requestConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.SQL_AIRPORTS_SELECT_BY_ID)) {
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                airport.setId(resultSet.getInt(1));
                airport.setName(resultSet.getString(2));
                airport.setCountry(resultSet.getString(3));
                airport.setCity(resultSet.getString(4));
                airport.setIATACode(resultSet.getString(5));
                return airport;
            }
            logger.error(EXCEPTION_FIND_BY_ID_ERROR_MESSAGE);
            throw new DAOException(EXCEPTION_FIND_BY_ID_ERROR_MESSAGE);
        } catch (SQLException e) {
            logger.error(EXCEPTION_FIND_BY_ID_ERROR_MESSAGE + EXCEPTION_SQL_MESSAGE, e);
            throw new DAOException(EXCEPTION_FIND_BY_ID_ERROR_MESSAGE + EXCEPTION_SQL_MESSAGE);
        } finally {
            CloseResultSet(resultSet);
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public boolean deleteById(Integer id) throws DAOException {
        logger.debug("deleteById method");
        Connection connection = connectionPool.requestConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.SQL_AIRPORTS_DELETE_BY_ID)) {
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
}
