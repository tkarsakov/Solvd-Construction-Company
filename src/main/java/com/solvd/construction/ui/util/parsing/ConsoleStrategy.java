package com.solvd.construction.ui.util.parsing;

import com.solvd.construction.model.*;
import com.solvd.construction.service.factory.ServiceFactory;
import com.solvd.construction.ui.menuoptions.ObjectSelectOptions;
import com.solvd.construction.ui.util.input.Input;
import com.solvd.construction.ui.util.input.helpers.prompters.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConsoleStrategy extends ParsingStrategy {
    private final static Logger LOGGER = LogManager.getLogger();

    @Override
    public void parseIntoDatabase(ServiceFactory serviceFactory) {
        LOGGER.info(ObjectSelectOptions.getOptions());
        ObjectSelectOptions option = Input.enumInput(ObjectSelectOptions.class);
        Model model;
        switch (option) {
            case PROJECTMATERIAL:
                model = Input.getModelFromConsole(new ProjectMaterialPrompter());
                serviceFactory.getProjectMaterialService().create((ProjectMaterial) model);
                return;
            case SUPPLIER:
                model = Input.getModelFromConsole(new SupplierPrompter());
                serviceFactory.getSupplierService().create((Supplier) model);
                return;
            case PROJECT:
                model = Input.getModelFromConsole(new ProjectPrompter());
                serviceFactory.getProjectService().create((Project) model);
                return;
            case COUNTRY:
                model = Input.getModelFromConsole(new CountryPrompter());
                serviceFactory.getCountryService().create((Country) model);
                return;
            case CLIENT:
                model = Input.getModelFromConsole(new ClientPrompter());
                serviceFactory.getClientService().create((Client) model);
        }
    }
}
