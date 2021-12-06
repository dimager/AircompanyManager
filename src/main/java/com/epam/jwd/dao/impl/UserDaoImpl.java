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

public class UserDaoImpl implements BaseDao<Long, User> {
    private final static Logger logger = LogManager.getLogger(UserDaoImpl.class);

    private static final String EXCEPTION_SQL_MESSAGE = "SQL exception";
    private static final String EXCEPTION_UPDATE_ERROR_MESSAGE = "User wasn't updated in db. ";
    private static final String EXCEPTION_SAVE_ERROR_MESSAGE = "New user wasn't saved in db. ";
    private static final String EXCEPTION_FINDALL_ERROR_MESSAGE = "Find all users. ";
    private static final String EXCEPTION_FIND_BY_ID_ERROR_MESSAGE = "User wasn't found. ";
    private static final String EXCEPTION_DELETE_BY_ID_ERROR_MESSAGE = "User wasn't found in DB ";
    private static final String EXCEPTION_EMAIL_IN_USE_EXCEPTION = "Email in use ";
    private static final String EXCEPTION_USERNAME_IN_USE_EXCEPTION = "Username in use ";
    private static final String EXCEPTION_UPDATE_NEW_USER_EXCEPTION = "User wasn't updated in db. ";
    private static final String EXCEPTION_FIND_BY_USERNAME_ERROR_MESSAGE = "Username wasn't found. ";
    private static final String EXCEPTION_GET_FREE_USERS_ERROR_MESSAGE = "Users not found";
    private static final String EXCEPTION_FIND_USER_FLIGHTS_ERROR_MESSAGE = "Find users flights. ";
    private final int ONE_UPDATED_ROW = 1;
    ConnectionPool connectionPool = ConnectionPoolImpl.getInstance();

