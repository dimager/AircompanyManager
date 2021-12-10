package com.epam.jwd.dao.impl;

import com.epam.jwd.dao.BaseDao;
import com.epam.jwd.dao.SQLQueries;
import com.epam.jwd.dao.connectionpool.ConnectionPool;
import com.epam.jwd.dao.connectionpool.impl.ConnectionPoolImpl;
import com.epam.jwd.dao.entity.Aircraft;
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


public class AircraftDaoImpl implements BaseDao<Integer, Aircraft> {
    private static final Logger logger = LogManager.getLogger(AircraftDaoImpl.class);
    private static final String EXCEPTION_UPDATE_ERROR_MESSAGE = "203";
    private static final String EXCEPTION_SAVE_ERROR_MESSAGE = "204";
    private static final String EXCEPTION_FINDALL_SQL_ERROR_MESSAGE = "204";
    private static final String EXCEPTION_FIND_BY_ID_ERROR_MESSAGE = "205";
    private static final String EXCEPTION_DELETE_BY_ID_ERROR_MESSAGE = "206";
    private static final String EXCEPTION_SQL_MESSAGE = "200";
    private static final String EXCEPTION_CHANGE_OPERATION_STATUS_ERROR_MESSAGE = "202";

    private final int ONE_UPDATED_ROW = 1;
    private final ConnectionPool connectionPool = ConnectionPoolImpl.getInstance();

