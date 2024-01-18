package com.solvd.construction.ui;

import com.solvd.construction.service.factory.ServiceFactory;
import com.solvd.construction.ui.menuoptions.AdminOptions;
import com.solvd.construction.ui.util.AdminMenuUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AdminMenu {
    private static final Logger LOGGER = LogManager.getLogger();

    public static void showMenu(ServiceFactory serviceFactory) {
        while (true) {
            LOGGER.info(AdminOptions.getOptions());
            switch (Input.enumInput(AdminOptions.class)) {
                case CREATE -> InputTypeMenu.showMenu(serviceFactory);
                case READ -> AdminMenuUtil.read(serviceFactory);
                case UPDATE -> AdminMenuUtil.update(serviceFactory);
                case DELETE -> AdminMenuUtil.delete(serviceFactory);
                case BACK -> ModeSelectMenu.showMenu(serviceFactory);
                case EXIT -> System.exit(0);
            }
        }
    }
}
