package com.solvd.construction.service.impl;

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
                            .get()
            );
        }
        return projectMaterials;
    }
}
