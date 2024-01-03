package com.solvd.construction.ui.menuoptions;

public enum DaoOptions {
    JDBC("jdbc - Use JDBC implementation"),
    MYBATIS("mybatis - Use My Batis implementation"),
    EXIT("exit - Terminate application");
    final String description;

    DaoOptions(String description) {
        this.description = description;
    }

    public static String getOptions() {
        StringBuilder stringBuilder = new StringBuilder();
        for (var option : DaoOptions.values()) {
            stringBuilder
                    .append("\n")
                    .append(option.description)
                    .append("\n");
        }
        return stringBuilder.toString();
    }
}
