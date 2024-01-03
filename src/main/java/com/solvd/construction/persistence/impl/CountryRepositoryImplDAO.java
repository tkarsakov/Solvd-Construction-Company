package com.solvd.construction.persistence.impl;

import com.solvd.construction.model.Country;
import com.solvd.construction.persistence.CountryRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CountryRepositoryImplDAO extends ModelRepositoryImpl<Country> implements CountryRepository {
    private final int[] FIELD_TYPES = {Types.VARCHAR, Types.BIGINT};
    private final String TABLE_NAME = "countries";
    private final String[] TABLE_COLUMNS = {"country_name", "postal_code"};

    @Override
    public void create(Country country) {
        super.create(country, TABLE_NAME, TABLE_COLUMNS, FIELD_TYPES);
    }

    @Override
    public Optional<Country> findById(Long id) {
        return super.findById(id, TABLE_NAME);
    }

    @Override
    public List<Country> findAll() {
        return super.findAll(TABLE_NAME);
    }

    @Override
    public Optional<Country> findByCountryName(String countryName) {
        return super.findByUniqueVarchar(countryName, TABLE_NAME, TABLE_COLUMNS[1]);
    }

    @Override
    public void update(Country country) {
        super.update(country, TABLE_NAME, TABLE_COLUMNS, FIELD_TYPES);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id, TABLE_NAME);
    }

    @Override
    public Object[] getModelParams(Country country) {
        return new Object[]{country.getCountryName(), country.getPostalCode()};
    }

    @Override
    public Optional<Country> getOptionalOfModel(ResultSet resultSet) throws SQLException {
        return Optional.of(
                new Country(
                        resultSet.getLong(1),
                        resultSet.getString(TABLE_COLUMNS[0]),
                        resultSet.getLong(TABLE_COLUMNS[1]))
        );
    }

    @Override
    public List<Country> getListOfModel(ResultSet resultSet) throws SQLException {
        List<Country> countries = new ArrayList<>();
        while (resultSet.next()) {
            countries.add(
                    new Country(
                            resultSet.getLong(1),
                            resultSet.getString(TABLE_COLUMNS[0]),
                            resultSet.getLong(TABLE_COLUMNS[1]))
            );
        }
        return countries;
    }
}