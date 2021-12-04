package com.epam.jwd.dao.impl;

import com.epam.jwd.dao.BaseDao;
import com.epam.jwd.dao.SQLQueries;
import com.epam.jwd.dao.connectionpool.ConnectionPool;
import com.epam.jwd.dao.connectionpool.impl.ConnectionPoolImpl;
import com.epam.jwd.dao.entity.Brigade;
import com.epam.jwd.dao.entity.User;
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

public class BrigadeDaoImpl implements BaseDao<Long, Brigade> {

    private static final String EXCEPTION_SQL_MESSAGE = "SQL exception";
    private static final String EXCEPTION_UPDATE_ERROR_MESSAGE = "Brigade wasn't updated in db. ";
    private static final String EXCEPTION_SAVE_ERROR_MESSAGE = "New brigade wasn't saved in db. ";
    private static final String EXCEPTION_FINDALL_ERROR_MESSAGE = "Find all brigades. ";
    private static final String EXCEPTION_FIND_BY_ID_ERROR_MESSAGE = "Brigades wasn't found. ";
    private static final String EXCEPTION_DELETE_BY_ID_ERROR_MESSAGE = "Brigade wasn't found in DB ";
    private static final String EXCEPTION_GET_BRIGADE_USERS_ERROR_MESSAGE = "Get users from db. ";
    private static final String EXCEPTION_ADD_USER_TO_BRIGADE_EXCEPTION = "User wasn't added to brigade. ";
    private static final String EXCEPTION_REMOVE_USER_FROM_BRIGADE_EXCEPTION = "User wasn't removed to brigade. ";

    private final static Logger logger = LogManager.getLogger(BrigadeDaoImpl.class);
    private final int ONE_UPDATED_ROW = 1;
    private final ConnectionPool connectionPool = ConnectionPoolImpl.getInstance();

    public List<User> getBrigadeUsers(long brigadeId) throws DAOException {
        List<User> users = new ArrayList<>();
        Connection connection = connectionPool.requestConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.SELECT_BRIGADES_USER)) {
            preparedStatement.setLong(1, brigadeId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong(1));
                user.setRoleId(resultSet.getInt(2));
                user.setFirstName(resultSet.getString(3));
                user.setLastName(resultSet.getString(4));
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            logger.error(EXCEPTION_GET_BRIGADE_USERS_ERROR_MESSAGE + EXCEPTION_SQL_MESSAGE, e);
            throw new DAOException(EXCEPTION_GET_BRIGADE_USERS_ERROR_MESSAGE + EXCEPTION_SQL_MESSAGE);
        } finally {
            ConnectionPoolImpl.getInstance().returnConnection(connection);
        }
    }


    public boolean addUserToBrigade(long userId, long brigadeId) throws DAOException {
        Connection connection = connectionPool.requestConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.SQL_BRIGADE_HAS_USERS_INSERT)) {
            preparedStatement.setLong(1, brigadeId);
            preparedStatement.setLong(2, userId);
            if (preparedStatement.executeUpdate() == ONE_UPDATED_ROW) {
                return true;
            }
            throw new DAOException(EXCEPTION_ADD_USER_TO_BRIGADE_EXCEPTION);
        } catch (SQLException e) {
            logger.error(EXCEPTION_ADD_USER_TO_BRIGADE_EXCEPTION + EXCEPTION_SQL_MESSAGE, e);
            throw new DAOException(EXCEPTION_ADD_USER_TO_BRIGADE_EXCEPTION + EXCEPTION_SQL_MESSAGE);
        } finally {
            connectionPool.returnConnection(connection);
        }
    }

    public boolean removeUserFromBrigade(long userId, long brigadeId) throws DAOException {
        Connection connection = connectionPool.requestConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.SQL_BRIGADE_HAS_USERS_DELETE)) {
            preparedStatement.setLong(1, brigadeId);
            preparedStatement.setLong(2, userId);
            if (preparedStatement.executeUpdate() == ONE_UPDATED_ROW) {
                return true;
            }
            throw new DAOException(EXCEPTION_REMOVE_USER_FROM_BRIGADE_EXCEPTION);
        } catch (SQLException e) {
            logger.error(EXCEPTION_REMOVE_USER_FROM_BRIGADE_EXCEPTION + EXCEPTION_SQL_MESSAGE, e);
            throw new DAOException(EXCEPTION_REMOVE_USER_FROM_BRIGADE_EXCEPTION + EXCEPTION_SQL_MESSAGE);
        } finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public Brigade save(Brigade brigade) throws DAOException {
        Connection connection = connectionPool.requestConnection();
        ResultSet resultSet = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.SQL_BRIGADES_INSERT, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, brigade.getBrigadeName());
            if (preparedStatement.executeUpdate() == ONE_UPDATED_ROW) {
                resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    brigade.setId(resultSet.getLong(1));
                    return brigade;
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
    public boolean update(Brigade brigade) throws DAOException {
        Connection connection = connectionPool.requestConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.SQL_BRIGADES_UPDATE_BY_ID)) {
            preparedStatement.setString(1, brigade.getBrigadeName());
            preparedStatement.setLong(2, brigade.getId());
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
    public List<Brigade> findAll() throws DAOException {
        List<Brigade> brigades = new ArrayList<>();
        Connection connection = connectionPool.requestConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.SQL_BRIGADES_SELECT_ALL);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Brigade brigade = new Brigade();
                brigade.setId(resultSet.getLong(1));
                brigade.setBrigadeName(resultSet.getString(2));
                brigades.add(brigade);
            }
            return brigades;
        } catch (SQLException e) {
            logger.error(EXCEPTION_FINDALL_ERROR_MESSAGE + EXCEPTION_SQL_MESSAGE, e);
            throw new DAOException(EXCEPTION_FINDALL_ERROR_MESSAGE + EXCEPTION_SQL_MESSAGE);
        } finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public Brigade findById(Long id) throws DAOException {
        Brigade brigade = new Brigade();
        ResultSet resultSet = null;
        Connection connection = ConnectionPoolImpl.getInstance().requestConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.SQL_BRIGADES_SELECT_BY_ID)) {
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                brigade.setId(resultSet.getLong(1));
                brigade.setBrigadeName(resultSet.getString(2));
                return brigade;
            }
            logger.error(EXCEPTION_FIND_BY_ID_ERROR_MESSAGE);
            throw new DAOException(EXCEPTION_FIND_BY_ID_ERROR_MESSAGE);
        } catch (SQLException e) {
            logger.error(EXCEPTION_FIND_BY_ID_ERROR_MESSAGE + EXCEPTION_SQL_MESSAGE, e);
            throw new DAOException(EXCEPTION_FIND_BY_ID_ERROR_MESSAGE + EXCEPTION_SQL_MESSAGE);
        } finally {
            CloseResultSet(resultSet);
            ConnectionPoolImpl.getInstance().returnConnection(connection);
        }
    }

    @Override
    public boolean deleteById(Long id) throws DAOException {
        Connection connection = connectionPool.requestConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.SQL_BRIGADES_DELETE_BY_ID)) {
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
