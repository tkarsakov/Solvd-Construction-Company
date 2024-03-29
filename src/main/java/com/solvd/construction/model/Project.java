package com.solvd.construction.model;

import com.solvd.construction.xml.jaxb.adapters.TimestampAdapter;
import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@XmlRootElement(name = "project")
@XmlAccessorType(XmlAccessType.NONE)
public class Project implements Model {

    private final BigDecimal FLOOR_COST = new BigDecimal("50000.00");
    private final BigDecimal INTERIOR_COST_PER_FLOOR = new BigDecimal("10000.00");
    private Long id;
    private Timestamp finishDate;
    private Long clientId;
    private Timestamp startDate;
    @XmlElement(name = "floors")
    private Long floors;
    private BigDecimal budget;
    private Boolean interiorWork;
    private Client client;
    private List<Employee> employeeList;
    private List<ProjectMaterial> projectMaterials;
    private Long deadline;

    private Project() {
        this(null, null, null, null, null, null, null, null, null);
    }

    public Project(Long id, Timestamp finishDate, Long clientId, Timestamp startDate,
                   Long floors, BigDecimal budget, Boolean interiorWork, Long deadline) {
        this.id = id;
        this.startDate = startDate;
        this.clientId = clientId;
        this.finishDate = finishDate;
        this.interiorWork = interiorWork;
        this.floors = floors;
        this.budget = budget;
        this.deadline = deadline;
    }

    public Project(Timestamp startDate, Timestamp finishDate, Client client, List<Employee> employeeList, List<ProjectMaterial> projectMaterials, Long deadline, Long floors, Boolean interiorWork, BigDecimal budget) {
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.client = client;
        this.employeeList = employeeList;
        this.projectMaterials = projectMaterials;
        this.deadline = deadline;
        this.floors = floors;
        this.interiorWork = interiorWork;
        this.budget = budget;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isInteriorWork() {
        return interiorWork;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    @XmlElement
    @XmlJavaTypeAdapter(TimestampAdapter.class)
    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getFinishDate() {
        return finishDate;
    }

    @XmlElement
    @XmlJavaTypeAdapter(TimestampAdapter.class)
    public void setFinishDate(Timestamp finishDate) {
        this.finishDate = finishDate;
    }

    public Long getClientId() {
        return clientId;
    }

    @XmlElement
    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public long getFloors() {
        return floors;
    }

    @XmlElement(name = "floors")
    public void setFloors(Long floors) {
        this.floors = floors;
    }

    public BigDecimal getBudget() {
        return budget;
    }

    @XmlElement
    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }

    public Client getClient() {
        return client;
    }

    @XmlElement(name = "client")
    public void setClient(Client client) {
        this.client = client;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    @XmlElementWrapper(name = "employees")
    @XmlElement(name = "employee")
    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public List<ProjectMaterial> getProjectMaterials() {
        return projectMaterials;
    }

    @XmlElementWrapper(name = "projectMaterials")
    @XmlElement(name = "projectMaterial")
    public void setProjectMaterials(List<ProjectMaterial> projectMaterials) {
        this.projectMaterials = projectMaterials;
    }

    public Long getDeadline() {
        return deadline;
    }

    @XmlElement
    public void setDeadline(Long deadline) {
        this.deadline = deadline;
    }


    @XmlElement
    public void setInteriorWork(Boolean interiorWork) {
        this.interiorWork = interiorWork;
    }

    public BigDecimal calculateEarnings() {
        BigDecimal totalMonthsSalary = getEmployeeSalaries();
        BigDecimal totalMaterialCost = new BigDecimal("0.00");
        for (var projectMaterial : projectMaterials) {
            totalMaterialCost = totalMaterialCost.add(projectMaterial.getMaterialAmount().multiply(projectMaterial.getSuppliedMaterial().getPrice()));
        }
        BigDecimal totalFloorCost = FLOOR_COST.multiply(new BigDecimal(this.floors));
        BigDecimal totalInteriorWork = new BigDecimal("0.00");
        if (this.interiorWork) {
            totalInteriorWork = INTERIOR_COST_PER_FLOOR.multiply(new BigDecimal(this.floors));
        }
        return budget.subtract(totalMonthsSalary).subtract(totalMaterialCost).subtract(totalFloorCost).subtract(totalInteriorWork);
    }

    private BigDecimal getEmployeeSalaries() {
        BigDecimal totalMonthSalary = new BigDecimal("0.00");
        for (var employee : employeeList) {
            totalMonthSalary = totalMonthSalary.add(employee.getPosition().getMonthsSalary());
        }
        return totalMonthSalary;
    }

    public String getCostsString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Project ").append(id)
                .append(" Deadline: ")
                .append(this.getDeadline().toString())
                .append(" Days")
                .append("\n");

        stringBuilder.append("EMPLOYEES: \n");
        for (var employee : employeeList) {
            stringBuilder.append(employee.getFirstName())
                    .append(" ")
                    .append(employee.getLastName())
                    .append(" | Salary: ")
                    .append(employee.getPosition().getMonthsSalary().toString())
                    .append("\n");
        }
        BigDecimal totalMonthsSalary = getEmployeeSalaries();
        stringBuilder.append("Total employee salary per month: ")
                .append(totalMonthsSalary.toString()).append("\n");
        BigDecimal totalSalary = totalMonthsSalary.multiply(new BigDecimal(this.getDeadline() / 30));
        stringBuilder.append("Total employee salary for the project: ")
                .append(totalSalary).append("\n");

        stringBuilder.append("MATERIALS: \n");
        BigDecimal totalMaterialCost = new BigDecimal("0.00");
        for (var projectMaterial : projectMaterials) {
            stringBuilder.append(projectMaterial.getSuppliedMaterial().getMaterialName().getMaterialName());
            stringBuilder.append(" | Amount : ");
            stringBuilder.append(projectMaterial.getMaterialAmount().toString()).append(" ")
                    .append(projectMaterial.getMeasure());
            stringBuilder.append(" | Price :");
            stringBuilder.append(projectMaterial.getSuppliedMaterial().getPrice().toString()).append("\n");
            totalMaterialCost = totalMaterialCost.add(projectMaterial.getMaterialAmount().multiply(projectMaterial.getSuppliedMaterial().getPrice()));
        }
        stringBuilder.append("Total material cost: ").append(totalMaterialCost).append("\n");

        BigDecimal totalFloorCost = FLOOR_COST.multiply(new BigDecimal(this.floors));
        stringBuilder.append("FLOORS: ").append(this.getFloors()).append(" | Total cost: ")
                .append(totalFloorCost).append("\n");

        BigDecimal totalInteriorWork = new BigDecimal("0.00");
        if (this.interiorWork) {
            totalInteriorWork = INTERIOR_COST_PER_FLOOR.multiply(new BigDecimal(this.floors));
            stringBuilder.append("INTERIOR WORK for ").append(this.floors).append(" floors ");
            stringBuilder.append(" | Total: ").append(totalInteriorWork).append("\n");
        }

        stringBuilder.append("______________________________________________________\n");
        stringBuilder.append("TOTAL PROJECT COST: ").append(totalMaterialCost
                .add(totalSalary).add(totalFloorCost).add(totalInteriorWork)).append("\n");
        return stringBuilder.toString();
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", finishDate=" + finishDate.toString() +
                ", clientId=" + clientId +
                ", startDate=" + startDate.toString() +
                ", floors=" + floors +
                ", budget=" + budget +
                ", interiorWork=" + interiorWork +
                "}\n";
    }
}
