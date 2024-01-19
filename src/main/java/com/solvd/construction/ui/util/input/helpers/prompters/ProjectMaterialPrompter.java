package com.solvd.construction.ui.util.input.helpers.prompters;

import com.solvd.construction.model.ProjectMaterial;
import com.solvd.construction.ui.util.input.Input;

import java.math.BigDecimal;

public class ProjectMaterialPrompter implements Prompter<ProjectMaterial> {
    @Override
    public ProjectMaterial newInstanceFromConsole() {
        LOGGER.info("Enter supplied material id");
        Long suppliedMaterialId = Input.longConsoleInput();
        LOGGER.info("Enter material amount");
        BigDecimal materialAmount = new BigDecimal(Input.stringConsoleInput());
        LOGGER.info("Enter project Id");
        Long projectId = Input.longConsoleInput();
        LOGGER.info("Enter measure");
        String measure = Input.stringConsoleInput();
        return new ProjectMaterial(
                null,
                suppliedMaterialId,
                materialAmount,
                projectId,
                measure
        );
    }
}
