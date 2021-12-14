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

    /**
     *Allows saving  entity to DB.
     * @param  entity entity which should be saved in DB.
     * @return entity with generated id.
     * @throws DAOException throws if entity wasn't saved in DB.
     */
    T save(T entity) throws DAOException;

    /**
     * Allows updating data of <T> entity in DB.
     * @param  entity which should be updated in DB.
     * @return true if data was updated, otherwise exception
     * @throws DAOException throws if entity wasn't updated or SQL exception
     */
    boolean update(T entity) throws DAOException;


    /**
     * Allows finding all entity in DB
     * @return List of all entities.
     * @throws DAOException throws if SQL exception
     */
    List<T> findAll() throws DAOException;


    /**
     * Allows finding entity by id.
     * @param id entity id.
     * @return entity if its was found other, otherwise exception
     * @throws DAOException throws if entity wasn't found or SQL exception
     */
    T findById(K id) throws DAOException;

    /**
     * Allows deleting entity from DB by id.
     * @param id entity id.
     * @return true if entity was deleted from DB, otherwise exception
     * @throws DAOException
     */
    boolean deleteById(K id) throws DAOException;

    /**
     * Allows closing result set.
     * @param resultSet result set which should be closed.
     */
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
