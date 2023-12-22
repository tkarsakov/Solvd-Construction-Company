package com.solvd.construction.persistence.impl;

import com.solvd.construction.persistence.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public abstract class BaseRepositoryImpl {
    protected static final Logger LOGGER = LogManager.getLogger();
    protected final ConnectionPool CONNECTION_POOL = ConnectionPool.INSTANCE;


    protected <T> void baseCreate(T t, String sql, Object[] parameters, int[] types) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            connection.setAutoCommit(false);
            int i = 1;
            for (var param : parameters) {
                preparedStatement.setObject(i, param, types[i - 1]);
                i++;
            }
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            rollbackConnection(connection);
            LOGGER.fatal("Cannot create instance {}", t);
            LOGGER.fatal(e.getMessage());
            throw new RuntimeException("Create operation failed");
        } finally {
            setAutoCommitToFalse(connection);
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    protected void baseUpdate(String sql, Object[] params, int[] types) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            connection.setAutoCommit(false);
            int i = 1;
            for (var param : params) {
                preparedStatement.setObject(i, param, types[i - 1]);
                i++;
            }
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            rollbackConnection(connection);
            LOGGER.fatal(e.getMessage());
            throw new RuntimeException("Update query failed");
        } finally {
            setAutoCommitToFalse(connection);
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    protected ResultSet baseFindById(String sql, Long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
            return preparedStatement.getResultSet();
        } catch (SQLException e) {
            LOGGER.fatal(e.getMessage());
            throw new RuntimeException("Cannot execute findById query");
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    protected void baseDeleteById(String sql, Long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            connection.setAutoCommit(false);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                LOGGER.fatal(e1.getMessage());
                throw new RuntimeException("Rollback failed");
            }
            LOGGER.fatal(e.getMessage());
            throw new RuntimeException("Delete operation failed");
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e1) {
                LOGGER.fatal(e1.getMessage());
            }
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    private void rollbackConnection(Connection connection) {
        try {
            connection.rollback();
        } catch (SQLException e1) {
            LOGGER.fatal(e1.getMessage());
            throw new RuntimeException("Rollback canceled");
        }
    }

    private void setAutoCommitToFalse(Connection connection) {
        try {
            connection.setAutoCommit(true);
        } catch (SQLException e2) {
            LOGGER.fatal(e2.getMessage());
            throw new RuntimeException("Cannot setAutoCommit to false");
        }
    }
}
