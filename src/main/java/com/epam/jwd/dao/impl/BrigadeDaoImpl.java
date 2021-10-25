package com.epam.jwd.dao.impl;

import com.epam.jwd.connectioonpool.ConnectionPollImpl;
import com.epam.jwd.dao.BaseDao;
import com.epam.jwd.entity.Aircraft;
import com.epam.jwd.entity.Brigade;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BrigadeDaoImpl implements BaseDao<Long, Brigade> {
    private static final String SELECT_BRIGADE_BY_ID_SQL = "SELECT brigades.brigade_id, brigades.brigade_name FROM aircompany_manager_db.brigades WHERE brigade_id = ?";
    private static final String SELECT_ALL_BRIGADES_SQL = "SELECT brigade_id, brigade_name FROM aircompany_manager_db.brigades;";
    private static final String INSERT_BRIGADE_SQL = "INSERT INTO aircompany_manager_db.brigades (brigade_name) VALUES (?)";
    private static final String DELETE_BRIGADE_BY_ID_SQL = "DELETE FROM aircompany_manager_db.brigades WHERE brigade_id = ?";

    @Override
    public boolean save(Brigade entity) {
        Connection connection = ConnectionPollImpl.getInstance().requestConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_BRIGADE_SQL);
            preparedStatement.setString(1, entity.getBrigadeName());
            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        } finally {
            ConnectionPollImpl.getInstance().returnConnection(connection);
        }
    }

    @Override
    public List<Brigade> findAll() {
        List<Brigade> brigades = new ArrayList<>();
        Connection connection = ConnectionPollImpl.getInstance().requestConnection();
        try (Statement statement = connection.createStatement();) {
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_BRIGADES_SQL);
            while (resultSet.next()) {
                Brigade brigade = new Brigade();
                brigade.setId(resultSet.getLong(1));
                brigade.setBrigadeName(resultSet.getString(2));
                brigades.add(brigade);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            ConnectionPollImpl.getInstance().returnConnection(connection);
        }
        return brigades;
    }

    @Override
    public Brigade findById(Long id) {
        Brigade brigade = new Brigade();
        Connection connection = ConnectionPollImpl.getInstance().requestConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BRIGADE_BY_ID_SQL);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                brigade.setId(resultSet.getLong(1));
                brigade.setBrigadeName(resultSet.getString(2));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            ConnectionPollImpl.getInstance().returnConnection(connection);
        }
        return brigade;
    }

    @Override
    public boolean deleteById(Long id) {
        Connection connection = ConnectionPollImpl.getInstance().requestConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BRIGADE_BY_ID_SQL);
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate()==1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            ConnectionPollImpl.getInstance().returnConnection(connection);
        }
        return false;
    }


}
