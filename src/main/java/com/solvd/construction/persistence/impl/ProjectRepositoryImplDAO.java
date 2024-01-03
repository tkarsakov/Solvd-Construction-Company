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
            "finish_date", "client_id", "start_date", "floors", "budget", "interior_work"
    };
    private final int[] FIELD_TYPES = {
            Types.TIMESTAMP, Types.BIGINT, Types.TIMESTAMP, Types.BIGINT, Types.DECIMAL, Types.BOOLEAN
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
    public void setDeadline(Project project) {
        project.setDeadline(super.getTimediffDaysBetweenStartDateAndFinishDate(project, TABLE_NAME));
    }

    @Override
    public Object[] getModelParams(Project project) {
        return new Object[]{project.getStartDate(), project.getClientId(), project.getFinishDate(),
                project.getFloors(), project.getBudget(), project.isInteriorWork()};
    }

    @Override
    public Optional<Project> getOptionalOfModel(ResultSet resultSet) throws SQLException {
        return Optional.of(
                new Project(
                        resultSet.getLong(1),
                        resultSet.getTimestamp(TABLE_COLUMNS[0]),
                        resultSet.getLong(TABLE_COLUMNS[1]),
                        resultSet.getTimestamp(TABLE_COLUMNS[2]),
                        resultSet.getLong(TABLE_COLUMNS[3]),
                        resultSet.getBigDecimal(TABLE_COLUMNS[4]),
                        resultSet.getBoolean(TABLE_COLUMNS[5]),
                        null
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
                    resultSet.getLong(TABLE_COLUMNS[1]),
                    resultSet.getTimestamp(TABLE_COLUMNS[2]),
                    resultSet.getLong(TABLE_COLUMNS[3]),
                    resultSet.getBigDecimal(TABLE_COLUMNS[4]),
                    resultSet.getBoolean(TABLE_COLUMNS[5]),
                    null
            );
        }
        return projects;
    }
}