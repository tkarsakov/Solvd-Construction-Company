package com.solvd.construction.service;

import com.solvd.construction.model.Country;

import java.util.Optional;

public interface CountryService {
    Country create(Country country);

    Optional<Country> retrieveById(Long id);

    Optional<Country> retrieveByCountryName(String countryName);
}
