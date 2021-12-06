package com.epam.jwd.dao;

import com.epam.jwd.dao.entity.Entity;
import com.epam.jwd.dao.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public interface BaseDao<K, T extends Entity> {

    Logger logger = LogManager.getLogger(BaseDao.class);

    T save(T entity) throws DAOException;

    boolean update(T entity) throws DAOException;

    List<T> findAll() throws DAOException;

    T findById(K id) throws DAOException;

    boolean deleteById(K id) throws DAOException;

    default void CloseResultSet(ResultSet resultSet) {
        logger.debug("CloseResultSet method");
        if (Objects.nonNull(resultSet)) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                logger.error(e);
            }
        }
    }

}
