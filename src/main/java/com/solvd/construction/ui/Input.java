package com.solvd.construction.ui;

import com.solvd.construction.ui.menuoptions.UserOptions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Input {
    private static final Logger LOGGER = LogManager.getLogger();

    public static String stringConsoleInput() {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        while (input.isBlank()) {
            try {
                input = scanner.nextLine().toLowerCase();
            } catch (NoSuchElementException e) {
                LOGGER.info("Incorrect input. Please try again");
            }
        }
        return input;
    }

    public static UserOptions menuOptionConsoleInput() {
        UserOptions input;
        while (true) {
            try {
                input = UserOptions.valueOf(Input.stringConsoleInput().toUpperCase().strip());
                return input;
            } catch (IllegalArgumentException e) {
                LOGGER.info("Wrong command.");
            }
        }
    }
}
