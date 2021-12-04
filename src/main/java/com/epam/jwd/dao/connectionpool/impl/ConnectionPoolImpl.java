package com.epam.jwd.dao.connectionpool.impl;

import com.epam.jwd.dao.connectionpool.ConnectionPool;
import com.epam.jwd.dao.connectionpool.DataSourceFactory;
import com.epam.jwd.dao.connectionpool.ProxyConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;

public final class ConnectionPoolImpl implements ConnectionPool {

    private static final ConnectionPool INSTANCE = new ConnectionPoolImpl();
    private static final int CONNECTION_POOL_SIZE = 10;

    private final Queue<ProxyConnection> availableConnection = new ArrayBlockingQueue<>(CONNECTION_POOL_SIZE);
    private final List<ProxyConnection> usedConnection = new CopyOnWriteArrayList<>();
    private static boolean initialized = false;

    private ConnectionPoolImpl() {
    }

    public static ConnectionPool getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean init() {
        if (!initialized) {
            initialized = fillConnectionPool(CONNECTION_POOL_SIZE);
        }
        return initialized;
    }


    @Override
    public void shutdown() {
        closeConnections(availableConnection);
        closeConnections(usedConnection);
    }

    @Override
    public synchronized Connection requestConnection() {
        if (!initialized){
            init();
        }
        while (availableConnection.isEmpty()) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        ProxyConnection connection = availableConnection.poll();
        usedConnection.add(connection);
        return connection;
    }

    @Override
    public synchronized void returnConnection(Connection connection) {
        if (usedConnection.remove(connection)) {
            availableConnection.add((ProxyConnection) connection);
            this.notifyAll();
        }
    }

    private boolean fillConnectionPool(int amount) {
        for (int i = 0; i < amount; i++) {
            try {
                Connection connection = DataSourceFactory.getMysqlProperties().getConnection();
                final ProxyConnection proxyConnection = new ProxyConnection(this, connection);
                availableConnection.add(proxyConnection);
            } catch (SQLException throwables) {
                //log
                return false;
            } catch (IllegalStateException | ClassCastException | NullPointerException | IllegalArgumentException e) {
                // log
                return false;
            }
        }
        return true;
    }

    private void closeConnections(Collection<ProxyConnection> connections) {
        connections.forEach(connection -> closeConnection(connection));
    }

    private void closeConnection(ProxyConnection connection) {
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
