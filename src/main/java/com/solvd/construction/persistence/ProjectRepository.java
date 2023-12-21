package com.solvd.construction.persistence;

import com.solvd.construction.model.Project;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository {
    void create(Project project);

    Optional<Project> findById(Long id);

    List<Project> findAll();

    void update(Project project);

    void deleteById(Project project);
}
