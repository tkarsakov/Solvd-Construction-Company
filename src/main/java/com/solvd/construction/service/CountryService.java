package com.solvd.construction.service;

import com.solvd.construction.model.Country;

import java.util.List;
import java.util.Optional;

public interface CountryService extends IService<Country> {
    Country create(Country country);

    Optional<Country> retrieveById(Long id);

    Optional<Country> retrieveByCountryName(String countryName);

    List<Country> retrieveAll();

    void update(Country country);

    void delete(Long id);
}
