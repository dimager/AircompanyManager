package com.epam.jwd.dao.connectionpool;

import java.sql.Connection;

public interface ConnectionPool {

    /**
     * Initialize pool connection
     * @return true - if pool was initialized successfully, otherwise false
     */
    boolean init();

    /**
     * Close all pool connection
     */
    void shutdown();

    /**
     * Allows requesting connection from pool.
     * @return requested connection
     */
    Connection requestConnection();

    /**
     * Allows returning connection to pool
     * @param connection
     */
    void returnConnection(Connection connection);

}
