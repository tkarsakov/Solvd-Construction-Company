package com.solvd.construction.service.impl;

import com.solvd.construction.model.Project;
import com.solvd.construction.persistence.mappers.ProjectMapper;
import com.solvd.construction.service.ClientService;
import com.solvd.construction.service.EmployeeService;
import com.solvd.construction.service.ProjectMaterialService;
import com.solvd.construction.service.ProjectService;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class MBProjectServiceImpl implements ProjectService {
    private final SqlSessionFactory sessionFactory;
    private final EmployeeService employeeService;
    private final ProjectMaterialService projectMaterialService;
    private final ClientService clientService;

    public MBProjectServiceImpl(SqlSessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        this.employeeService = new MBEmployeeServiceImpl(sessionFactory);
        this.clientService = new MBClientServiceImpl(sessionFactory);
        this.projectMaterialService = new MBProjectMaterialServiceImpl(sessionFactory);
    }

    @Override
    public Project create(Project project) {
        try (SqlSession session = sessionFactory.openSession()) {
            ProjectMapper projectMapper = session.getMapper(ProjectMapper.class);
            project.setId(null);
            for (var employee : project.getEmployeeList()) {
                if (employee.getId() == null) {
                    employeeService.create(employee);
                }
            }
            project.getProjectMaterials().forEach(projectMaterial -> {
                if (projectMaterial.getId() == null) {
                    projectMaterialService.create(projectMaterial);
                }
            });
            if (project.getClient().getId() == null) {
                clientService.create(project.getClient());
            }
            projectMapper.create(project);
            return project;
        }
    }

    @Override
    public List<Project> retrieveAll() {
        try (SqlSession session = sessionFactory.openSession()) {
            ProjectMapper projectMapper = session.getMapper(ProjectMapper.class);
            return projectMapper.retrieveAll().stream().peek(setFields()).toList();
        }
    }

    @Override
    public Optional<Project> retrieveById(Long id) {
        try (SqlSession session = sessionFactory.openSession()) {
            ProjectMapper projectMapper = session.getMapper(ProjectMapper.class);
            Optional<Project> optionalProject = Optional.of(projectMapper.retrieveById(id));
            optionalProject.ifPresent(setFields());
            return optionalProject;
        }
    }

    @Override
    public void update(Project project) {
        try (SqlSession session = sessionFactory.openSession()) {
            ProjectMapper projectMapper = session.getMapper(ProjectMapper.class);
            projectMapper.update(project);
        }
    }

    @Override
    public void delete(Long id) {
        try (SqlSession session = sessionFactory.openSession()) {
            ProjectMapper projectMapper = session.getMapper(ProjectMapper.class);
            projectMapper.delete(id);
        }
    }

    private Consumer<Project> setFields() {
        return project -> {
            project.setClient(
                    clientService.retrieveById(project.getClientId()).orElse(null)
            );
            project.setProjectMaterials(
                    projectMaterialService.retrieveAllByProjectId(project.getId())
            );
            project.setEmployeeList(
                    employeeService.retrieveAllByProjectId(project.getId())
            );
        };
    }
}
