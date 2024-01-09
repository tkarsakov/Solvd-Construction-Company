package com.solvd.construction.ui;

import com.solvd.construction.service.impl.ServiceFactory;
import com.solvd.construction.ui.menuoptions.UserOptions;
import com.solvd.construction.ui.util.UserMenuUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserMenu {
    private static final Logger LOGGER = LogManager.getLogger();

    public static void showMenu(ServiceFactory serviceFactory) {

        while (true) {
            LOGGER.info(UserOptions.getOptions());
            switch (Input.enumInput(UserOptions.class)) {
                case EARNINGS:
                    UserMenuUtil.showEarnings(serviceFactory);
                    break;
                case PROJECT:
                    UserMenuUtil.showCosts(serviceFactory);
                    break;
                case BACK:
                    ModeSelectMenu.showMenu(serviceFactory);
                    break;
                case EXIT:
                    System.exit(0);
            }
        }

    }
}
