package com.solvd.construction.persistence.impl;

import com.solvd.construction.model.Project;
import com.solvd.construction.persistence.ProjectRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProjectRepositoryImplDAO extends ModelRepositoryImpl<Project> implements ProjectRepository {
    private final String TABLE_NAME = "projects";
    private final String[] TABLE_COLUMNS = {
            "start_date", "finish_date", "client_id", "floors", "interior_work", "budget"
    };
    private final int[] FIELD_TYPES = {
            Types.TIMESTAMP, Types.TIMESTAMP, Types.BIGINT, Types.BIGINT, Types.BOOLEAN, Types.DECIMAL
    };

    @Override
    public void create(Project project) {
        super.create(project, TABLE_NAME, TABLE_COLUMNS, FIELD_TYPES);
    }

    @Override
    public Optional<Project> findById(Long id) {
        return super.findById(id, TABLE_NAME);
    }

    @Override
    public List<Project> findAll() {
        return super.findAll(TABLE_NAME);
    }

    @Override
    public void update(Project project) {
        super.update(project, TABLE_NAME, TABLE_COLUMNS, FIELD_TYPES);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id, TABLE_NAME);
    }

    @Override
    public Object[] getModelParams(Project project) {
        return new Object[]{project.getStartDate(), project.getFinishDate(), project.getFloors()};
    }

    @Override
    public Optional<Project> getOptionalOfModel(ResultSet resultSet) throws SQLException {
        return Optional.of(
                new Project(
                        resultSet.getLong(1),
                        resultSet.getTimestamp(TABLE_COLUMNS[0]),
                        resultSet.getTimestamp(TABLE_COLUMNS[1]),
                        resultSet.getLong(TABLE_COLUMNS[2]),
                        resultSet.getLong(TABLE_COLUMNS[3]),
                        resultSet.getBoolean(TABLE_COLUMNS[4]),
                        resultSet.getBigDecimal(TABLE_COLUMNS[5])
                )
        );
    }

    @Override
    public List<Project> getListOfModel(ResultSet resultSet) throws SQLException {
        List<Project> projects = new ArrayList<>();
        while (resultSet.next()) {
            new Project(
                    resultSet.getLong(1),
                    resultSet.getTimestamp(TABLE_COLUMNS[0]),
                    resultSet.getTimestamp(TABLE_COLUMNS[1]),
                    resultSet.getLong(TABLE_COLUMNS[2]),
                    resultSet.getLong(TABLE_COLUMNS[3]),
                    resultSet.getBoolean(TABLE_COLUMNS[4]),
                    resultSet.getBigDecimal(TABLE_COLUMNS[5])
            );
        }
        return projects;
    }
}