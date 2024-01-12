package com.solvd.construction.json;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.solvd.construction.json.deserealizers.*;
import com.solvd.construction.model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class JsonUnmarshaller<T extends Model, D extends StdDeserializer<T>> {
    private static final Logger LOGGER = LogManager.getLogger();

    public Optional<T> unmarshall(Class<T> modelClass, File json, String deserializerName) {
        D deserializer = getDeserializer(modelClass);
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module =
                new SimpleModule(deserializerName, new Version(1, 0, 0, null, null, null));
        module.addDeserializer(modelClass, deserializer);
        mapper.registerModule(module);
        try {
            T model = mapper.readValue(json, modelClass);
            return Optional.of(model);
        } catch (IOException e) {
            LOGGER.warn("Unable to read file {}", json.getName());
            LOGGER.warn(e.getMessage());
            return Optional.empty();
        }
    }

    @SuppressWarnings("unchecked")
    private D getDeserializer(Class<T> modelClass) {
        D deserializer = null;
        if (modelClass.equals(Client.class)) {
            deserializer = (D) new ClientDeserializer();
            return deserializer;
        } else if (modelClass.equals(Country.class)) {
            deserializer = (D) new CountryDeserializer();
            return deserializer;
        } else if (modelClass.equals(Employee.class)) {
            deserializer = (D) new EmployeeDeserializer();
            return deserializer;
        } else if (modelClass.equals(Project.class)) {
            deserializer = (D) new ProjectDeserializer();
            return deserializer;
        } else if (modelClass.equals(Supplier.class)) {
            deserializer = (D) new SupplierDeserializer();
            return deserializer;
        }
        return deserializer;
    }
}
