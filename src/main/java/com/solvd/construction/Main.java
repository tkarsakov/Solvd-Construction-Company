package com.solvd.construction;


import com.solvd.construction.model.Project;
import com.solvd.construction.service.ProjectService;
import com.solvd.construction.service.impl.ProjectServiceImpl;

public class Main {
    public static void main(String[] args) {
        ProjectService projectService = new ProjectServiceImpl();
        Project project = projectService.retrieveById(2L).get();
        System.out.println(project.getCostsString());
    }
}
