package com.solvd.construction.xml;

import com.solvd.construction.model.Model;

public interface ModelHandler<T extends Model> {
    String END_TAG = "END";

    T getModel();
}
