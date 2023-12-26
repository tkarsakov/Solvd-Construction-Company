package com.solvd.construction.service.impl;

import com.solvd.construction.model.Country;
import com.solvd.construction.persistence.CountryRepository;
import com.solvd.construction.persistence.impl.CountryRepositoryImplDAO;
import com.solvd.construction.service.CountryService;

import java.util.Optional;

public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;

    public CountryServiceImpl() {
        this.countryRepository = new CountryRepositoryImplDAO();
    }

    @Override
    public Country create(Country country) {
        country.setId(null);
        countryRepository.create(country);
        return country;
    }

    @Override
    public Optional<Country> retrieveById(Long id) {
        return countryRepository.findById(id);
    }

    @Override
    public Optional<Country> retrieveByCountryName(String countryName) {
        return countryRepository.findByCountryName(countryName);
    }
}
