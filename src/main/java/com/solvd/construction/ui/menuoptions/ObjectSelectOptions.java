package com.solvd.construction.ui.menuoptions;

public enum ObjectSelectOptions {
    PROJECT("project - Project class from projects table"),
    PROJECTMATERIAL("projectmaterial - Project Material class from project_materials table"),
    CLIENT("client - Client class from clients table"),
    SUPPLIER("supplier - Supplier class from suppliers table"),
    COUNTRY("country - Country class from countries table");
    final String description;

    ObjectSelectOptions(String description) {
        this.description = description;
    }

    public static String getOptions() {
        StringBuilder stringBuilder = new StringBuilder();
        for (var option : ObjectSelectOptions.values()) {
            stringBuilder
                    .append("\n")
                    .append(option.description)
                    .append("\n");
        }
        return stringBuilder.toString();
    }

}
