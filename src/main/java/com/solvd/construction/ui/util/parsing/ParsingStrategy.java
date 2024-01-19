package com.solvd.construction.ui.util.parsing;

import com.solvd.construction.service.factory.ServiceFactory;
import com.solvd.construction.ui.util.input.Input;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class ParsingStrategy {
    private final static Logger LOGGER = LogManager.getLogger();

    protected static String getFilename(String format) {
        LOGGER.info("Enter filename of {} file located in root folder: ", format);
        String filename;
        while (true) {
            filename = Input.stringConsoleInput();
            String regex = "^(\\w|\\s|-)+\\." + format + "$";
            if (!filename.matches(regex)) {
                LOGGER.info("Provided {} does not match *.{} pattern", filename, format);
                continue;
            }
            break;
        }
        return filename;
    }

    public abstract void parseIntoDatabase(ServiceFactory serviceFactory);
}
