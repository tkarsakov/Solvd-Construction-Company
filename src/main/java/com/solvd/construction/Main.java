package com.solvd.construction;

import com.solvd.construction.persistence.CountryRepository;
import com.solvd.construction.persistence.impl.CountryRepositoryImpl;

public class Main {
    public static void main(String[] args) {
        CountryRepository countryRepository = new CountryRepositoryImpl();
        countryRepository.findAll().forEach(country -> System.out.println(country.getCountryName()));
    }
}
