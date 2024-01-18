package com.solvd.construction.service.factory;

import com.solvd.construction.service.*;
import com.solvd.construction.service.impl.*;

public class MyBatisServiceFactory extends BaseServiceFactory {
    @Override
    public ClientService getClientService() {
        return new MBClientServiceImpl(ServiceFactory.sessionFactory);
    }

    @Override
    public CountryService getCountryService() {
        return new MBCountryServiceImpl(ServiceFactory.sessionFactory);
    }

    @Override
    public EmployeeService getEmployeeService() {
        return new MBEmployeeServiceImpl(ServiceFactory.sessionFactory);
    }

    @Override
    public MaterialNameService getMaterialNameService() {
        return new MBMaterialNameServiceImpl(ServiceFactory.sessionFactory);
    }

    @Override
    public PositionService getPositionService() {
        return new MBPositionServiceImpl(ServiceFactory.sessionFactory);
    }

    @Override
    public ProjectMaterialService getProjectMaterialService() {
        return new MBProjectMaterialServiceImpl(ServiceFactory.sessionFactory);
    }

    @Override
    public ProjectService getProjectService() {
        return new MBProjectServiceImpl(ServiceFactory.sessionFactory);
    }

    @Override
    public SuppliedMaterialService getSuppliedMaterialService() {
        return new MBSuppliedMaterialServiceImpl(ServiceFactory.sessionFactory);
    }

    @Override
    public SupplierService getSupplierService() {
        return new MBSupplierServiceImpl(ServiceFactory.sessionFactory);
    }
}
