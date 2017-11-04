package com.momenton.coding.employee.repository;

import com.momenton.coding.employee.model.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    public Employee findEmployeeByName(String name);
}
