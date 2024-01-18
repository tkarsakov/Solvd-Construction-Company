package com.solvd.construction.ui;

import com.solvd.construction.service.factory.ServiceFactory;
import com.solvd.construction.ui.menuoptions.InputTypeOptions;
import com.solvd.construction.ui.util.ParsingHandler;
import com.solvd.construction.ui.util.parsing.ConsoleStrategy;
import com.solvd.construction.ui.util.parsing.JaxbStrategy;
import com.solvd.construction.ui.util.parsing.JsonStrategy;
import com.solvd.construction.ui.util.parsing.SaxStrategy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class InputTypeMenu {
    private static final Logger LOGGER = LogManager.getLogger();

    public static void showMenu(ServiceFactory serviceFactory) {
        ParsingHandler parsingHandler = new ParsingHandler(serviceFactory);
        LOGGER.info(InputTypeOptions.getOptions());
        switch (Input.enumInput(InputTypeOptions.class)) {
            case CONSOLE -> parsingHandler.parse(new ConsoleStrategy());
            case SAX -> parsingHandler.parse(new SaxStrategy());
            case JSON -> parsingHandler.parse(new JsonStrategy());
            case JAXB -> parsingHandler.parse(new JaxbStrategy());
            case BACK -> AdminMenu.showMenu(serviceFactory);
        }
    }
}
