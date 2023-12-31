package com.solvd.construction.ui.util;

import com.solvd.construction.model.*;
import com.solvd.construction.service.impl.ServiceFactory;
import com.solvd.construction.ui.Input;
import com.solvd.construction.ui.menuoptions.ObjectSelectOptions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class AdminMenuUtil {
    private static final Logger LOGGER = LogManager.getLogger();

    public static void create(ServiceFactory serviceFactory) {
        LOGGER.info(ObjectSelectOptions.getOptions());
        ObjectSelectOptions option = Input.objectSelectOptionConsoleInput();
        Model model = Input.getModelFromConsole(option);
        switch (option) {
            case PROJECTMATERIAL -> serviceFactory.getProjectMaterialService().create((ProjectMaterial) model);
            case SUPPLIER -> serviceFactory.getSupplierService().create((Supplier) model);
            case PROJECT -> serviceFactory.getProjectService().create((Project) model);
            case COUNTRY -> serviceFactory.getCountryService().create((Country) model);
            case CLIENT -> serviceFactory.getClientService().create((Client) model);
        }
    }

    public static void read(ServiceFactory serviceFactory) {
        LOGGER.info(ObjectSelectOptions.getOptions());
        ObjectSelectOptions option = Input.objectSelectOptionConsoleInput();
        switch (option) {
            case PROJECTMATERIAL -> serviceFactory.getProjectMaterialService().retrieveAll()
                    .forEach(projectMaterial -> LOGGER.info(projectMaterial.toString()));
            case SUPPLIER -> serviceFactory.getSupplierService().retrieveAll();
            case PROJECT -> serviceFactory.getProjectService().retrieveAll();
            case COUNTRY -> serviceFactory.getCountryService().retrieveAll();
            case CLIENT -> serviceFactory.getClientService().retrieveAll();
        }
    }

    public static void update(ServiceFactory serviceFactory) {
        LOGGER.info(ObjectSelectOptions.getOptions());
        ObjectSelectOptions option = Input.objectSelectOptionConsoleInput();
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
        ObjectSelectOptions option = Input.objectSelectOptionConsoleInput();
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
