package com.solvd.construction.service.impl;

import com.solvd.construction.model.Employee;
import com.solvd.construction.persistence.mappers.EmployeeMapper;
import com.solvd.construction.service.EmployeeService;
import com.solvd.construction.service.PositionService;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;
import java.util.function.Consumer;

public class MBEmployeeServiceImpl implements EmployeeService {
    private final SqlSessionFactory sessionFactory;
    private final PositionService positionService;

    public MBEmployeeServiceImpl(SqlSessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        this.positionService = new MBPositionServiceImpl(sessionFactory);
    }

    @Override
    public Employee create(Employee employee) {
        try (SqlSession session = sessionFactory.openSession()) {
            EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);
            employee.setId(null);
            employee.setPosition(positionService.retrieveById(employee.getPositionId()).orElse(null));
            employeeMapper.create(employee);
            session.commit();
            return employee;
        }
    }

    @Override
    public List<Employee> retrieveAll() {
        try (SqlSession session = sessionFactory.openSession()) {
            EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);
            List<Employee> employeeList = employeeMapper.retrieveAll();
            employeeList.forEach(setFields());
            return employeeList;
        }
    }

    @Override
    public List<Employee> retrieveAllByProjectId(Long id) {
        try (SqlSession session = sessionFactory.openSession()) {
            EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);
            List<Employee> employeeList = employeeMapper.retrieveAllByProjectId(id);
            employeeList.forEach(setFields());
            return employeeList;
        }
    }

    private Consumer<Employee> setFields() {
        return employee -> employee.setPosition(positionService.retrieveById(employee.getPositionId()).orElse(null));
    }
}
