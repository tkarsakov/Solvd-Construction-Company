package com.solvd.construction.service;

import com.solvd.construction.model.ProjectMaterial;

import java.util.List;

public interface ProjectMaterialService {
    ProjectMaterial create(ProjectMaterial projectMaterial);

    List<ProjectMaterial> retrieveAllByProjectId(Long projectId);
}
