package com.solvd.construction.json.deserealizers;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.solvd.construction.model.Project;
import com.solvd.construction.model.ProjectMaterial;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;

public class ProjectDeserializer extends StdDeserializer<Project> {
    private static final Logger LOGGER = LogManager.getLogger();

    public ProjectDeserializer() {
        this(null);
    }

    protected ProjectDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Project deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        ObjectCodec codec = jsonParser.getCodec();
        JsonNode node = codec.readTree(jsonParser);
        if (node.get("project") == null) {
            LOGGER.warn("Root node 'project' not found in provided file");
            return null;
        }
        node = node.get("project");

        Project project = new Project(null, null, null, null, null, null, null, null);
        Timestamp finishDate = Timestamp.valueOf(node.get("finishDate").asText());
        project.setFinishDate(finishDate);
        Timestamp startDate = Timestamp.valueOf(node.get("startDate").asText());
        project.setStartDate(startDate);
        Long floors = node.get("floors").asLong();
        project.setFloors(floors);
        Boolean interiorWork = node.get("interiorWork").asBoolean();
        project.setInteriorWork(interiorWork);
        BigDecimal budget = new BigDecimal(node.get("budget").asText());
        project.setBudget(budget);

        if (node.get("clientId") == null) {
            project.setClient(ClientDeserializer.getModelFromRootNode(node.get("client")));
        } else {
            Long clientId = node.get("clientId").asLong();
            project.setClientId(clientId);
        }

        project.setEmployeeList(new ArrayList<>());
        for (var employeeNode : node.get("employees")) {
            project.getEmployeeList().add(EmployeeDeserializer.getModelFromNode(employeeNode));
        }
        project.setProjectMaterials(new ArrayList<>());
        for (var projectMaterialNode : node.get("projectMaterials")) {
            project.getProjectMaterials().add(
                    new ProjectMaterial(
                            null,
                            projectMaterialNode.get("suppliedMaterialId").asLong(),
                            new BigDecimal(projectMaterialNode.get("materialAmount").asText()),
                            null,
                            projectMaterialNode.get("measure").asText()
                    )
            );
        }
        return project;
    }
}
