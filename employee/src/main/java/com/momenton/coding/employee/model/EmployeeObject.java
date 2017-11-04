package com.momenton.coding.employee.model;
/**
 * objects interacting with frontend
 * @author jerry.yu
 *
 */
public class EmployeeObject {

    private String name;
    private String managerName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManagerName() {
        return managerName;
    }

    public EmployeeObject() {
    }

    public EmployeeObject(String name, String managerName) {
        this.name = name;
        this.managerName = managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String toString() {
        return new StringBuilder("name:").append(name).append(", manager:").append(managerName).toString();
    }
}
