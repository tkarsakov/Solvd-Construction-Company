package com.solvd.construction.service.factory;

import com.solvd.construction.service.*;
import com.solvd.construction.service.impl.*;

public class JdbcServiceFactory extends BaseServiceFactory {

    @Override
    public EmployeeService getEmployeeService() {
        return new EmployeeServiceImpl();
    }

    @Override
    public ProjectService getProjectService() {
        return new ProjectServiceImpl();
    }

    @Override
    public ClientService getClientService() {
        return new ClientServiceImpl();
    }

    @Override
    public SupplierService getSupplierService() {
        return new SupplierServiceImpl();
    }

    @Override
    public ProjectMaterialService getProjectMaterialService() {
        return new ProjectMaterialServiceImpl();
    }

    @Override
    public MaterialNameService getMaterialNameService() {
        return new MaterialNameServiceImpl();
    }

    @Override
    public CountryService getCountryService() {
        return new CountryServiceImpl();
    }

    @Override
    public PositionService getPositionService() {
        return new PositionServiceImpl();
    }

    @Override
    public SuppliedMaterialService getSuppliedMaterialService() {
        return new SuppliedMaterialServiceImpl();
    }
}
