package com.solvd.construction.ui.util.input.helpers.prompters;

import com.solvd.construction.model.Client;
import com.solvd.construction.ui.util.input.Input;

public class ClientPrompter implements Prompter<Client> {
    @Override
    public Client newInstanceFromConsole() {
        LOGGER.info("Enter client name: ");
        String clientName = Input.stringConsoleInput();
        LOGGER.info("Enter client email: ");
        String clientEmail = Input.stringConsoleInput();
        LOGGER.info("Enter country ID: ");
        Long countryId = Input.longConsoleInput();
        return new Client(
                null,
                clientName,
                clientEmail,
                countryId
        );
    }
}
