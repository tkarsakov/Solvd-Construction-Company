package com.solvd.construction.ui.menuoptions;

public enum ModeSelectOptions {
    ADMIN("admin - Admin operations on database"),
    USER("user - User operations with database info"),
    EXIT("exit - Terminate program");
    final String description;

    ModeSelectOptions(String description) {
        this.description = description;
    }

    public static String getOptions() {
        StringBuilder stringBuilder = new StringBuilder();
        for (var option : ModeSelectOptions.values()) {
            stringBuilder.append(option.description)
                    .append("\n");
        }
        return stringBuilder.toString();
    }
}
