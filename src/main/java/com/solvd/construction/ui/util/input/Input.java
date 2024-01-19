package com.solvd.construction.ui.util.input;

import com.solvd.construction.model.Model;
import com.solvd.construction.ui.util.input.helpers.ModelCreationHelper;
import com.solvd.construction.ui.util.input.helpers.prompters.Prompter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Input {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final StringTokenizer commands;

    static {
        LOGGER.info("Enter a menu command or a string of commands delimited by whitespace (eg. 'jdbc user project')");
        LOGGER.info("Commands only work for menu options, data-sensitive inputs need to be typed in by hand");
        commands = new StringTokenizer(Input.stringConsoleInput());
    }

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

    public static Long longConsoleInput() {
        Scanner scanner = new Scanner(System.in);
        Long input = null;
        while (input == null) {
            try {
                LOGGER.info("Enter a number of type long: ");
                input = Long.parseLong(scanner.nextLine());
            } catch (NoSuchElementException e) {
                LOGGER.info("Incorrect input. Please try again");
            } catch (NumberFormatException e) {
                LOGGER.info("Input is not a number or is formatted incorrectly");
            }
        }
        return input;
    }


    public static <T extends Enum<T>> T enumInput(Class<T> enumClass) {
        T input;
        while (true) {
            try {
                if (commands.hasMoreTokens()) {
                    input = Enum.valueOf(enumClass, commands.nextToken().toUpperCase());
                    return input;
                }
                input = Enum.valueOf(enumClass, Input.stringConsoleInput().toUpperCase().strip());
                return input;
            } catch (IllegalArgumentException e) {
                LOGGER.info("Wrong command.");
            }
        }
    }

    public static <T extends Model> Model getModelFromConsole(Prompter<T> prompter) {
        return ModelCreationHelper.getModelFromConsole(prompter);
    }
}
