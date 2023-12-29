package com.solvd.construction.service.impl;

import com.solvd.construction.model.ProjectMaterial;
import com.solvd.construction.persistence.mappers.ProjectMaterialMapper;
import com.solvd.construction.service.ProjectMaterialService;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class MBProjectMaterialServiceImpl implements ProjectMaterialService {
    private final ProjectMaterialMapper projectMaterialMapper;
    private final MBSuppliedMaterialServiceImpl suppliedMaterialService;

    public MBProjectMaterialServiceImpl(SqlSession session) {
        this.projectMaterialMapper = session.getMapper(ProjectMaterialMapper.class);
        this.suppliedMaterialService = new MBSuppliedMaterialServiceImpl(session);
    }

    @Override
    public ProjectMaterial create(ProjectMaterial projectMaterial) {
        projectMaterial.setId(null);
        if (suppliedMaterialService.retrieveById(projectMaterial.getSuppliedMaterial().getId()).isEmpty()) {
            suppliedMaterialService.create(projectMaterial.getSuppliedMaterial());
        }
        projectMaterialMapper.create(projectMaterial);
        return projectMaterial;
    }

    @Override
    public List<ProjectMaterial> retrieveAllByProjectId(Long projectId) {
        List<ProjectMaterial> projectMaterials = projectMaterialMapper.retrieveAllByProjectId(projectId);
        for (var projectMaterial : projectMaterials) {
            projectMaterial.setSuppliedMaterial(
                    suppliedMaterialService
                            .retrieveById(projectMaterial.getSuppliedMaterialId())
                            .orElse(null)
            );
        }
        return projectMaterials;
    }
}
