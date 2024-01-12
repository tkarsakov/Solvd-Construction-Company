package com.solvd.construction.ui.util;

import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.solvd.construction.json.JsonUnmarshaller;
import com.solvd.construction.model.*;
import com.solvd.construction.service.impl.ServiceFactory;
import com.solvd.construction.ui.Input;
import com.solvd.construction.ui.menuoptions.JsonOptions;
import com.solvd.construction.ui.menuoptions.ObjectSelectOptions;
import com.solvd.construction.xml.CoolParser;
import com.solvd.construction.xml.sax.handlers.*;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.helpers.DefaultHandler;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;

public class InputTypeMenuUtil {
    private static final String SCHEMA_FOLDER = "src/main/resources/xsd/";
    private static final Logger LOGGER = LogManager.getLogger();

    private static String getFilename(String format) {
        LOGGER.info("Enter filename of {} file located in root folder: ", format);
        String filename;
        while (true) {
            filename = Input.stringConsoleInput();
            String regex = "^(\\w|\\s|-)+\\." + format + "$";
            if (!filename.matches(regex)) {
                LOGGER.info("Provided {} does not match *.{} pattern", filename, format);
                continue;
            }
            break;
        }
        return filename;
    }

    public static void parseIntoDatabaseSAX(ServiceFactory serviceFactory) {
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

    private static <T extends Model> void createModelJAXB(Class<T> modelClass, String filename, ServiceFactory serviceFactory) {
        T model = parseModelJAXB(modelClass, filename);
        if (model == null) {
            LOGGER.info("Result of JAXB parsing is an empty model. No SQL operations will be performed");
            return;
        }
        serviceFactory.getService(modelClass).create(model);
    }

    public static void parseIntoDatabaseJAXB(ServiceFactory serviceFactory) {
        LOGGER.info(ObjectSelectOptions.getOptions());
        ObjectSelectOptions option = Input.enumInput(ObjectSelectOptions.class);
        String filename = getFilename("xml");
        switch (option) {
            case COUNTRY -> createModelJAXB(Country.class, filename, serviceFactory);
            case PROJECT -> createModelJAXB(Project.class, filename, serviceFactory);
            case CLIENT -> createModelJAXB(Client.class, filename, serviceFactory);
            case SUPPLIER -> createModelJAXB(Supplier.class, filename, serviceFactory);
            case PROJECTMATERIAL -> createModelJAXB(ProjectMaterial.class, filename, serviceFactory);
        }
    }

    @SuppressWarnings("unchecked")
    private static <T extends Model> T parseModelJAXB(Class<T> modelClass, String filename) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(modelClass);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            T model = (T) unmarshaller.unmarshal(new FileReader(filename));
            return model;
        } catch (JAXBException e) {
            LOGGER.info("Error while creating context");
            LOGGER.fatal(e.getMessage());
        } catch (IOException e) {
            LOGGER.info("Cannot open file {}", filename);
            LOGGER.fatal(e.getMessage());
        }
        return null;
    }

    public static void parseIntoDatabaseJson(ServiceFactory serviceFactory) {
        LOGGER.info(JsonOptions.getOptions());
        JsonOptions option = Input.enumInput(JsonOptions.class);
        String filename = getFilename("json");
        switch (option) {
            case COUNTRY:
                createModelJson(Country.class, filename, "CountryDeserializer", serviceFactory);
                break;
            case SUPPLIER:
                createModelJson(Supplier.class, filename, "SupplierDeserializer", serviceFactory);
                break;
            case CLIENT:
                createModelJson(Client.class, filename, "ClientDeserializer", serviceFactory);
                break;
            case PROJECT:
                createModelJson(Project.class, filename, "ProjectDeserializer", serviceFactory);
                break;
            case EMPLOYEE:
                createModelJson(Employee.class, filename, "EmployeeDeserializer", serviceFactory);
                break;
        }
    }

    private static <T extends Model, D extends StdDeserializer<T>> T getModelFromJson(Class<T> modelClass, String filename, String deserializerName) {
        JsonUnmarshaller<T, D> jsonUnmarshaller = new JsonUnmarshaller<>();
        Optional<T> optional = jsonUnmarshaller.unmarshall(modelClass, new File(filename), deserializerName);
        if (optional.isEmpty()) {
            LOGGER.info("Json deserialization failed");
            return null;
        } else {
            return optional.get();
        }
    }

    private static <T extends Model> void createModelJson(Class<T> modelClass, String filename, String deserializerName, ServiceFactory serviceFactory) {
        T model = getModelFromJson(modelClass, filename, deserializerName);
        if (model == null) {
            LOGGER.warn("JSON deserialization returned empty model. No database actions will be performed");
            return;
        }
        serviceFactory.getService(modelClass).create(model);
    }
}
