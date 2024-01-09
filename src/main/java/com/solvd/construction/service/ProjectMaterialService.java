package com.solvd.construction.service;

import com.solvd.construction.model.ProjectMaterial;

import java.util.List;

public interface ProjectMaterialService extends IService<ProjectMaterial> {
    ProjectMaterial create(ProjectMaterial projectMaterial);

    List<ProjectMaterial> retrieveAllByProjectId(Long projectId);

    List<ProjectMaterial> retrieveAll();

    void update(ProjectMaterial projectMaterial);

    void delete(Long id);
}
