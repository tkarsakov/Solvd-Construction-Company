package com.solvd.construction.ui.util.parsing;

import com.solvd.construction.model.*;
import com.solvd.construction.service.factory.ServiceFactory;
import com.solvd.construction.ui.Input;
import com.solvd.construction.ui.menuoptions.ObjectSelectOptions;
import com.solvd.construction.xml.CoolParser;
import com.solvd.construction.xml.sax.handlers.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.helpers.DefaultHandler;

import java.util.Optional;

public class SaxStrategy extends ParsingStrategy {
    private static final String SCHEMA_FOLDER = "src/main/resources/xsd/";
    private static final Logger LOGGER = LogManager.getLogger();

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

    private static <T extends Model, M extends DefaultHandler & ModelHandler<?>> void createModelSAX(Class<T> modelClass,
                                                                                                     M handler, String schemaFileName, String xmlFileName, ServiceFactory serviceFactory) {
        T model = getModelFromXml(handler, schemaFileName, xmlFileName);
        if (model == null) {
            LOGGER.info("Result of SAX parsing is an empty model. No SQL operations will be performed");
            return;
        }
        serviceFactory.getService(modelClass).create(model);
    }

    @Override
    public void parseIntoDatabase(ServiceFactory serviceFactory) {
        LOGGER.info(ObjectSelectOptions.getOptions());
        ObjectSelectOptions option = Input.enumInput(ObjectSelectOptions.class);
        String filename = getFilename("xml");
        switch (option) {
            case COUNTRY:
                createModelSAX(Country.class, new CountryHandler(), "country.xsd", filename, serviceFactory);
                break;
            case CLIENT:
                createModelSAX(Client.class, new ClientHandler(), "client.xsd", filename, serviceFactory);
                break;
            case PROJECT:
                createModelSAX(Project.class, new ProjectHandler(), "project.xsd", filename, serviceFactory);
                break;
            case SUPPLIER:
                createModelSAX(Supplier.class, new SupplierHandler(), "supplier.xsd", filename, serviceFactory);
                break;
            case PROJECTMATERIAL:
                createModelSAX(ProjectMaterial.class, new ProjectMaterialHandler(), "projectMaterial.xsd", filename, serviceFactory);
                break;
        }
    }
}
