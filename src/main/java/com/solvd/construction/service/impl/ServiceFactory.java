package com.solvd.construction.service.impl;

import com.solvd.construction.model.*;
import com.solvd.construction.service.*;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;

public class ServiceFactory {
    private static final Logger LOGGER = LogManager.getLogger();

    private final Boolean isMyBatis;
    private SqlSessionFactory sessionFactory;

    {
        String resource = "mybatis-config.xml";
        try (InputStream inputStream = Resources.getResourceAsStream(resource)) {
            sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            LOGGER.fatal("Cannot find mybatis config file");
            System.exit(1);
        }
    }

    public ServiceFactory(String implementation) {
        this.isMyBatis = implementation.equals("mybatis");
    }

    @SuppressWarnings("unchecked")
    public <T extends Model> IService<T> getService(Class<T> modelClass) {
        if (modelClass.equals(Project.class)) {
            return (IService<T>) getProjectService();
        } else if (modelClass.equals(Client.class)) {
            return (IService<T>) getClientService();
        } else if (modelClass.equals(Supplier.class)) {
            return (IService<T>) getSupplierService();
        } else if (modelClass.equals(Employee.class)) {
            return (IService<T>) getEmployeeService();
        } else if (modelClass.equals(ProjectMaterial.class)) {
            return (IService<T>) getProjectMaterialService();
        } else if (modelClass.equals(MaterialName.class)) {
            return (IService<T>) getMaterialNameService();
        } else if (modelClass.equals(Country.class)) {
            return (IService<T>) getCountryService();
        } else if (modelClass.equals(Position.class)) {
            return (IService<T>) getPositionService();
        } else if (modelClass.equals(SuppliedMaterial.class)) {
            return (IService<T>) getSuppliedMaterialService();
        }
        return null;
    }

    public ClientService getClientService() {
        if (isMyBatis) {
            return new MBClientServiceImpl(sessionFactory);
        } else {
            return new ClientServiceImpl();
        }
    }

    public CountryService getCountryService() {
        if (isMyBatis) {
            return new MBCountryServiceImpl(sessionFactory);
        } else {
            return new CountryServiceImpl();
        }
    }

    public EmployeeService getEmployeeService() {
        if (isMyBatis) {
            return new MBEmployeeServiceImpl(sessionFactory);
        } else {
            return new EmployeeServiceImpl();
        }
    }

    public MaterialNameService getMaterialNameService() {
        if (isMyBatis) {
            return new MBMaterialNameServiceImpl(sessionFactory);
        } else {
            return new MaterialNameServiceImpl();
        }
    }

    public PositionService getPositionService() {
        if (isMyBatis) {
            return new MBPositionServiceImpl(sessionFactory);
        } else {
            return new PositionServiceImpl();
        }
    }

    public ProjectMaterialService getProjectMaterialService() {
        if (isMyBatis) {
            return new MBProjectMaterialServiceImpl(sessionFactory);
        } else {
            return new ProjectMaterialServiceImpl();
        }
    }

    public ProjectService getProjectService() {
        if (isMyBatis) {
            return new MBProjectServiceImpl(sessionFactory);
        } else {
            return new ProjectServiceImpl();
        }
    }

    public SuppliedMaterialService getSuppliedMaterialService() {
        if (isMyBatis) {
            return new MBSuppliedMaterialServiceImpl(sessionFactory);
        } else {
            return new SuppliedMaterialServiceImpl();
        }
    }

    public SupplierService getSupplierService() {
        if (isMyBatis) {
            return new MBSupplierServiceImpl(sessionFactory);
        } else {
            return new SupplierServiceImpl();
        }
    }
}
