package com.solvd.construction.service.impl;

import com.solvd.construction.model.Country;
import com.solvd.construction.service.CountryService;
import com.solvd.construction.persistence.mappers.CountryMapper;
import org.apache.ibatis.session.SqlSession;

import java.util.Optional;

public class MBCountryServiceImpl implements CountryService {
    private final SqlSession session;
    private final CountryMapper countryMapper;

    public MBCountryServiceImpl(SqlSession session) {
        this.session = session;
        this.countryMapper = session.getMapper(CountryMapper.class);
    }

    @Override
    public Country create(Country country) {
        country.setId(null);
        countryMapper.create(country);
        return country;
    }

    @Override
    public Optional<Country> retrieveById(Long id) {
        return Optional.of(countryMapper.retrieveById(id));
    }

    @Override
    public Optional<Country> retrieveByCountryName(String countryName) {
        return Optional.of(countryMapper.retrieveByCountryName(countryName));
    }
}
