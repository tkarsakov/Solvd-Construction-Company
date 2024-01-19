package com.solvd.construction.ui.util.input.helpers.prompters;

import com.solvd.construction.model.Supplier;
import com.solvd.construction.ui.util.input.Input;

public class SupplierPrompter implements Prompter<Supplier> {

    @Override
    public Supplier newInstanceFromConsole() {
        String supplierName = Input.stringConsoleInput();
        LOGGER.info("Enter client email: ");
        String supplierEmail = Input.stringConsoleInput();
        LOGGER.info("Enter country ID: ");
        Long countryId1 = Input.longConsoleInput();
        return new Supplier(
                null,
                supplierName,
                supplierEmail,
                countryId1
        );
    }
}
