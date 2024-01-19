package com.solvd.construction.service.impl.jdbc;

import com.solvd.construction.model.Country;
import com.solvd.construction.persistence.CountryRepository;
import com.solvd.construction.persistence.impl.CountryRepositoryImplDAO;
import com.solvd.construction.service.CountryService;

import java.util.List;
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

    @Override
    public List<Country> retrieveAll() {
        return countryRepository.findAll();
    }

    @Override
    public void update(Country country) {
        countryRepository.update(country);
    }

    @Override
    public void delete(Long id) {
        countryRepository.deleteById(id);
    }
}
