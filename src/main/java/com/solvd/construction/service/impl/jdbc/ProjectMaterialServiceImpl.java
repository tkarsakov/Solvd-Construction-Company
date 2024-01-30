package com.solvd.construction.service.impl.jdbc;

import com.solvd.construction.model.ProjectMaterial;
import com.solvd.construction.persistence.ProjectMaterialRepository;
import com.solvd.construction.persistence.impl.ProjectMaterialRepositoryImplDAO;
import com.solvd.construction.service.ProjectMaterialService;
import com.solvd.construction.service.SuppliedMaterialService;

import java.util.List;

public class ProjectMaterialServiceImpl implements ProjectMaterialService {
    private final ProjectMaterialRepository projectMaterialRepository;
    private final SuppliedMaterialService suppliedMaterialService;

    public ProjectMaterialServiceImpl() {
        this.projectMaterialRepository = new ProjectMaterialRepositoryImplDAO();
        this.suppliedMaterialService = new SuppliedMaterialServiceImpl();
    }

    @Override
    public ProjectMaterial create(ProjectMaterial projectMaterial) {
        projectMaterial.setId(null);
        projectMaterial.setSuppliedMaterial(suppliedMaterialService.retrieveById(projectMaterial.getSuppliedMaterialId()).orElse(null));
        projectMaterialRepository.create(projectMaterial);
        return projectMaterial;
    }

    @Override
    public List<ProjectMaterial> retrieveAllByProjectId(Long projectId) {
        List<ProjectMaterial> projectMaterials = projectMaterialRepository.findAllByProjectId(projectId);
        for (var projectMaterial : projectMaterials) {
            projectMaterial.setSuppliedMaterial(
                    suppliedMaterialService
                            .retrieveById(projectMaterial.getSuppliedMaterialId())
                            .orElse(null)
            );
        }
        return projectMaterials;
    }

    @Override
    public List<ProjectMaterial> retrieveAll() {
        List<ProjectMaterial> projectMaterials = projectMaterialRepository.findAll();
        for (var projectMaterial : projectMaterials) {
            projectMaterial.setSuppliedMaterial(
                    suppliedMaterialService
                            .retrieveById(projectMaterial.getSuppliedMaterialId())
                            .orElse(null)
            );
        }
        return projectMaterials;
    }

    @Override
    public void update(ProjectMaterial projectMaterial) {
        projectMaterialRepository.update(projectMaterial);
    }

    @Override
    public void delete(Long id) {
        projectMaterialRepository.deleteById(id);
    }
}
