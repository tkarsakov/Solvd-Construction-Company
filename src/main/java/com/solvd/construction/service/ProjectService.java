package com.solvd.construction.service;

import com.solvd.construction.model.Project;

import java.util.List;

public interface ProjectService {
    Project create(Project project);

    List<Project> retrieveAll();

    Project retrieveById(Long id);
}
