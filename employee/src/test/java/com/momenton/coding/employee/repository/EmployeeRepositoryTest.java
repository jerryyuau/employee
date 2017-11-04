package com.momenton.coding.employee.repository;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.momenton.coding.employee.model.Employee;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EmployeeRepositoryTest {
 
    @Autowired
    private TestEntityManager entityManager;
 
    @Autowired
    private EmployeeRepository employeeRepository;
 
    // write test cases here
    @Test
    public void testFindEmployeeByName() {
        // save employee
        Employee employee = new Employee("employee", null);
        entityManager.persist(employee);
        entityManager.flush();  
        // get employee
        Employee found = employeeRepository.findEmployeeByName(employee.getName());
        // verify
        assertEquals("employee name is not employee", found.getName(), employee.getName());
        assertNull(found.getManager());
    }
    
    @Test
    public void testFindEmployeeByNameWithManager() {
        // save manager and employee
        Employee manager = new Employee("manager", null);

        Employee employee = new Employee("employee", manager);
        entityManager.persist(manager);
        entityManager.persist(employee);
        entityManager.flush();  
        // get employee
        Employee found = employeeRepository.findEmployeeByName(employee.getName());
        // verify
        assertEquals("employee name is not employee", found.getName(), employee.getName());
        assertEquals("manager name is not employee", found.getManager().getName(), manager.getName());
    }
}