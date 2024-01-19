package com.solvd.construction.ui.util.input.helpers;

import com.solvd.construction.model.Model;
import com.solvd.construction.ui.util.input.helpers.prompters.Prompter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ModelCreationHelper {
    private static final Logger LOGGER = LogManager.getLogger();

    public static <T extends Model> Model getModelFromConsole(Prompter<T> prompter) {
        return prompter.newInstanceFromConsole();
    }
}
