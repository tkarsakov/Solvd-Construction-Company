package com.solvd.construction.ui.menuoptions;

public enum JsonOptions {
    PROJECT("project - Project class from projects table"),
    EMPLOYEE("employee - Employee class from employees table"),
    CLIENT("client - Client class from clients table"),
    SUPPLIER("supplier - Supplier class from suppliers table"),
    COUNTRY("country - Country class from countries table");
    final String description;

    JsonOptions(String description) {
        this.description = description;
    }

    public static String getOptions() {
        StringBuilder stringBuilder = new StringBuilder();
        for (var option : JsonOptions.values()) {
            stringBuilder
                    .append("\n")
                    .append(option.description)
                    .append("\n");
        }
        return stringBuilder.toString();
    }
}
