package com.solvd.construction.persistence.impl;

import com.solvd.construction.model.Employee;
import com.solvd.construction.persistence.EmployeeRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeRepositoryImplDAO extends ModelRepositoryImpl<Employee> implements EmployeeRepository {
    private final int[] FIELD_TYPES = {Types.VARCHAR, Types.VARCHAR, Types.BIGINT};
    private final String TABLE_NAME = "employees";
    private final String MTM_TABLE = "work_assignments";
    private final String[] MTM_COLUMNS = {"worker_id", "project_id"};
    private final String[] TABLE_COLUMNS = {"first_name", "last_name", "position_id"};

    @Override
    public Object[] getModelParams(Employee employee) {
        return new Object[]{employee.getFirstName(), employee.getLastName(), employee.getPositionId()};
    }

    @Override
    public Optional<Employee> getOptionalOfModel(ResultSet resultSet) throws SQLException {
        return Optional.of(
                new Employee(
                        resultSet.getLong(1),
                        resultSet.getString(TABLE_COLUMNS[0]),
                        resultSet.getString(TABLE_COLUMNS[1]),
                        resultSet.getLong(TABLE_COLUMNS[2]))
        );
    }

    @Override
    public List<Employee> getListOfModel(ResultSet resultSet) throws SQLException {
        List<Employee> employeeList = new ArrayList<>();
        while (resultSet.next()) {
            employeeList.add(
                    new Employee(
                            resultSet.getLong(1),
                            resultSet.getString(TABLE_COLUMNS[0]),
                            resultSet.getString(TABLE_COLUMNS[1]),
                            resultSet.getLong(TABLE_COLUMNS[2]))
            );
        }
        return employeeList;
    }

    @Override
    public void create(Employee employee) {
        super.create(employee, TABLE_NAME, TABLE_COLUMNS, FIELD_TYPES);
    }

    @Override
    public Optional<Employee> findById(Long id) {
        return super.findById(id, TABLE_NAME);
    }

    @Override
    public List<Employee> findAll() {
        return super.findAll(TABLE_NAME);
    }

    @Override
    public void update(Employee employee) {
        super.update(employee, TABLE_NAME, TABLE_COLUMNS, FIELD_TYPES);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id, TABLE_NAME);
    }

    @Override
    public List<Employee> findAllByProjectId(Long projectId) {
        return super.findAllByIdInManyToManyTable(projectId, TABLE_NAME, MTM_TABLE, MTM_COLUMNS[1], MTM_COLUMNS[0]);
    }
}