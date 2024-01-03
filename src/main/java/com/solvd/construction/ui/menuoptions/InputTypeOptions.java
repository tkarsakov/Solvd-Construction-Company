package com.solvd.construction.ui.menuoptions;

public enum InputTypeOptions {
    XML("xml - Parse an xml file"),
    CONSOLE("console - Type in fields through console"),
    BACK("back - Go back to previous menu");
    final String description;

    InputTypeOptions(String description) {
        this.description = description;
    }

    public static String getOptions() {
        StringBuilder stringBuilder = new StringBuilder();
        for (var option : InputTypeOptions.values()) {
            stringBuilder
                    .append("\n")
                    .append(option.description)
                    .append("\n");
        }
        return stringBuilder.toString();
    }
}
