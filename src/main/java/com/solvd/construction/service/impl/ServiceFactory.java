package com.solvd.construction.service.impl;

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
    private final String IMPLEMENTATION;
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
        this.IMPLEMENTATION = implementation;
    }

    public ClientService getClientService() {
        if (IMPLEMENTATION.equals("mybatis")) {
            return new MBClientServiceImpl(sessionFactory);
        } else {
            return new ClientServiceImpl();
        }
    }

    public CountryService getCountryService() {
        if (IMPLEMENTATION.equals("mybatis")) {
            return new MBCountryServiceImpl(sessionFactory);
        } else {
            return new CountryServiceImpl();
        }
    }

    public EmployeeService getEmployeeService() {
        if (IMPLEMENTATION.equals("mybatis")) {
            return new MBEmployeeServiceImpl(sessionFactory);
        } else {
            return new EmployeeServiceImpl();
        }
    }

    public MaterialNameService getMaterialNameService() {
        if (IMPLEMENTATION.equals("mybatis")) {
            return new MBMaterialNameServiceImpl(sessionFactory);
        } else {
            return new MaterialNameServiceImpl();
        }
    }

    public PositionService getPositionService() {
        if (IMPLEMENTATION.equals("mybatis")) {
            return new MBPositionServiceImpl(sessionFactory);
        } else {
            return new PositionServiceImpl();
        }
    }

    public ProjectMaterialService getProjectMaterialService() {
        if (IMPLEMENTATION.equals("mybatis")) {
            return new MBProjectMaterialServiceImpl(sessionFactory);
        } else {
            return new ProjectMaterialServiceImpl();
        }
    }

    public ProjectService getProjectService() {
        if (IMPLEMENTATION.equals("mybatis")) {
            return new MBProjectServiceImpl(sessionFactory);
        } else {
            return new ProjectServiceImpl();
        }
    }

    public SuppliedMaterialService getSuppliedMaterialService() {
        if (IMPLEMENTATION.equals("mybatis")) {
            return new MBSuppliedMaterialServiceImpl(sessionFactory);
        } else {
            return new SuppliedMaterialServiceImpl();
        }
    }

    public SupplierService getSupplierService() {
        if (IMPLEMENTATION.equals("mybatis")) {
            return new MBSupplierServiceImpl(sessionFactory);
        } else {
            return new SupplierServiceImpl();
        }
    }
}
