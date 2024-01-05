package com.solvd.construction.xml.sax.handlers;

import com.solvd.construction.model.Client;
import com.solvd.construction.model.Project;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.math.BigDecimal;
import java.sql.Timestamp;

import static com.solvd.construction.xml.modeltags.ClientTags.*;
import static com.solvd.construction.xml.modeltags.ProjectTags.*;

public class ProjectHandler extends DefaultHandler implements ModelHandler<Project> {
    private String currentTag;
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
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        currentTag = ModelHandler.END_TAG;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (currentTag.equals(finishDate.name())) {
            project.setFinishDate(Timestamp.valueOf(new String(ch, start, length)));
        } else if (currentTag.equals(clientId.name())) {
            project.setClientId(Long.valueOf(new String(ch, start, length)));
        } else if (currentTag.equals(startDate.name())) {
            project.setFinishDate(Timestamp.valueOf(new String(ch, start, length)));
        } else if (currentTag.equals(floors.name())) {
            project.setFloors(Long.valueOf(new String(ch, start, length)));
        } else if (currentTag.equals(budget.name())) {
            project.setBudget(new BigDecimal(new String(ch, start, length)));
        } else if (currentTag.equals(interior_work.name())) {
            project.setInteriorWork(Boolean.valueOf(new String(ch, start, length)));
        } else if (currentTag.equals(clientName.name())) {
            project.setClient(new Client(new String(ch, start, length), null, null));
        } else if (currentTag.equals(clientEmail.name())) {
            project.getClient().setClientEmail(new String(ch, start, length));
        } else if (currentTag.equals(countryId.name())) {
            project.getClient().setCountryId(Long.valueOf(new String(ch, start, length)));
        }
    }
}
