package com.momenton.coding.employee.service;

import com.momenton.coding.employee.model.Employee;
import com.momenton.coding.employee.model.EmployeeObject;
import com.momenton.coding.employee.repository.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository repository;

    public EmployeeObject getEmployeeByName(String name) {
        Employee employee = repository.findEmployeeByName(name);
        EmployeeObject result = null;
        if(employee != null) {
            String managerName = employee.getManager() != null? employee.getManager().getName(): "";
            result = new EmployeeObject(employee.getName(), managerName);
        }
        return result;
    }

    public List<EmployeeObject> findAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .map(employee -> {
                    String managerName = employee.getManager() != null? employee.getManager().getName(): "";
                    return new EmployeeObject(employee.getName(), managerName);
                }).collect(Collectors.toList());

    }
    /**
     * rules: if manager name is empty, it is allowed to be addded.
     * 		  if manager name doesn't exist, it is not allowed
     * 		  if employee name already exist, it is not allowed
     * @param obj
     */
    public void add(EmployeeObject obj) {
    	Employee manager = repository.findEmployeeByName(obj.getManagerName());
    	// not allowed
    	if(manager == null && !"".equalsIgnoreCase(obj.getManagerName())) {
    		throw new IllegalArgumentException("The manager of " + obj.getName() + "doesn't exist");
    	}
    	Employee employee = repository.findEmployeeByName(obj.getName());
    	if(employee == null) {
    		employee = new Employee(obj.getName(), null);
    	} else {
    		throw new IllegalArgumentException(obj.getName() + "already exist");
    	}
    	employee.setManager(manager);
    	repository.save(employee);
    }
}
