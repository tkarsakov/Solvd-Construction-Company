package com.solvd.construction.persistence.mappers;

import com.solvd.construction.model.Client;
import org.apache.ibatis.annotations.*;

public interface ClientMapper {
    @Insert("INSERT INTO clients(client_name, client_email, country_id) " +
            "VALUES (#{clientName}, #{clientEmail}, #{countryId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Client create(Client client);

    @Select("SELECT * FROM clients WHERE id = #{id}")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "clientName", column = "client_name"),
            @Result(property = "clientEmail", column = "client_email"),
            @Result(property = "countryId", column = "country_id")
    })
    Client retrieveById(Long id);
}
