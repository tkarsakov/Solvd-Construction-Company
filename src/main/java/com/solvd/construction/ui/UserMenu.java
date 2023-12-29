package com.solvd.construction.ui;

import com.solvd.construction.service.ProjectService;
import com.solvd.construction.service.impl.ProjectServiceImpl;
import com.solvd.construction.ui.menuoptions.UserOptions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserMenu {
    private static final Logger LOGGER = LogManager.getLogger();
    private final ProjectService projectService = new ProjectServiceImpl();

    public static void showMenu(String implementation) {
        switch (implementation) {
            case "jdbc":
                break;
            case "mybatis":
                break;
        }

        while (true) {
            LOGGER.info(UserOptions.getOptions());
            switch (Input.userOptionConsoleInput()) {
                case EARNINGS:
                    break;
                case PROJECT:
                    break;
                case BACK:
                    break;
                case EXIT:
                    System.exit(0);
            }
        }
    }
}
