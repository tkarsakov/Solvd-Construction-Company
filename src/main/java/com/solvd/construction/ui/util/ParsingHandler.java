package com.solvd.construction.ui.util;

import com.solvd.construction.service.factory.ServiceFactory;
import com.solvd.construction.ui.util.parsing.ParsingStrategy;

public class ParsingHandler {
    private final ServiceFactory serviceFactory;

    public ParsingHandler(ServiceFactory serviceFactory) {
        this.serviceFactory = serviceFactory;
    }

    public void parse(ParsingStrategy parsingStrategy) {
        parsingStrategy.parseIntoDatabase(serviceFactory);
    }
}
