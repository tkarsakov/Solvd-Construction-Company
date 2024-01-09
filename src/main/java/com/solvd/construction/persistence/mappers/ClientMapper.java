package com.solvd.construction.persistence.mappers;

import com.solvd.construction.model.Client;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ClientMapper {
    @Insert("INSERT INTO clients(client_name, client_email, country_id) " +
            "VALUES (#{clientName}, #{clientEmail}, #{countryId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void create(Client client);

    @Select("SELECT * FROM clients WHERE id = #{id}")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "clientName", column = "client_name"),
            @Result(property = "clientEmail", column = "client_email"),
            @Result(property = "countryId", column = "country_id")
    })
    Client retrieveById(Long id);

    @Select("SELECT * FROM clients")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "clientName", column = "client_name"),
            @Result(property = "clientEmail", column = "client_email"),
            @Result(property = "countryId", column = "country_id")
    })
    List<Client> retrieveAll();

    @Update("UPDATE clients " +
            "SET client_name = #{clientName}, client_email = #{clientEmail}, country_id = #{countryId}" +
            " WHERE id = #{id}")
    void update(Client client);

    @Delete("DELETE FROM clients WHERE id = #{id}")
    void delete(Long id);
}
