package com.solvd.construction.persistence.impl;

import com.solvd.construction.model.Position;
import com.solvd.construction.persistence.PositionRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PositionRepositoryImplDAO extends ModelRepositoryImpl<Position> implements PositionRepository {
    private final String TABLE_NAME = "positions";
    private final String[] TABLE_COLUMNS = {"position_name", "months_salary"};
    private final int[] FIELD_TYPES = {Types.VARCHAR, Types.DECIMAL};

    @Override
    public void create(Position position) {
        super.create(position, TABLE_NAME, TABLE_COLUMNS, FIELD_TYPES);
    }

    @Override
    public Optional<Position> findById(Long id) {
        return super.findById(id, TABLE_NAME);
    }

    @Override
    public List<Position> findAll() {
        return super.findAll(TABLE_NAME);
    }

    @Override
    public void update(Position position) {
        super.update(position, TABLE_NAME, TABLE_COLUMNS, FIELD_TYPES);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id, TABLE_NAME);
    }

    @Override
    public Optional<Position> findByPositionName(String positionName) {
        return super.findByUniqueVarchar(positionName, TABLE_NAME, TABLE_COLUMNS[0]);
    }

    @Override
    public Object[] getModelParams(Position position) {
        return new Object[]{position.getPositionName(), position.getMonthsSalary()};
    }

    @Override
    public Optional<Position> getOptionalOfModel(ResultSet resultSet) throws SQLException {
        return Optional.of(
                new Position(
                        resultSet.getLong(1),
                        resultSet.getString(TABLE_COLUMNS[0]),
                        resultSet.getBigDecimal(TABLE_COLUMNS[1]))
        );
    }

    @Override
    public List<Position> getListOfModel(ResultSet resultSet) throws SQLException {
        List<Position> positions = new ArrayList<>();
        while (resultSet.next()) {
            positions.add(
                    new Position(
                            resultSet.getLong(1),
                            resultSet.getString(TABLE_COLUMNS[0]),
                            resultSet.getBigDecimal(TABLE_COLUMNS[1])
                    )
            );
        }
        return positions;
    }
}