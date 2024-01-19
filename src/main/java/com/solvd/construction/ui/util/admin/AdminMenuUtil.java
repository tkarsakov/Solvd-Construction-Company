package com.solvd.construction.ui.util.admin;

import com.solvd.construction.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class AdminMenuUtil {
    private static final Logger LOGGER = LogManager.getLogger();
    protected final ServiceFactory serviceFactory;

    public AdminMenuUtil(ServiceFactory serviceFactory) {
        this.serviceFactory = serviceFactory;
    }

    public void executeOption(AdminOption adminOption) {
        adminOption.execute(serviceFactory);
    }

}
