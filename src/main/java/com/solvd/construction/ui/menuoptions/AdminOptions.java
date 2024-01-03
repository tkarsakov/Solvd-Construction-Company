package com.solvd.construction.ui.menuoptions;

public enum AdminOptions {
    PARSE("parse - Parse markup file into database"),
    CREATE("create - Create an object from console"),
    READ("read - Show all objects of specified type"),
    UPDATE("update - Update a database entry by id"),
    DELETE("delete - Delete a database entry by id"),
    BACK("back - Go back to previous menu"),
    EXIT("exit - Terminate program");
    final String description;

    AdminOptions(String description) {
        this.description = description;
    }

    public static String getOptions() {
        StringBuilder stringBuilder = new StringBuilder();
        for (var option : AdminOptions.values()) {
            stringBuilder.append(option.description)
                    .append("\n");
        }
        return stringBuilder.toString();
    }
}
