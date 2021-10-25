package com.epam.jwd.dao.impl;

import com.epam.jwd.connectioonpool.ConnectionPollImpl;
import com.epam.jwd.dao.BaseDao;
import com.epam.jwd.entity.BrigadeHasUser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BrigadeHasUserDaoImpl implements BaseDao<Long, BrigadeHasUser> {
    private static final String SELECT_BRIGADE_HAS_USER_BY_BRIGADEID_SQL = "SELECT brigades_brigade_id, users_user_id FROM aircompany_manager_db.brigades_has_users WHERE brigades_brigade_id = ?";
    private static final String SELECT_ALL_BRIGADE_HAS_USER_SQL = "SELECT brigades_brigade_id, users_user_id FROM aircompany_manager_db.brigades_has_users";
    private static final String INSERT_BRIGADEUSER_SQL = "INSERT INTO aircompany_manager_db.brigades_has_users (brigades_brigade_id,users_user_id) VALUES (?,?)";
    private static final String DELETE_BRIGADE_HAS_USER_BY_USERID_SQL = "DELETE FROM aircompany_manager_db.brigades_has_users WHERE users_user_id = ?";

    @Override
    public boolean save(BrigadeHasUser entity) {
        Connection connection = ConnectionPollImpl.getInstance().requestConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_BRIGADEUSER_SQL);
            preparedStatement.setLong(1, entity.getBrigadeId());
            preparedStatement.setLong(2, entity.getUserId());
            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        } finally {
            ConnectionPollImpl.getInstance().returnConnection(connection);
        }
    }

    @Override
    public List<BrigadeHasUser> findAll() {
        List<BrigadeHasUser> brigadeHasUsers = new ArrayList<>();
        Connection connection = ConnectionPollImpl.getInstance().requestConnection();
        try (Statement statement = connection.createStatement();) {
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_BRIGADE_HAS_USER_SQL);
            while (resultSet.next()) {
                BrigadeHasUser brigadeHasUser = new BrigadeHasUser();
                brigadeHasUser.setBrigadeId(resultSet.getLong(1));
                brigadeHasUser.setUserId(resultSet.getLong(2));
                brigadeHasUsers.add(brigadeHasUser);
            }
            return brigadeHasUsers;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        } finally {
            ConnectionPollImpl.getInstance().returnConnection(connection);
        }
    }

    @Override
    public BrigadeHasUser findById(Long id) {
        BrigadeHasUser brigadeHasUser = new BrigadeHasUser();
        Connection connection = ConnectionPollImpl.getInstance().requestConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BRIGADE_HAS_USER_BY_BRIGADEID_SQL);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                brigadeHasUser.setBrigadeId(resultSet.getLong(1));
                brigadeHasUser.setUserId(resultSet.getLong(2));
            }
            return brigadeHasUser;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        } finally {
            ConnectionPollImpl.getInstance().returnConnection(connection);
        }
    }

    @Override
    public boolean deleteById(Long id) {
        Connection connection = ConnectionPollImpl.getInstance().requestConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BRIGADE_HAS_USER_BY_USERID_SQL);
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        } finally {
            ConnectionPollImpl.getInstance().returnConnection(connection);
        }
    }
}
