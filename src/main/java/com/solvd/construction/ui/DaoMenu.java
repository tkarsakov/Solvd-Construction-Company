package com.solvd.construction.ui;

import com.solvd.construction.service.factory.JdbcServiceFactory;
import com.solvd.construction.service.factory.MyBatisServiceFactory;
import com.solvd.construction.service.factory.ServiceFactory;
import com.solvd.construction.ui.menuoptions.DaoOptions;
import com.solvd.construction.ui.util.input.Input;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DaoMenu {
    private static final Logger LOGGER = LogManager.getLogger();

    public static void showMenu() {
        LOGGER.info(DaoOptions.getOptions());
        switch (Input.enumInput(DaoOptions.class)) {
            case JDBC:
                ModeSelectMenu.showMenu(new ServiceFactory(new JdbcServiceFactory()));
                break;
            case MYBATIS:
                ModeSelectMenu.showMenu(new ServiceFactory(new MyBatisServiceFactory()));
                break;
            case EXIT:
                System.exit(0);
        }
    }
}
