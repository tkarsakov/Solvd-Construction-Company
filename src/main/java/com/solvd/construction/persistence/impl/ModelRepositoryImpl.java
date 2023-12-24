package com.solvd.construction.persistence.impl;

import com.solvd.construction.model.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public abstract class ModelRepositoryImpl<T extends Model> extends BaseRepositoryImpl {

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
        super.baseCreate(t, sql.toString(), params, FIELD_TYPES);
    }

    public Optional<T> findById(Long id, String TABLE_NAME) {
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE id = ?";
        try (ResultSet resultSet = super.baseFindById(sql, id)) {
            return getOptionalOfModel(resultSet);
        } catch (SQLException e) {
            LOGGER.fatal(e.getMessage());
            throw new RuntimeException("Cannot close ResultSet");
        }
    }

    public List<T> findAll(String TABLE_NAME) {
        Connection connection = CONNECTION_POOL.getConnection();
        String sql = "SELECT * FROM " + TABLE_NAME;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            return getListOfModel(resultSet);
        } catch (SQLException e) {
            LOGGER.fatal(e.getMessage());
            throw new RuntimeException("Cannot execute statement");
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
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
        super.baseUpdate(sql.toString(), params, FIELD_TYPES);
    }

    public void deleteById(Long id, String TABLE_NAME) {
        String sql = "DELETE FROM " + TABLE_NAME + " WHERE id = ?";
        super.baseDeleteById(sql, id);
    }

    public abstract Optional<T> getOptionalOfModel(ResultSet resultSet) throws SQLException;

    public abstract List<T> getListOfModel(ResultSet resultSet) throws SQLException;
}