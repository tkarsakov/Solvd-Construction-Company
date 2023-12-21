package com.solvd.construction.persistence;

import io.github.cdimascio.dotenv.Dotenv;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ConcurrentLinkedDeque;

public enum ConnectionPool {
    INSTANCE;
    private static class ConnectionPoolService {
        private static final Logger LOGGER = LogManager.getLogger();
        private static final int POOL_SIZE = 5;
        private final ConcurrentLinkedDeque<Connection> connectionsDeque;
        private ConnectionPoolService() {
            connectionsDeque = new ConcurrentLinkedDeque<>();
            Dotenv dotenv = Dotenv.configure()
                    .load();
            String jdbcUrl = dotenv.get("JDBC_URL");
            String username = dotenv.get("username");
            String password = dotenv.get("password");
            for (int i = 0; i < POOL_SIZE; i++) {
                try {
                    connectionsDeque.add(DriverManager.getConnection(jdbcUrl, username, password));
                } catch (SQLException e) {
                    LOGGER.fatal("Cannot connect to MySQL server");
                    for (var trace : e.getStackTrace()) {
                        LOGGER.fatal(trace.toString());
                    }
                }
            }
        }
        public synchronized Connection getConnection() throws InterruptedException {
            if (connectionsDeque.peekLast() == null) {
                wait();
            }
            return connectionsDeque.pop();
        }
        public synchronized void releaseConnection(Connection connection){
            connectionsDeque.add(connection);
            notify();
        }
    }
    private final ConnectionPoolService connectionPoolService = new ConnectionPoolService();

    public Connection getConnection() throws InterruptedException{
        return connectionPoolService.getConnection();
    }
    public void releaseConnection(Connection connection) {
        connectionPoolService.releaseConnection(connection);
    }
}
