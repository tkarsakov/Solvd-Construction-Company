package com.solvd.construction.persistence;

import com.solvd.construction.model.ProjectMaterial;

import java.util.List;
import java.util.Optional;

public interface ProjectMaterialRepository {
    void create(ProjectMaterial projectMaterial);

    Optional<ProjectMaterial> findById(Long id);

    List<ProjectMaterial> findAll();

    void update(ProjectMaterial projectMaterial);

    void deleteById(Long id);
}
