package com.solvd.construction.service.impl;

import com.solvd.construction.model.Country;
import org.apache.ibatis.annotations.*;

public interface CountryMapper {
    @Insert("INSERT INTO countries(country_name, postal_code) VALUES (#{countryName}, #{postalCode})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Country create(Country country);

    @Select("SELECT * FROM countries WHERE id = #{id}")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "countryName", column = "country_name"),
            @Result(property = "postalCode", column = "postal_code")
    })
    Country retrieveById(Long id);

    Country retrieveByCountryName(String countryName);
}
