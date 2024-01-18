package com.solvd.construction.ui.util;

import com.solvd.construction.model.*;
import com.solvd.construction.service.factory.ServiceFactory;
import com.solvd.construction.ui.Input;
import com.solvd.construction.ui.menuoptions.ObjectSelectOptions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class AdminMenuUtil {
    private static final Logger LOGGER = LogManager.getLogger();

    public static void read(ServiceFactory serviceFactory) {
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

    public static void update(ServiceFactory serviceFactory) {
        LOGGER.info(ObjectSelectOptions.getOptions());
        ObjectSelectOptions option = Input.enumInput(ObjectSelectOptions.class);
        LOGGER.info("Enter id");
        Long id = Input.longConsoleInput();
        Model model = Input.getModelFromConsole(option);
        model.setId(id);
        switch (option) {
            case PROJECTMATERIAL -> serviceFactory.getProjectMaterialService().update((ProjectMaterial) model);
            case SUPPLIER -> serviceFactory.getSupplierService().update((Supplier) model);
            case PROJECT -> serviceFactory.getProjectService().update((Project) model);
            case COUNTRY -> serviceFactory.getCountryService().update((Country) model);
            case CLIENT -> serviceFactory.getClientService().update((Client) model);
        }
    }

    public static void delete(ServiceFactory serviceFactory) {
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
