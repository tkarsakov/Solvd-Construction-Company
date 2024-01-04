package com.solvd.construction.ui;

import com.solvd.construction.model.*;
import com.solvd.construction.ui.menuoptions.ObjectSelectOptions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.Timestamp;
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

    public static Model getModelFromConsole(ObjectSelectOptions option) {
        Model model = null;
        switch (option) {
            case CLIENT:
                LOGGER.info("Enter client name: ");
                String clientName = Input.stringConsoleInput();
                LOGGER.info("Enter client email: ");
                String clientEmail = Input.stringConsoleInput();
                LOGGER.info("Enter country ID: ");
                Long countryId = Input.longConsoleInput();
                model = new Client(
                        null,
                        clientName,
                        clientEmail,
                        countryId
                );
                break;
            case COUNTRY:
                LOGGER.info("Enter country name: ");
                String countryName = Input.stringConsoleInput();
                LOGGER.info("Enter postal code: ");
                Long postalCode = Input.longConsoleInput();
                model = new Country(
                        null,
                        countryName,
                        postalCode
                );
                break;
            case PROJECT:
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
                model = new Project(
                        null,
                        Timestamp.valueOf(startDate),
                        clientId,
                        Timestamp.valueOf(finishDate),
                        floors,
                        budget,
                        interiorWork,
                        null
                );
                break;
            case SUPPLIER:
                LOGGER.info("Enter supplier name: ");
                String supplierName = Input.stringConsoleInput();
                LOGGER.info("Enter client email: ");
                String supplierEmail = Input.stringConsoleInput();
                LOGGER.info("Enter country ID: ");
                Long countryId1 = Input.longConsoleInput();
                model = new Client(
                        null,
                        supplierName,
                        supplierEmail,
                        countryId1
                );
                break;
            case PROJECTMATERIAL:
                LOGGER.info("Enter supplied material id");
                Long suppliedMaterialId = Input.longConsoleInput();
                LOGGER.info("Enter material amount");
                BigDecimal materialAmount = new BigDecimal(Input.stringConsoleInput());
                LOGGER.info("Enter project Id");
                Long projectId = Input.longConsoleInput();
                LOGGER.info("Enter measure");
                String measure = Input.stringConsoleInput();
                model = new ProjectMaterial(
                        null,
                        suppliedMaterialId,
                        materialAmount,
                        projectId,
                        measure
                );
                break;
        }
        return model;
    }
}
