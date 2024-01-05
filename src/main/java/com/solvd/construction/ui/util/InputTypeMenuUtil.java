package com.solvd.construction.ui.util;

import com.solvd.construction.model.*;
import com.solvd.construction.service.impl.ServiceFactory;
import com.solvd.construction.ui.Input;
import com.solvd.construction.ui.menuoptions.ObjectSelectOptions;
import com.solvd.construction.xml.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.helpers.DefaultHandler;

import java.util.Optional;

public class InputTypeMenuUtil {
    private static final String SCHEMA_FOLDER = "src/main/resources/xsd/";
    private static final Logger LOGGER = LogManager.getLogger();

    public static void parseIntoDatabase(ServiceFactory serviceFactory) {
        LOGGER.info(ObjectSelectOptions.getOptions());
        ObjectSelectOptions option = Input.enumInput(ObjectSelectOptions.class);
        LOGGER.info("Enter filename of xml file located in resources folder: ");
        String filename;
        while (true) {
            filename = Input.stringConsoleInput();
            if (!filename.matches("^(\\w|\\s|-)+\\.xml$")) {
                LOGGER.info("Provided {} does not match *.xml pattern", filename);
                continue;
            }
            break;
        }
        switch (option) {
            case COUNTRY:
                createModel(Country.class, new CountryHandler(), "country.xsd", filename, serviceFactory);
                break;
            case CLIENT:
                createModel(Client.class, new ClientHandler(), "client.xsd", filename, serviceFactory);
                break;
            case PROJECT:
                createModel(Project.class, new ProjectHandler(), "project.xsd", filename, serviceFactory);
                break;
            case SUPPLIER:
                createModel(Supplier.class, new SupplierHandler(), "supplier.xsd", filename, serviceFactory);
                break;
            case PROJECTMATERIAL:
                createModel(ProjectMaterial.class, new ProjectMaterialHandler(), "projectMaterial.xsd", filename, serviceFactory);
                break;
        }
    }

    @SuppressWarnings("unchecked")
    private static <T extends Model, M extends DefaultHandler & ModelHandler<?>> T getModelFromXml(
            M handler, String schemaFileName, String xmlFileName) {
        CoolParser<M> coolParser = new CoolParser<>(handler, SCHEMA_FOLDER + schemaFileName);
        Optional<Model> optionalModel = coolParser.parse(xmlFileName);
        if (optionalModel.isPresent()) {
            return (T) optionalModel.get();
        } else {
            LOGGER.info("Unable to parse {}", xmlFileName);
        }
        return null;
    }

    private static <T extends Model, M extends DefaultHandler & ModelHandler<?>> void createModel(Class<T> modelClass,
                                                                                                  M handler, String schemaFileName, String xmlFileName, ServiceFactory serviceFactory) {
        T model = getModelFromXml(handler, schemaFileName, xmlFileName);
        if (model == null) {
            return;
        }
        serviceFactory.getService(modelClass).create(model);
    }
}
