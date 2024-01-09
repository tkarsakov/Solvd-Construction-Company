package com.solvd.construction.xml.sax.handlers;

import com.solvd.construction.model.Client;
import com.solvd.construction.model.Employee;
import com.solvd.construction.model.Project;
import com.solvd.construction.model.ProjectMaterial;
import com.solvd.construction.xml.modeltags.ProjectTags;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;

import static com.solvd.construction.xml.modeltags.ProjectTags.*;

public class ProjectHandler extends DefaultHandler implements ModelHandler<Project> {
    private String currentTag;
    private ProjectMaterial currentProjectMaterial;
    private final Project project = new Project(
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null
    );

    @Override
    public Project getModel() {
        return project;
    }

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        currentTag = qName;
        if (currentTag.equals(employees.name())) {
            project.setEmployeeList(new ArrayList<>());
        } else if (currentTag.equals(employee.name())) {
            project.getEmployeeList().add(new Employee(
                    null,
                    attributes.getValue("firstName"),
                    attributes.getValue("lastName"),
                    Long.valueOf(attributes.getValue("positionId"))));
        } else if (currentTag.equals(projectMaterials.name())) {
            project.setProjectMaterials(new ArrayList<>());
        } else if (currentTag.equals(projectMaterial.name())) {
            currentProjectMaterial = new ProjectMaterial(null, null, null, null, null);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals(projectMaterial.name())) {
            project.getProjectMaterials().add(currentProjectMaterial);
        }
        currentTag = ModelHandler.END_TAG;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (Arrays.stream(ProjectTags.values()).noneMatch(value -> value.name().equals(currentTag))) {
            return;
        }
        ProjectTags tag = ProjectTags.valueOf(currentTag);
        String content = new String(ch, start, length);
        switch (tag) {
            case finishDate -> project.setFinishDate(Timestamp.valueOf(content));
            case budget -> project.setBudget(new BigDecimal(content));
            case floors -> project.setFloors(Long.valueOf(content));
            case interiorWork -> project.setInteriorWork(Boolean.valueOf(content));
            case clientId -> project.setClientId(Long.valueOf(content));
            case startDate -> project.setStartDate(Timestamp.valueOf(content));
            case clientName -> project.setClient(new Client(content, null, null));
            case clientEmail -> project.getClient().setClientEmail(content);
            case countryId -> project.getClient().setCountryId(Long.valueOf(content));
            case suppliedMaterialId -> currentProjectMaterial.setSuppliedMaterialId(Long.valueOf(content));
            case materialAmount -> currentProjectMaterial.setMaterialAmount(new BigDecimal(content));
            case measure -> currentProjectMaterial.setMeasure(content);
        }
    }
}
