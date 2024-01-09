package com.solvd.construction.ui.menuoptions;

public enum InputTypeOptions {
    SAX("sax - Parse an xml file using sax"),
    JAXB("jaxb - Parse an xml file using jaxb"),
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
