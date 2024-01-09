package com.solvd.construction.xml.sax.handlers;

import com.solvd.construction.model.ProjectMaterial;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.math.BigDecimal;

import static com.solvd.construction.xml.modeltags.ProjectMaterialTags.*;

public class ProjectMaterialHandler extends DefaultHandler implements ModelHandler<ProjectMaterial> {
    private String currentTag;
    private final ProjectMaterial projectMaterial = new ProjectMaterial(null, null, null, null, null);

    @Override
    public ProjectMaterial getModel() {
        return projectMaterial;
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
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        currentTag = ModelHandler.END_TAG;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (currentTag.equals(suppliedMaterialId.name())) {
            projectMaterial.setSuppliedMaterialId(Long.valueOf(new String(ch, start, length)));
        } else if (currentTag.equals(materialAmount.name())) {
            projectMaterial.setMaterialAmount(new BigDecimal(new String(ch, start, length)));
        } else if (currentTag.equals(projectId.name())) {
            projectMaterial.setProjectId(Long.valueOf(new String(ch, start, length)));
        } else if (currentTag.equals(measure.name())) {
            projectMaterial.setMeasure(new String(ch, start, length));
        }
    }
}
