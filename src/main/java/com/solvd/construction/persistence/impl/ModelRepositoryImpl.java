package com.solvd.construction.persistence.impl;

import com.solvd.construction.model.Model;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public abstract class ModelRepositoryImpl<T extends Model> {
    public static final Logger LOGGER = LogManager.getLogger();
    private final BaseAtomicOperations baseAtomicOperations = new BaseAtomicOperations();

    public abstract Object[] getModelParams(T t);

    public void create(T t, String TABLE_NAME, String[] TABLE_COLUMNS, int[] FIELD_TYPES) {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO ").append(TABLE_NAME).append(" (");
        for (int i = 0; i < TABLE_COLUMNS.length - 1; i++) {
            sql.append(TABLE_COLUMNS[i]).append(", ");
        }
        sql.append(TABLE_COLUMNS[TABLE_COLUMNS.length - 1]).append(") ")
                .append("VALUES (");
        sql.append("?, ".repeat(TABLE_COLUMNS.length - 1)).append("?);");
        Object[] params = getModelParams(t);
        baseAtomicOperations.baseCreate(t, sql.toString(), params, FIELD_TYPES);
    }

    public Optional<T> findById(Long id, String TABLE_NAME) {
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE id = ?";
        try (ResultSet resultSet = baseAtomicOperations.baseSelectSingleResultById(sql, id)) {
            if (resultSet.next()) {
                return getOptionalOfModel(resultSet);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            LOGGER.fatal(e.getMessage());
            throw new RuntimeException("Cannot close ResultSet");
        }
    }

    public List<T> findAll(String TABLE_NAME) {
        Connection connection = baseAtomicOperations.CONNECTION_POOL.getConnection();
        String sql = "SELECT * FROM " + TABLE_NAME;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            return getListOfModel(resultSet);
        } catch (SQLException e) {
            LOGGER.fatal(e.getMessage());
            throw new RuntimeException("Cannot execute statement");
        } finally {
            baseAtomicOperations.CONNECTION_POOL.releaseConnection(connection);
        }
    }

    public void update(T t, String TABLE_NAME, String[] TABLE_COLUMNS, int[] FIELD_TYPES) {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE ")
                .append(TABLE_NAME)
                .append(" SET ");
        for (int i = 0; i < TABLE_COLUMNS.length - 1; i++) {
            sql.append(TABLE_COLUMNS[i]).append(" = ?, ");
        }
        sql.append(TABLE_COLUMNS[TABLE_COLUMNS.length - 1]).append(" = ?")
                .append(" WHERE ")
                .append("id = ")
                .append(t.getId());
        Object[] params = getModelParams(t);
        baseAtomicOperations.baseUpdate(sql.toString(), params, FIELD_TYPES);
    }

    public void deleteById(Long id, String TABLE_NAME) {
        String sql = "DELETE FROM " + TABLE_NAME + " WHERE id = ?";
        baseAtomicOperations.baseDeleteById(sql, id);
    }

    public Long getTimediffDaysBetweenStartDateAndFinishDate(T t, String TABLE_NAME) {
        String sql = "SELECT " +
                "TIMESTAMPDIFF(DAY, start_date, finish_date) " +
                "FROM " +
                TABLE_NAME +
                " WHERE id = ?";
        try (ResultSet resultSet = baseAtomicOperations.baseSelectSingleResultById(sql, t.getId())) {
            if (resultSet.next()) {
                return resultSet.getLong(1);
            } else {
                throw new RuntimeException("Cannot determine deadline for project. Check start_date and finish_date " +
                        "values");
            }
        } catch (SQLException e) {
            LOGGER.fatal(e.getMessage());
            throw new RuntimeException("Failed to close ResultSet");
        }
    }

    public Optional<T> findByUniqueVarchar(String query, String TABLE_NAME, String TABLE_COLUMN) {
        String sql = "SELECT " +
                "* " + "FROM " +
                TABLE_NAME + " WHERE" +
                TABLE_COLUMN + " = ?;";
        try (ResultSet resultSet = baseAtomicOperations.baseSelectSingleResultByUniqueVarchar(sql, query)) {
            if (resultSet.next()) {
                return getOptionalOfModel(resultSet);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            LOGGER.fatal(e.getMessage());
            throw new RuntimeException("Failed to close ResultSet");
        }
    }

    public List<T> findAllByBigint(Long bigint, String TABLE_NAME, String TABLE_COLUMN) {
        String sql = "SELECT * " +
                "FROM " + TABLE_NAME +
                " WHERE " + TABLE_COLUMN +
                " = ?;";
        Connection connection = baseAtomicOperations.CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, bigint);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            return getListOfModel(resultSet);
        } catch (SQLException e) {
            LOGGER.fatal(e.getMessage());
            throw new RuntimeException("Failed while trying to SELECT by Long/Bigint");
        } finally {
            baseAtomicOperations.CONNECTION_POOL.releaseConnection(connection);
        }
    }

    public abstract Optional<T> getOptionalOfModel(ResultSet resultSet) throws SQLException;

    public abstract List<T> getListOfModel(ResultSet resultSet) throws SQLException;
}