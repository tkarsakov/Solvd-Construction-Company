package com.solvd.construction.ui;

import com.solvd.construction.model.Project;
import com.solvd.construction.service.ProjectService;
import com.solvd.construction.service.impl.MBProjectServiceImpl;
import com.solvd.construction.service.impl.ProjectServiceImpl;
import com.solvd.construction.ui.menuoptions.UserOptions;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

public class UserMenu {
    private static final Logger LOGGER = LogManager.getLogger();
    private static ProjectService projectService;
    private static SqlSessionFactory sessionFactory;

    public static void showMenu(String implementation) {
        switch (implementation) {
            case "jdbc":
                projectService = new ProjectServiceImpl();
                break;
            case "mybatis":
                String resource = "mybatis-config.xml";
                try (InputStream inputStream = Resources.getResourceAsStream(resource);) {
                    sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
                    projectService = new MBProjectServiceImpl(sessionFactory.openSession());
                } catch (IOException e) {
                    LOGGER.fatal("Cannot find mybatis config file");
                    System.exit(1);
                }
                break;
        }

        while (true) {
            LOGGER.info(UserOptions.getOptions());
            switch (Input.userOptionConsoleInput()) {
                case EARNINGS:
                    break;
                case PROJECT:
                    LOGGER.info("Enter project id: ");
                    Long projectId = Input.longConsoleInput();
                    Optional<Project> project = projectService.retrieveById(projectId);
                    if (project.isEmpty()) {
                        LOGGER.info("Project with specified ID does not exist");
                        break;
                    }
                    LOGGER.info(project.get().getCostsString());
                    break;
                case BACK:
                    ModeSelectMenu.showMenu(implementation);
                    break;
                case EXIT:
                    System.exit(0);
            }
        }

    }
}
