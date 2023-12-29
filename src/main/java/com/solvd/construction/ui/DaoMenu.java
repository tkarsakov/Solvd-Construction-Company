package com.solvd.construction.ui;

import com.solvd.construction.ui.menuoptions.DaoOptions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DaoMenu {
    private static final Logger LOGGER = LogManager.getLogger();

    public static void showMenu() {
        String implementation;
        LOGGER.info(DaoOptions.getOptions());
        switch (Input.daoOptionConsoleInput()) {
            case JDBC:
                implementation = "jdbc";
                break;
            case MYBATIS:
                implementation = "mybatis";
                break;
            case EXIT:
                System.exit(0);
        }
    }
}
