package com.solvd.construction.persistence.mappers;

import com.solvd.construction.model.Supplier;
import org.apache.ibatis.annotations.*;

public interface SupplierMapper {
    @Insert("INSERT INTO suppliers(supplier_name, supplier_email, country_id) VALUES (#{supplierName}, #{supplierEmail}," +
            "#{countryId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Supplier create(Supplier supplier);

    @Select("SELECT * FROM suppliers WHERE id = #{id}")
    @Results(value = {
            @Result(column = "id", property = "id"),
            @Result(column = "supplier_name", property = "supplierName"),
            @Result(column = "supplier_email", property = "supplierEmail"),
            @Result(column = "country_id", property = "countryId")
    })
    Supplier retrieveById(Long id);

    @Select("SELECT * FROM suppliers WHERE supplier_name = #{supplierName}")
    @Results(value = {
            @Result(column = "id", property = "id"),
            @Result(column = "supplier_name", property = "supplierName"),
            @Result(column = "supplier_email", property = "supplierEmail"),
            @Result(column = "country_id", property = "countryId")
    })
    Supplier retrieveBySupplierName(String supplierName);
}