    @Override
    public Aircraft save(Aircraft aircraft) throws DAOException {
        logger.debug("save method");
        Connection connection = connectionPool.requestConnection();
        ResultSet resultSet = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.SQL_AIRCRAFTS_INSERT, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, aircraft.getProducer());
            preparedStatement.setString(2, aircraft.getModel());
            preparedStatement.setString(3, aircraft.getRegistrationCode());
            if (preparedStatement.executeUpdate() == ONE_UPDATED_ROW) {
                resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    aircraft.setId(resultSet.getInt(1));
                    return aircraft;
                }
            }
            logger.error(EXCEPTION_SAVE_ERROR_MESSAGE);
            throw new DAOException(EXCEPTION_SAVE_ERROR_MESSAGE);

        } catch (SQLException e) {
            logger.error(EXCEPTION_SAVE_ERROR_MESSAGE + EXCEPTION_SQL_MESSAGE, e);
            throw new DAOException(EXCEPTION_SAVE_ERROR_MESSAGE);
        } finally {
            CloseResultSet(resultSet);
            connectionPool.returnConnection(connection);
        }
    }

    public boolean ChangeInOperationStatus(int aircraftId, boolean inOperation) throws DAOException {
        logger.debug("ChangeInOperationStatus method");
        Connection connection = connectionPool.requestConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.SQL_AIRCRAFTS_UPDATE_OPERATION_STATUS_BY_ID)) {
            preparedStatement.setBoolean(1, inOperation);
            preparedStatement.setInt(2, aircraftId);
            if (preparedStatement.executeUpdate() == ONE_UPDATED_ROW) {
                return true;
            }
            logger.error(EXCEPTION_CHANGE_OPERATION_STATUS_ERROR_MESSAGE);
            throw new DAOException(EXCEPTION_CHANGE_OPERATION_STATUS_ERROR_MESSAGE);
        } catch (SQLException e) {
            logger.error(EXCEPTION_CHANGE_OPERATION_STATUS_ERROR_MESSAGE + EXCEPTION_SQL_MESSAGE, e);
            throw new DAOException(EXCEPTION_CHANGE_OPERATION_STATUS_ERROR_MESSAGE);
        } finally {
            connectionPool.returnConnection(connection);
        }
    }

    /**
     * Update Aircraft entry in database by ID.
     *
     * @param aircraft- aircraft entity.
     * @return true - if entry is successfully updated, otherwise false.
     */
    @Override
    public boolean update(Aircraft aircraft) throws DAOException {
        logger.debug("update method");
        Connection connection = connectionPool.requestConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.SQL_AIRCRAFTS_UPDATE_BY_ID)) {
            preparedStatement.setString(1, aircraft.getProducer());
            preparedStatement.setString(2, aircraft.getModel());
            preparedStatement.setString(3, aircraft.getRegistrationCode());
            preparedStatement.setInt(4, aircraft.getId());
            if (preparedStatement.executeUpdate() == ONE_UPDATED_ROW) {
                return true;
            }
            logger.error(EXCEPTION_UPDATE_ERROR_MESSAGE);
            throw new DAOException(EXCEPTION_UPDATE_ERROR_MESSAGE);
        } catch (SQLException e) {
            logger.error(EXCEPTION_UPDATE_ERROR_MESSAGE + EXCEPTION_SQL_MESSAGE, e);
            throw new DAOException(EXCEPTION_UPDATE_ERROR_MESSAGE);
        } finally {
            connectionPool.returnConnection(connection);
        }
    }

    /**
     * @return
     */
    @Override
    public List<Aircraft> findAll() throws DAOException {
        logger.debug("findAll method");
        List<Aircraft> aircrafts = new ArrayList<>();
        Connection connection = connectionPool.requestConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.SQL_AIRCRAFTS_SELECT_ALL);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Aircraft aircraft = new Aircraft();
                aircraft.setId(resultSet.getInt(1));
                aircraft.setProducer(resultSet.getString(2));
                aircraft.setModel(resultSet.getString(3));
                aircraft.setRegistrationCode(resultSet.getString(4));
                aircraft.setInOperation(resultSet.getBoolean(5));
                aircrafts.add(aircraft);
            }
            return aircrafts;
        } catch (SQLException e) {
            logger.error(EXCEPTION_FINDALL_SQL_ERROR_MESSAGE + EXCEPTION_SQL_MESSAGE, e);
            throw new DAOException(EXCEPTION_FINDALL_SQL_ERROR_MESSAGE);
        } finally {
            connectionPool.returnConnection(connection);
        }

    }

    @Override
    public Aircraft findById(Integer id) throws DAOException {
        logger.debug("findById method");
        Aircraft aircraft = new Aircraft();
        ResultSet resultSet = null;
        Connection connection = connectionPool.requestConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.SQL_AIRCRAFTS_SELECT_BY_ID)) {
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                aircraft.setId(resultSet.getInt(1));
                aircraft.setProducer(resultSet.getString(2));
                aircraft.setModel(resultSet.getString(3));
                aircraft.setRegistrationCode(resultSet.getString(4));
                aircraft.setInOperation(resultSet.getBoolean(5));
                return aircraft;
            }
            logger.error(EXCEPTION_FIND_BY_ID_ERROR_MESSAGE);
            throw new DAOException(EXCEPTION_FIND_BY_ID_ERROR_MESSAGE);
        } catch (SQLException e) {
            logger.error(EXCEPTION_FIND_BY_ID_ERROR_MESSAGE + EXCEPTION_SQL_MESSAGE, e);
            throw new DAOException(EXCEPTION_FIND_BY_ID_ERROR_MESSAGE);
        } finally {
            CloseResultSet(resultSet);
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public boolean deleteById(Integer id) throws DAOException {
        logger.debug("deleteById method");
        Connection connection = connectionPool.requestConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.SQL_AIRCRAFTS_DELETE_BY_ID)) {
            preparedStatement.setLong(1, id);
            if (preparedStatement.executeUpdate() == ONE_UPDATED_ROW) {
                return true;
            }
            logger.error(EXCEPTION_DELETE_BY_ID_ERROR_MESSAGE);
            throw new DAOException(EXCEPTION_DELETE_BY_ID_ERROR_MESSAGE);
        } catch (SQLException e) {
            logger.error(EXCEPTION_DELETE_BY_ID_ERROR_MESSAGE + EXCEPTION_SQL_MESSAGE, e);
            throw new DAOException(EXCEPTION_DELETE_BY_ID_ERROR_MESSAGE);
        } finally {
            connectionPool.returnConnection(connection);
        }
    }
}