    public boolean emailInUse(String email) throws DAOException {
        logger.debug("emailInUse method");
        ResultSet resultSet = null;
        Connection connection = connectionPool.requestConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.SQL_CHECK_EMAIL)) {
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();
            return resultSet.next() && resultSet.getString(1).equals(email);
        } catch (SQLException e) {
            logger.error(EXCEPTION_EMAIL_IN_USE_EXCEPTION + EXCEPTION_SQL_MESSAGE, e);
            throw new DAOException(EXCEPTION_EMAIL_IN_USE_EXCEPTION + EXCEPTION_SQL_MESSAGE);
        } finally {
            CloseResultSet(resultSet);
            connectionPool.returnConnection(connection);
        }

    }

    public boolean usernameInUse(String username) throws DAOException {
        logger.debug("usernameInUse method");
        ResultSet resultSet = null;
        Connection connection = connectionPool.requestConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.SQL_CHECK_FREEUSERNAME)) {
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            return resultSet.next() && resultSet.getString(1).equals(username);
        } catch (SQLException e) {
            logger.error(EXCEPTION_USERNAME_IN_USE_EXCEPTION + EXCEPTION_SQL_MESSAGE, e);
            throw new DAOException(EXCEPTION_USERNAME_IN_USE_EXCEPTION + EXCEPTION_SQL_MESSAGE);
        } finally {
            CloseResultSet(resultSet);
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public User save(User user) throws DAOException {
        logger.debug("save method");
        Connection connection = connectionPool.requestConnection();
        ResultSet resultSet = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.SQL_USERS_INSERT, Statement.RETURN_GENERATED_KEYS)) {
            userToStatement(user, preparedStatement);
            if (preparedStatement.executeUpdate() == ONE_UPDATED_ROW) {
                resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    user.setId(resultSet.getLong(1));
                    return user;
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
    public boolean update(User user) throws DAOException {
        logger.debug("update method");
        Connection connection = connectionPool.requestConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.SQL_USERS_UPDATE_BY_ID)) {
            userToStatement(user, preparedStatement);
            preparedStatement.setLong(8, user.getId());
            if (preparedStatement.executeUpdate() == ONE_UPDATED_ROW) {
                return true;
            }
            throw new DAOException(EXCEPTION_UPDATE_ERROR_MESSAGE);

        } catch (SQLException e) {
            logger.error(EXCEPTION_UPDATE_NEW_USER_EXCEPTION + EXCEPTION_SQL_MESSAGE, e);
            throw new DAOException(EXCEPTION_UPDATE_NEW_USER_EXCEPTION + EXCEPTION_SQL_MESSAGE);
        } finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public List<User> findAll() throws DAOException {
        logger.debug("findAll method");
        List<User> users = new ArrayList<>();
        Connection connection = connectionPool.requestConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.SQL_USERS_SELECT_ALL);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                User user = new User();
                resultSetToUser(user, resultSet);
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            logger.error(EXCEPTION_FINDALL_ERROR_MESSAGE + EXCEPTION_SQL_MESSAGE, e);
            throw new DAOException(EXCEPTION_FINDALL_ERROR_MESSAGE + EXCEPTION_SQL_MESSAGE);
        } finally {
            connectionPool.returnConnection(connection);
        }
    }


    @Override
    public User findById(Long id) throws DAOException {
        logger.debug("findById method");
        User users = new User();
        ResultSet resultSet = null;
        Connection connection = ConnectionPoolImpl.getInstance().requestConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.SQL_USERS_SELECT_BY_ID)) {
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                resultSetToUser(users, resultSet);
            } else {
                logger.error(EXCEPTION_FIND_BY_ID_ERROR_MESSAGE);
                throw new DAOException(EXCEPTION_FIND_BY_ID_ERROR_MESSAGE);
            }
            return users;
        } catch (SQLException e) {
            logger.error(EXCEPTION_FIND_BY_ID_ERROR_MESSAGE + EXCEPTION_SQL_MESSAGE, e);
            throw new DAOException(EXCEPTION_FIND_BY_ID_ERROR_MESSAGE + EXCEPTION_SQL_MESSAGE);
        } finally {
            CloseResultSet(resultSet);
            ConnectionPoolImpl.getInstance().returnConnection(connection);
        }
    }

    public List<User> getBrigadeFreeUsers(long brigadeId) throws DAOException {
        logger.debug("getBrigadeFreeUsers method");
        List<User> users = new ArrayList<>();
        Connection connection = connectionPool.requestConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.SELECT_BRIGADES_FREE_USER)) {
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
            logger.error(EXCEPTION_GET_FREE_USERS_ERROR_MESSAGE + EXCEPTION_SQL_MESSAGE, e);
            throw new DAOException(EXCEPTION_GET_FREE_USERS_ERROR_MESSAGE + EXCEPTION_SQL_MESSAGE);
        } finally {
            ConnectionPoolImpl.getInstance().returnConnection(connection);
        }
    }

    @Override
    public boolean deleteById(Long id) throws DAOException {
        logger.debug("deleteById method");
        Connection connection = connectionPool.requestConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.SQL_USERS_DELETE_BY_ID)) {
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

    public List<Long> getUserFlights(long userId) throws DAOException {
        logger.debug("getUserFlights method");
        List<Long> flightsIds = new ArrayList<>();
        ResultSet resultSet = null;
        Connection connection = ConnectionPoolImpl.getInstance().requestConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.SELECT_USER_FLIGHTS)) {
            preparedStatement.setLong(1, userId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                flightsIds.add(resultSet.getLong(1));
            }
            return flightsIds;
        } catch (SQLException e) {
            logger.error(EXCEPTION_FIND_USER_FLIGHTS_ERROR_MESSAGE + EXCEPTION_SQL_MESSAGE, e);
            throw new DAOException(EXCEPTION_FIND_USER_FLIGHTS_ERROR_MESSAGE + EXCEPTION_SQL_MESSAGE);
        } finally {
            CloseResultSet(resultSet);
            ConnectionPoolImpl.getInstance().returnConnection(connection);
        }
    }

    public List<Brigade> getUserBrigades(long userId) throws DAOException {
        logger.debug("getUserBrigades method");
        List<Brigade> userBrigades = new ArrayList<>();
        ResultSet resultSet = null;
        Connection connection = ConnectionPoolImpl.getInstance().requestConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.SELECT_USER_BRIGADES)) {
            preparedStatement.setLong(1, userId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Brigade brigade = new Brigade();
                brigade.setId(resultSet.getLong(1));
                brigade.setBrigadeName(resultSet.getString(2));
                userBrigades.add(brigade);
            }
            return userBrigades;
        } catch (SQLException e) {
            logger.error(EXCEPTION_FIND_USER_FLIGHTS_ERROR_MESSAGE + EXCEPTION_SQL_MESSAGE, e);
            throw new DAOException(EXCEPTION_FIND_USER_FLIGHTS_ERROR_MESSAGE + EXCEPTION_SQL_MESSAGE);
        } finally {
            CloseResultSet(resultSet);
            ConnectionPoolImpl.getInstance().returnConnection(connection);
        }
    }

    private void resultSetToUser(User user, ResultSet resultSet) throws SQLException {
        logger.debug("resultSetToUser method");
        user.setId(resultSet.getLong(1));
        user.setRoleId(resultSet.getInt(2));
        user.setFirstName(resultSet.getString(3));
        user.setLastName(resultSet.getString(4));
        user.setEmail(resultSet.getString(5));
        user.setUsername(resultSet.getString(6));
        user.setPassword(resultSet.getString(7));
        user.setSalt(resultSet.getString(8));
    }

    private void userToStatement(User user, PreparedStatement preparedStatement) throws SQLException {
        logger.debug("userToStatement method");
        preparedStatement.setInt(1, user.getRoleId());
        preparedStatement.setString(2, user.getFirstName());
        preparedStatement.setString(3, user.getLastName());
        preparedStatement.setString(4, user.getEmail());
        preparedStatement.setString(5, user.getUsername());
        preparedStatement.setString(6, user.getPassword());
        preparedStatement.setString(7, user.getSalt());
    }

    public User findByUsername(String username) throws DAOException {
        logger.debug("findByUsername method");
        User user = new User();
        ResultSet resultSet = null;
        Connection connection = ConnectionPoolImpl.getInstance().requestConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.SQL_USERS_SELECT_BY_USERNAME)) {
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                resultSetToUser(user, resultSet);
            } else {
                logger.error(EXCEPTION_FIND_BY_USERNAME_ERROR_MESSAGE);
                throw new DAOException(EXCEPTION_FIND_BY_USERNAME_ERROR_MESSAGE);
            }
            return user;
        } catch (SQLException e) {
            logger.error(EXCEPTION_FIND_BY_USERNAME_ERROR_MESSAGE + EXCEPTION_SQL_MESSAGE, e);
            throw new DAOException(EXCEPTION_FIND_BY_USERNAME_ERROR_MESSAGE + EXCEPTION_SQL_MESSAGE);
        } finally {
            CloseResultSet(resultSet);
            ConnectionPoolImpl.getInstance().returnConnection(connection);
        }
    }

    public boolean updateRole(long userId, int roleId) throws DAOException {
        logger.debug("updateRole method");
        Connection connection = connectionPool.requestConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.SQL_USERS_UPDATE_ROLE)) {
            preparedStatement.setInt(1, roleId);
            preparedStatement.setLong(2, userId);
            if (preparedStatement.executeUpdate() == ONE_UPDATED_ROW) {
                return true;
            }
            logger.error(EXCEPTION_UPDATE_ERROR_MESSAGE);
            throw new DAOException(EXCEPTION_UPDATE_ERROR_MESSAGE);
        } catch (SQLException e) {
            logger.error(EXCEPTION_UPDATE_NEW_USER_EXCEPTION + EXCEPTION_SQL_MESSAGE, e);
            throw new DAOException(EXCEPTION_UPDATE_NEW_USER_EXCEPTION + EXCEPTION_SQL_MESSAGE);
        } finally {
            connectionPool.returnConnection(connection);
        }
    }
}
