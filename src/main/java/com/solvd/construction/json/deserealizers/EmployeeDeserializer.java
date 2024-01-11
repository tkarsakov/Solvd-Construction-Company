package com.solvd.construction.json.deserealizers;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.solvd.construction.model.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class EmployeeDeserializer extends StdDeserializer<Employee> {
    private static final Logger LOGGER = LogManager.getLogger();

    public EmployeeDeserializer() {
        this(null);
    }

    protected EmployeeDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Employee deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        ObjectCodec codec = jsonParser.getCodec();
        JsonNode node = codec.readTree(jsonParser);
        if (node.get("employee") == null) {
            LOGGER.warn("Root node employee not found in provided file");
            return null;
        }
        node = node.get("employee");
        return getModelFromNode(node);
    }

    public static Employee getModelFromNode(JsonNode node) {
        String firstName = node.get("firstName").asText();
        String lastName = node.get("lastName").asText();
        Long positionId = node.get("positionId").asLong();
        return new Employee(null, firstName, lastName, positionId);
    }
}
