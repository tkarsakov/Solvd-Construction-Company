package com.solvd.construction.ui.util.admin;

import com.solvd.construction.service.factory.ServiceFactory;
import com.solvd.construction.ui.Input;
import com.solvd.construction.ui.menuoptions.ObjectSelectOptions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AdminReadOption implements AdminOption {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void execute(ServiceFactory serviceFactory) {
        LOGGER.info(ObjectSelectOptions.getOptions());
        ObjectSelectOptions option = Input.enumInput(ObjectSelectOptions.class);
        switch (option) {
            case PROJECTMATERIAL -> serviceFactory.getProjectMaterialService().retrieveAll()
                    .forEach(projectMaterial -> LOGGER.info(projectMaterial.toString()));
            case SUPPLIER -> serviceFactory.getSupplierService().retrieveAll()
                    .forEach(supplier -> LOGGER.info(supplier.toString()));
            case PROJECT -> serviceFactory.getProjectService().retrieveAll()
                    .forEach(project -> LOGGER.info(project.toString()));
            case COUNTRY -> serviceFactory.getCountryService().retrieveAll()
                    .forEach(country -> LOGGER.info(country.toString()));
            case CLIENT -> serviceFactory.getClientService().retrieveAll()
                    .forEach(client -> LOGGER.info(client.toString()));
        }
    }
}
