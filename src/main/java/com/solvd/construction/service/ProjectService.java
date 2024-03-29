package com.solvd.construction.service;

import com.solvd.construction.model.Project;

import java.util.List;
import java.util.Optional;

public interface ProjectService extends IService<Project> {
    Project create(Project project);

    List<Project> retrieveAll();

    Optional<Project> retrieveById(Long id);

    void update(Project project);

    void delete(Long id);
}
