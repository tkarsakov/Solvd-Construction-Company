package com.solvd.construction.ui.util.parsing;

import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.solvd.construction.json.JsonUnmarshaller;
import com.solvd.construction.model.*;
import com.solvd.construction.service.factory.ServiceFactory;
import com.solvd.construction.ui.menuoptions.JsonOptions;
import com.solvd.construction.ui.util.input.Input;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.Optional;

public class JsonStrategy extends ParsingStrategy {
    private final static Logger LOGGER = LogManager.getLogger();

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

    @Override
    public void parseIntoDatabase(ServiceFactory serviceFactory) {
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
}
