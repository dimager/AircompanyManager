package repository.impl;

import repository.Repository;
import repository.connectioonpool.ConnectionPollImpl;
import repository.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements Repository<Long, User> {
    private static final String SELECT_USER_BY_ID_SQL = "SELECT user_id,  role_id, username, password, first_name, last_name, salt FROM aircompany_manager_db.users WHERE user_id=?";
    private static final String SELECT_ALL_USERS_SQL = "SELECT user_id,  role_id, username, password, first_name, last_name, salt FROM aircompany_manager_db.users";
    private static final String INSERT_NEW_USER_SQL = "INSERT INTO aircompany_manager_db.users (role_id,username,password,first_name,last_name,salt) VALUES (?,?,?,?,?,?,?);";
    private static final String DELETE_USER_SQL = "DELETE FROM aircompany_manager_db.users WHERE id=?";

    @Override
    public boolean save(User entity) {
        Connection connection = ConnectionPollImpl.getInstance().requestConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_NEW_USER_SQL)) {
            preparedStatement.setByte(1,entity.getRoleId());
            preparedStatement.setString(2,entity.getUsername());
            preparedStatement.setString(3, entity.getPassword());
            preparedStatement.setString(4,entity.getFirstName());
            preparedStatement.setString(5,entity.getLastName());
            preparedStatement.setString(6,entity.getSalt());
            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        } finally {
            ConnectionPollImpl.getInstance().returnConnection(connection);
        }
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        Connection connection = ConnectionPollImpl.getInstance().requestConnection();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_USERS_SQL);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong(1));
                user.setRoleId(resultSet.getByte(2));
                user.setUsername(resultSet.getString(3));
                user.setPassword(resultSet.getString(4));
                user.setFirstName(resultSet.getString(5));
                user.setLastName(resultSet.getString(6));
                user.setSalt(resultSet.getString(7));
                users.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            ConnectionPollImpl.getInstance().returnConnection(connection);
            return users;
        }
    }

    @Override
    public User findById(Long id) {
        User user = new User();
        Connection connection = ConnectionPollImpl.getInstance().requestConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user.setId(resultSet.getLong(1));
                user.setRoleId(resultSet.getByte(2));
                user.setUsername(resultSet.getString(3));
                user.setPassword(resultSet.getString(4));
                user.setFirstName(resultSet.getString(5));
                user.setLastName(resultSet.getString(6));
                user.setSalt(resultSet.getString(7));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            ConnectionPollImpl.getInstance().returnConnection(connection);
            return user;
        }
    }

    @Override
    public boolean deleteById(Long id) {
        Connection connection = ConnectionPollImpl.getInstance().requestConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER_SQL)) {
            preparedStatement.setLong(1,id);
            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        } finally {
            ConnectionPollImpl.getInstance().returnConnection(connection);
        }
    }
}
