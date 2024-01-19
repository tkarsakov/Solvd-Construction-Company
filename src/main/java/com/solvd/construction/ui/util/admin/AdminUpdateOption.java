package com.solvd.construction.ui.util.admin;

import com.solvd.construction.model.*;
import com.solvd.construction.service.factory.ServiceFactory;
import com.solvd.construction.ui.Input;
import com.solvd.construction.ui.menuoptions.ObjectSelectOptions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AdminUpdateOption implements AdminOption {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void execute(ServiceFactory serviceFactory) {
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
}
