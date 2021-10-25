package com.epam.jwd.connectioonpool;

import java.sql.Connection;

public interface ConnectionPool {
    boolean init();
    void shutdown();
    Connection requestConnection();
    void returnConnection(Connection connection);

}
