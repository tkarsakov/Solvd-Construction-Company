package com.solvd.construction.persistence.mappers;

import com.solvd.construction.model.Country;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface CountryMapper {
    @Insert("INSERT INTO countries(country_name, postal_code) VALUES (#{countryName}, #{postalCode})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void create(Country country);

    @Select("SELECT * FROM countries WHERE id = #{id}")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "countryName", column = "country_name"),
            @Result(property = "postalCode", column = "postal_code")
    })
    Country retrieveById(Long id);

    Country retrieveByCountryName(String countryName);

    @Select("SELECT * FROM countries")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "countryName", column = "country_name"),
            @Result(property = "postalCode", column = "postal_code")
    })
    List<Country> retrieveAll();

    @Update("UPDATE countries SET country_name = #{countryName}, postal_code = #{postalCode} WHERE id = #{id}")
    void update(Country country);

    @Delete("DELETE FROM countries WHERE id = #{id}")
    void delete(Long id);
}
