package com.solvd.construction.ui.util.input.helpers.prompters;

import com.solvd.construction.model.Country;
import com.solvd.construction.ui.util.input.Input;

public class CountryPrompter implements Prompter<Country> {

    @Override
    public Country newInstanceFromConsole() {
        LOGGER.info("Enter country name: ");
        String countryName = Input.stringConsoleInput();
        LOGGER.info("Enter postal code: ");
        Long postalCode = Input.longConsoleInput();
        return new Country(
                null,
                countryName,
                postalCode
        );
    }
}
