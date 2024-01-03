package com.solvd.construction.persistence.mappers;

import com.solvd.construction.model.MaterialName;
import org.apache.ibatis.annotations.*;

public interface MaterialNameMapper {
    @Insert("INSERT INTO material_names(material_name) VALUES (#{materialName})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    MaterialName create(MaterialName materialName);

    @Select("SELECT * FROM material_names WHERE id = #{id}")
    @Results(value = {
            @Result(column = "id", property = "id"),
            @Result(column = "material_name", property = "materialName")
    })
    MaterialName retrieveById(Long id);

    @Select("SELECT * FROM material_names WHERE material_name = #{materialName}")
    @Results(value = {
            @Result(column = "id", property = "id"),
            @Result(column = "material_name", property = "materialName")
    })
    MaterialName retrieveByMaterialName(String materialName);
}
