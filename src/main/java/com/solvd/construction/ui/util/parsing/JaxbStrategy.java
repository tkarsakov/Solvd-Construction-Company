package com.solvd.construction.ui.util.parsing;

import com.solvd.construction.model.*;
import com.solvd.construction.service.factory.ServiceFactory;
import com.solvd.construction.ui.menuoptions.ObjectSelectOptions;
import com.solvd.construction.ui.util.input.Input;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileReader;
import java.io.IOException;

public class JaxbStrategy extends ParsingStrategy {
    private final static Logger LOGGER = LogManager.getLogger();

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

    private static <T extends Model> void createModelJAXB(Class<T> modelClass, String filename, ServiceFactory serviceFactory) {
        T model = parseModelJAXB(modelClass, filename);
        if (model == null) {
            LOGGER.info("Result of JAXB parsing is an empty model. No SQL operations will be performed");
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
            case COUNTRY -> createModelJAXB(Country.class, filename, serviceFactory);
            case PROJECT -> createModelJAXB(Project.class, filename, serviceFactory);
            case CLIENT -> createModelJAXB(Client.class, filename, serviceFactory);
            case SUPPLIER -> createModelJAXB(Supplier.class, filename, serviceFactory);
            case PROJECTMATERIAL -> createModelJAXB(ProjectMaterial.class, filename, serviceFactory);
        }
    }

}
