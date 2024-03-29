package com.solvd.construction.service.impl.jdbc;

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
        if (project.getClientId() == null) {
            clientService.create(project.getClient());
            project.setClientId(project.getClient().getId());
        } else {
            project.setClient(clientService.retrieveById(project.getClientId()).orElse(null));
        }
        projectRepository.create(project);
        for (var employee : project.getEmployeeList()) {
            if (employee.getId() == null) {
                employeeService.create(employee);
            }
        }
        project.getProjectMaterials().forEach(projectMaterial -> {
            if (projectMaterial.getId() == null) {
                projectMaterial.setProjectId(project.getId());
                projectMaterialService.create(projectMaterial);
            }
        });
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

    @Override
    public void update(Project project) {
        projectRepository.update(project);
    }

    @Override
    public void delete(Long id) {
        projectRepository.deleteById(id);
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
            projectRepository.setDeadline(project);
        };
    }
}
