package com.solvd.construction.ui.util.admin;

import com.solvd.construction.model.*;
import com.solvd.construction.service.factory.ServiceFactory;
import com.solvd.construction.ui.menuoptions.ObjectSelectOptions;
import com.solvd.construction.ui.util.input.Input;
import com.solvd.construction.ui.util.input.helpers.prompters.*;
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
        Model model;
        switch (option) {
            case PROJECTMATERIAL:
                model = Input.getModelFromConsole(new ProjectMaterialPrompter());
                model.setId(id);
                serviceFactory.getProjectMaterialService().update((ProjectMaterial) model);
                return;
            case SUPPLIER:
                model = Input.getModelFromConsole(new SupplierPrompter());
                model.setId(id);
                serviceFactory.getSupplierService().update((Supplier) model);
                return;
            case PROJECT:
                model = Input.getModelFromConsole(new ProjectPrompter());
                model.setId(id);
                serviceFactory.getProjectService().update((Project) model);
                return;
            case COUNTRY:
                model = Input.getModelFromConsole(new CountryPrompter());
                model.setId(id);
                serviceFactory.getCountryService().update((Country) model);
                return;
            case CLIENT:
                model = Input.getModelFromConsole(new ClientPrompter());
                model.setId(id);
                serviceFactory.getClientService().update((Client) model);
        }
    }
}
