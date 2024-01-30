package com.solvd.construction.ui.util.input.helpers.prompters;

import com.solvd.construction.model.Project;
import com.solvd.construction.ui.util.input.Input;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class ProjectPrompter implements Prompter<Project> {
    @Override
    public Project newInstanceFromConsole() {
        LOGGER.info("Enter start date(DD-MM-YYYY HH:MM:SS): ");
        String startDate = Input.stringConsoleInput();
        LOGGER.info("Enter client ID: ");
        Long clientId = Input.longConsoleInput();
        LOGGER.info("Enter finish date(DD-MM-YYYY HH:MM:SS): ");
        String finishDate = Input.stringConsoleInput();
        LOGGER.info("Enter interior work (0 or 1 for true or false): ");
        Boolean interiorWork = Input.stringConsoleInput().equals("1");
        LOGGER.info("Enter number of floors: ");
        Long floors = Input.longConsoleInput();
        LOGGER.info("Enter budget: ");
        BigDecimal budget = new BigDecimal(Input.stringConsoleInput());
        return new Project(
                null,
                Timestamp.valueOf(startDate),
                clientId,
                Timestamp.valueOf(finishDate),
                floors,
                budget,
                interiorWork,
                null
        );
    }
}
