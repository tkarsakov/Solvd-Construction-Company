package com.solvd.construction.service.impl.mybatis;

import com.solvd.construction.model.Country;
import com.solvd.construction.persistence.mappers.CountryMapper;
import com.solvd.construction.service.CountryService;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;
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
            session.commit();
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

    @Override
    public List<Country> retrieveAll() {
        try (SqlSession session = sessionFactory.openSession()) {
            CountryMapper countryMapper = session.getMapper(CountryMapper.class);
            return countryMapper.retrieveAll();
        }
    }

    @Override
    public void update(Country country) {
        try (SqlSession session = sessionFactory.openSession()) {
            CountryMapper countryMapper = session.getMapper(CountryMapper.class);
            countryMapper.update(country);
            session.commit();
        }
    }

    @Override
    public void delete(Long id) {
        try (SqlSession session = sessionFactory.openSession()) {
            CountryMapper countryMapper = session.getMapper(CountryMapper.class);
            countryMapper.delete(id);
            session.commit();
        }
    }
}
