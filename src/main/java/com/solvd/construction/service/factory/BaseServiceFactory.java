package com.solvd.construction.service.factory;

import com.solvd.construction.model.*;
import com.solvd.construction.service.*;

public abstract class BaseServiceFactory {
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

    public abstract EmployeeService getEmployeeService();

    public abstract ProjectService getProjectService();

    public abstract ClientService getClientService();

    public abstract SupplierService getSupplierService();

    public abstract ProjectMaterialService getProjectMaterialService();

    public abstract MaterialNameService getMaterialNameService();

    public abstract CountryService getCountryService();

    public abstract PositionService getPositionService();

    public abstract SuppliedMaterialService getSuppliedMaterialService();

}
