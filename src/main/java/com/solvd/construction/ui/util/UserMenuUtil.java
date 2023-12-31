package com.solvd.construction.ui.util;

import com.solvd.construction.model.Project;
import com.solvd.construction.service.ProjectService;
import com.solvd.construction.service.impl.ServiceFactory;
import com.solvd.construction.ui.Input;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class UserMenuUtil {
    private static final Logger LOGGER = LogManager.getLogger();

    public static void showCosts(ServiceFactory serviceFactory) {
        ProjectService projectService = serviceFactory.getProjectService();
        LOGGER.info("Enter project ID: ");
        Optional<Project> project = projectService.retrieveById(Input.longConsoleInput());
        if (project.isPresent()) {
            LOGGER.info(project.get().getCostsString());
        } else {
            LOGGER.info("Cannot find project");
        }
    }

    public static void showEarnings(ServiceFactory serviceFactory) {
        ProjectService projectService = serviceFactory.getProjectService();
        LOGGER.info("Enter project ID: ");
        Optional<Project> project = projectService.retrieveById(Input.longConsoleInput());
        if (project.isPresent()) {
            LOGGER.info(project.get().calculateEarnings().toString());
        } else {
            LOGGER.info("Cannot find project");
        }
    }
}
