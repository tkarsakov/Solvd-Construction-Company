package com.solvd.construction.service.factory;

import com.solvd.construction.model.Model;
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
    public static SqlSessionFactory sessionFactory;

    static {
        String resource = "mybatis-config.xml";
        try (InputStream inputStream = Resources.getResourceAsStream(resource)) {
            sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            LOGGER.fatal("Cannot find mybatis config file");
            System.exit(1);
        }
    }

    private final BaseServiceFactory baseServiceFactory;

    public ServiceFactory(BaseServiceFactory baseServiceFactory) {
        this.baseServiceFactory = baseServiceFactory;
    }

    public <T extends Model> IService<T> getService(Class<T> modelClass) {
        return baseServiceFactory.getService(modelClass);
    }

    public ClientService getClientService() {
        return baseServiceFactory.getClientService();
    }

    public CountryService getCountryService() {
        return baseServiceFactory.getCountryService();
    }

    public EmployeeService getEmployeeService() {
        return baseServiceFactory.getEmployeeService();
    }

    public MaterialNameService getMaterialNameService() {
        return baseServiceFactory.getMaterialNameService();
    }

    public PositionService getPositionService() {
        return baseServiceFactory.getPositionService();
    }

    public ProjectMaterialService getProjectMaterialService() {
        return baseServiceFactory.getProjectMaterialService();
    }

    public ProjectService getProjectService() {
        return baseServiceFactory.getProjectService();
    }

    public SuppliedMaterialService getSuppliedMaterialService() {
        return baseServiceFactory.getSuppliedMaterialService();
    }

    public SupplierService getSupplierService() {
        return baseServiceFactory.getSupplierService();
    }
}
