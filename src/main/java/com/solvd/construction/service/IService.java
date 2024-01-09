package com.solvd.construction.service;

import com.solvd.construction.model.Model;

public interface IService<T extends Model> {
     T create(T model);
}
