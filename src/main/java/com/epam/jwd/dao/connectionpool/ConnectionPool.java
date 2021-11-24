package com.epam.jwd.dao.connectionpool;

import java.sql.Connection;

public interface ConnectionPool {
    boolean init();
    void shutdown();
    Connection requestConnection();
    void returnConnection(Connection connection);

}
