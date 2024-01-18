package com.solvd.construction.ui;

import com.solvd.construction.service.factory.ServiceFactory;
import com.solvd.construction.ui.menuoptions.ModeSelectOptions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ModeSelectMenu {
    private static final Logger LOGGER = LogManager.getLogger();

    public static void showMenu(ServiceFactory serviceFactory) {
        LOGGER.info(ModeSelectOptions.getOptions());
        switch (Input.enumInput(ModeSelectOptions.class)) {
            case ADMIN:
                AdminMenu.showMenu(serviceFactory);
                break;
            case USER:
                UserMenu.showMenu(serviceFactory);
                break;
            case BACK:
                DaoMenu.showMenu();
                break;
            case EXIT:
                System.exit(0);
        }
    }
}
