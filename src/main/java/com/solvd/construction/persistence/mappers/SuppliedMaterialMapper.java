package com.solvd.construction.persistence.mappers;

import com.solvd.construction.model.SuppliedMaterial;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface SuppliedMaterialMapper {
    @Insert("INSERT INTO supplied_materials(material_id, supplier_id, price) VALUES (#{materialId}, #{supplierId}, " +
            "#{price})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void create(SuppliedMaterial suppliedMaterial);

    @Select("SELECT * FROM supplied_materials")
    @Results(value = {
            @Result(column = "id", property = "id"),
            @Result(column = "material_id", property = "materialNameId"),
            @Result(column = "supplier_id", property = "supplierId"),
            @Result(column = "price", property = "price")
    })
    List<SuppliedMaterial> retrieveAll();

    @Select("SELECT * FROM supplied_materials WHERE id = #{id}")
    @Results(value = {
            @Result(column = "id", property = "id"),
            @Result(column = "material_id", property = "materialNameId"),
            @Result(column = "supplier_id", property = "supplierId"),
            @Result(column = "price", property = "price")
    })
    SuppliedMaterial retrieveById(Long id);
}
