package com.solvd.construction.ui.util.input.helpers.prompters;

import com.solvd.construction.model.Model;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public interface Prompter<T extends Model> {
    Logger LOGGER = LogManager.getLogger();

    T newInstanceFromConsole();
}
