package com.solvd.construction.ui;

import com.solvd.construction.service.factory.ServiceFactory;
import com.solvd.construction.ui.menuoptions.AdminOptions;
import com.solvd.construction.ui.util.admin.AdminDeleteOption;
import com.solvd.construction.ui.util.admin.AdminMenuUtil;
import com.solvd.construction.ui.util.admin.AdminReadOption;
import com.solvd.construction.ui.util.admin.AdminUpdateOption;
import com.solvd.construction.ui.util.input.Input;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AdminMenu {
    private static final Logger LOGGER = LogManager.getLogger();

    public static void showMenu(ServiceFactory serviceFactory) {
        AdminMenuUtil adminMenuUtil = new AdminMenuUtil(serviceFactory);
        while (true) {
            LOGGER.info(AdminOptions.getOptions());
            switch (Input.enumInput(AdminOptions.class)) {
                case CREATE -> InputTypeMenu.showMenu(serviceFactory);
                case READ -> adminMenuUtil.executeOption(new AdminReadOption());
                case UPDATE -> adminMenuUtil.executeOption(new AdminUpdateOption());
                case DELETE -> adminMenuUtil.executeOption(new AdminDeleteOption());
                case BACK -> ModeSelectMenu.showMenu(serviceFactory);
                case EXIT -> System.exit(0);
            }
        }
    }
}
