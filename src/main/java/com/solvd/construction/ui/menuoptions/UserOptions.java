package com.solvd.construction.ui.menuoptions;

public enum UserOptions {
    EARNINGS("earnings - View detailed earnings."),
    PROJECT("project - View total costs associated with a project."),
    BACK("back - Go to previous menu"),
    EXIT("exit - Exit the program");
    final String description;

    UserOptions(String description) {
        this.description = description;
    }

    public static String getOptions() {
        StringBuilder stringBuilder = new StringBuilder();
        for (var option : UserOptions.values()) {
            stringBuilder
                    .append("\n")
                    .append(option.description)
                    .append("\n");
        }
        return stringBuilder.toString();
    }
}
