package com.solvd.construction.ui.util;

import com.solvd.construction.model.Country;
import com.solvd.construction.model.Model;
import com.solvd.construction.service.impl.ServiceFactory;
import com.solvd.construction.ui.Input;
import com.solvd.construction.ui.menuoptions.ObjectSelectOptions;
import com.solvd.construction.xml.CoolParser;
import com.solvd.construction.xml.CountryHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class InputTypeMenuUtil {
    private static final String RESOURCE_FOLDER = "src/main/resources";
    private static final String SCHEMA_FOLDER = "src/main/resources/xsd";
    private static final Logger LOGGER = LogManager.getLogger();

    public static void parseIntoDatabase(ServiceFactory serviceFactory) {
        ObjectSelectOptions option = Input.enumInput(ObjectSelectOptions.class);
        LOGGER.info("Enter filename of xml file located in resources folder: ");
        String filename;
        while (true) {
            filename = Input.stringConsoleInput();
            if (!filename.matches("^(\\w|\\s|-)+\\.xml$")) {
                LOGGER.info("Provided {} does not match *.xml pattern", filename);
                continue;
            }
            filename = RESOURCE_FOLDER + filename;
            break;
        }
        switch (option) {
            case COUNTRY:
                CoolParser<CountryHandler> coolParser = new CoolParser<>(new CountryHandler(), SCHEMA_FOLDER + "country.xsd");
                Optional<Model> optionalCountry = coolParser.parse(filename);
                if (optionalCountry.isPresent()) {
                    Country country = (Country) optionalCountry.get();
                    serviceFactory.getCountryService().create(country);
                } else {
                    LOGGER.info("Unable to parse {}", filename);
                }
                break;
        }
    }
}
