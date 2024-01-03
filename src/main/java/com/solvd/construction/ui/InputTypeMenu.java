package com.solvd.construction.ui;

import com.solvd.construction.service.impl.ServiceFactory;
import com.solvd.construction.ui.menuoptions.InputTypeOptions;
import com.solvd.construction.ui.util.AdminMenuUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class InputTypeMenu {
    private static final Logger LOGGER = LogManager.getLogger();

    public static void showMenu(ServiceFactory serviceFactory) {
        LOGGER.info(InputTypeOptions.getOptions());
        switch (Input.enumInput(InputTypeOptions.class)) {
            case CONSOLE -> AdminMenuUtil.create(serviceFactory);
            case XML -> {
            }
            case BACK -> AdminMenu.showMenu(serviceFactory);
        }
    }
}
