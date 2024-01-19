package com.solvd.construction.ui.util.admin;

import com.solvd.construction.service.factory.ServiceFactory;
import com.solvd.construction.ui.Input;
import com.solvd.construction.ui.menuoptions.ObjectSelectOptions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AdminDeleteOption implements AdminOption {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void execute(ServiceFactory serviceFactory) {
        LOGGER.info(ObjectSelectOptions.getOptions());
        ObjectSelectOptions option = Input.enumInput(ObjectSelectOptions.class);
        LOGGER.info("Enter id");
        Long id = Input.longConsoleInput();
        switch (option) {
            case PROJECTMATERIAL -> serviceFactory.getProjectMaterialService().delete(id);
            case SUPPLIER -> serviceFactory.getSupplierService().delete(id);
            case PROJECT -> serviceFactory.getProjectService().delete(id);
            case COUNTRY -> serviceFactory.getCountryService().delete(id);
            case CLIENT -> serviceFactory.getClientService().delete(id);
        }
    }
}
