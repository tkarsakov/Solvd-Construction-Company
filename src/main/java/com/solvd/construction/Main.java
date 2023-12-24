package com.solvd.construction;

import com.solvd.construction.persistence.CountryRepository;
import com.solvd.construction.persistence.impl.CountryRepositoryImplDAO;

public class Main {
    public static void main(String[] args) {
        CountryRepository countryRepository = new CountryRepositoryImplDAO();
        countryRepository.findAll().forEach(country -> System.out.println(country.getCountryName()));
    }
}
