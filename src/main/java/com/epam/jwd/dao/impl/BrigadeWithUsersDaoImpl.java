package com.epam.jwd.dao.impl;

import com.epam.jwd.connectioonpool.ConnectionPollImpl;
import com.epam.jwd.dao.BaseDao;
import com.epam.jwd.entity.Brigade;
import com.epam.jwd.entity.BrigadeWithUsers;
import com.epam.jwd.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BrigadeWithUsersDaoImpl implements BaseDao<Long, BrigadeWithUsers> {

    private static final String  SELECT_BRIGADES_USER = "select user_id, role_id, username, password, first_name, last_name, salt FROM users INNER JOIN brigades_has_users on brigades_has_users.users_user_id = users.user_id  where brigades_has_users.brigades_brigade_id = ?";

    @Override
    public boolean save(BrigadeWithUsers entity) {
        BrigadeDaoImpl brigadeDao = new BrigadeDaoImpl();
        brigadeDao.save(entity);


        UserDaoImpl userDao = new UserDaoImpl();
        entity.getBrigadeUsers().forEach(user -> new UserDaoImpl().save(user));
        return false;
    }

    @Override
    public List<BrigadeWithUsers> findAll() {
        return null;
    }

    @Override
    public BrigadeWithUsers findById(Long id) {
        return null;
    }

    public BrigadeWithUsers findById(Brigade brigade) {
        BrigadeWithUsers brigadeWithUsers =  new BrigadeWithUsers();
        brigadeWithUsers.setId(brigade.getId());
        brigadeWithUsers.setBrigadeName(brigade.getBrigadeName());
        Connection connection = ConnectionPollImpl.getInstance().requestConnection();
        try {
            User user = new User();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BRIGADES_USER);
            preparedStatement.setLong(1, brigadeWithUsers.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                user.setId(resultSet.getLong(1));
                user.setRoleId(resultSet.getByte(2));
                user.setUsername(resultSet.getString(3));
                user.setPassword(resultSet.getString(4));
                user.setFirstName(resultSet.getString(5));
                user.setLastName(resultSet.getString(6));
                user.setSalt(resultSet.getString(7));
                brigadeWithUsers.getBrigadeUsers().add(user);
            }
            return brigadeWithUsers;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        } finally {
            ConnectionPollImpl.getInstance().returnConnection(connection);
        }
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }
}
