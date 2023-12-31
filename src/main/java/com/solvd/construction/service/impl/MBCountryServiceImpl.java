package com.solvd.construction.service.impl;

import com.solvd.construction.model.Country;
import com.solvd.construction.persistence.mappers.CountryMapper;
import com.solvd.construction.service.CountryService;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.Optional;

public class MBCountryServiceImpl implements CountryService {
    private final SqlSessionFactory sessionFactory;

    public MBCountryServiceImpl(SqlSessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Country create(Country country) {
        try (SqlSession session = sessionFactory.openSession()) {
            CountryMapper countryMapper = session.getMapper(CountryMapper.class);
            country.setId(null);
            countryMapper.create(country);
            return country;
        }
    }

    @Override
    public Optional<Country> retrieveById(Long id) {
        try (SqlSession session = sessionFactory.openSession()) {
            CountryMapper countryMapper = session.getMapper(CountryMapper.class);
            return Optional.of(countryMapper.retrieveById(id));
        }
    }

    @Override
    public Optional<Country> retrieveByCountryName(String countryName) {
        try (SqlSession session = sessionFactory.openSession()) {
            CountryMapper countryMapper = session.getMapper(CountryMapper.class);
            return Optional.of(countryMapper.retrieveByCountryName(countryName));
        }
    }
}
