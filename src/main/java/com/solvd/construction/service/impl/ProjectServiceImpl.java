package com.solvd.construction.service.impl;

import com.solvd.construction.model.Project;
import com.solvd.construction.persistence.ProjectRepository;
import com.solvd.construction.persistence.impl.ProjectRepositoryImplDAO;
import com.solvd.construction.service.ClientService;
import com.solvd.construction.service.EmployeeService;
import com.solvd.construction.service.ProjectMaterialService;
import com.solvd.construction.service.ProjectService;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;
    private final EmployeeService employeeService;
    private final ProjectMaterialService projectMaterialService;
    private final ClientService clientService;

    public ProjectServiceImpl() {
        this.projectRepository = new ProjectRepositoryImplDAO();
        this.employeeService = new EmployeeServiceImpl();
        this.projectMaterialService = new ProjectMaterialServiceImpl();
        this.clientService = new ClientServiceImpl();
    }

    @Override
    public Project create(Project project) {
        project.setId(null);
        projectRepository.create(project);
        return project;
    }

    @Override
    public List<Project> retrieveAll() {
        return projectRepository.findAll().stream().peek(setFields()).toList();
    }

    @Override
    public Optional<Project> retrieveById(Long id) {
        Optional<Project> optionalProject = projectRepository.findById(id);
        optionalProject.ifPresent(setFields());
        return optionalProject;
    }

    private Consumer<Project> setFields() {
        return project -> {
            project.setClient(
                    clientService.retrieveById(project.getClient_id()).orElse(null)
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
