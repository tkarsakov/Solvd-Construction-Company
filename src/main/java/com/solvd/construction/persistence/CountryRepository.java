package com.solvd.construction.persistence;

import com.solvd.construction.model.Country;

import java.util.List;
import java.util.Optional;

public interface CountryRepository {
    void create(Country country);

    Optional<Country> findById(Long id);

    List<Country> findAll();

    void update(Country country);

    void deleteById(Long id);
}
