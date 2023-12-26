package com.solvd.construction.service.impl;

import com.solvd.construction.model.Project;
import com.solvd.construction.persistence.ProjectRepository;
import com.solvd.construction.persistence.impl.ProjectRepositoryImplDAO;
import com.solvd.construction.service.ClientService;
import com.solvd.construction.service.EmployeeService;
import com.solvd.construction.service.ProjectMaterialService;
import com.solvd.construction.service.ProjectService;

import java.util.List;

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
        return null;
    }

    @Override
    public List<Project> retrieveAll() {
        return null;
    }

    @Override
    public Project retrieveById(Long id) {
        return null;
    }
}
