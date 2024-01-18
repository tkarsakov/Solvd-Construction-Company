package com.solvd.construction.ui.util.parsing;

import com.solvd.construction.model.*;
import com.solvd.construction.service.factory.ServiceFactory;
import com.solvd.construction.ui.Input;
import com.solvd.construction.ui.menuoptions.ObjectSelectOptions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConsoleStrategy extends ParsingStrategy {
    private final static Logger LOGGER = LogManager.getLogger();

    @Override
    public void parseIntoDatabase(ServiceFactory serviceFactory) {
        LOGGER.info(ObjectSelectOptions.getOptions());
        ObjectSelectOptions option = Input.enumInput(ObjectSelectOptions.class);
        Model model = Input.getModelFromConsole(option);
        switch (option) {
            case PROJECTMATERIAL -> serviceFactory.getProjectMaterialService().create((ProjectMaterial) model);
            case SUPPLIER -> serviceFactory.getSupplierService().create((Supplier) model);
            case PROJECT -> serviceFactory.getProjectService().create((Project) model);
            case COUNTRY -> serviceFactory.getCountryService().create((Country) model);
            case CLIENT -> serviceFactory.getClientService().create((Client) model);
        }
    }
}
