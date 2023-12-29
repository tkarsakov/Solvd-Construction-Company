package com.solvd.construction.ui;

import com.solvd.construction.ui.menuoptions.ModeSelectOptions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ModeSelectMenu {
    private static final Logger LOGGER = LogManager.getLogger();

    public static void showMenu(String implementation) {
        LOGGER.info(ModeSelectOptions.getOptions());
        switch (Input.modeSelectOptionConsoleInput()) {
            case ADMIN:
                break;
            case USER:
                UserMenu.showMenu(implementation);
                break;
            case BACK:
                DaoMenu.showMenu();
                break;
            case EXIT:
                System.exit(0);
        }
    }
}
