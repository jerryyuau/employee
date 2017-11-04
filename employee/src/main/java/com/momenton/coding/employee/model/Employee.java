package com.momenton.coding.employee.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ID;

    private String name;
    @OneToOne(cascade=CascadeType.MERGE)
    private Employee manager;

    public Employee() {
    }

    public Employee(String name, Employee manager) {
    	this.name = name;
    	this.manager = manager;
    }
    public Long getID() {

        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }
}
