package com.momenton.coding.employee.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.momenton.coding.employee.model.Employee;
import com.momenton.coding.employee.model.EmployeeObject;
import com.momenton.coding.employee.repository.EmployeeRepository;

@RunWith(SpringRunner.class)
public class EmployeeServiceTest {
	@TestConfiguration
    static class EmployeeServiceTestContextConfiguration {
  
        @Bean
        public EmployeeService employeeService() {
            return new EmployeeService();
        }
    }
    @Autowired
    private EmployeeService employeeService;
 
    @MockBean
    private EmployeeRepository employeeRepository;
    
    @Test
    public void testFindEmployeeByNameNoManager() {
    	Employee employee = new Employee("employee", null);
        
        Mockito.when(employeeRepository.findEmployeeByName(employee.getName()))
          .thenReturn(employee);
        
        String name = "employee";
        EmployeeObject found = employeeService.getEmployeeByName(name);
        assertEquals("employee name is not employee", found.getName(), employee.getName());
        assertEquals("employee mananger name is not empty", found.getManagerName(), "");
     }
    @Test
    public void testFindEmployeeByNameWithManager() {
    	Employee manager = new Employee("manager", null);
    	Employee employee = new Employee("employee", manager);
        
        Mockito.when(employeeRepository.findEmployeeByName(employee.getName()))
          .thenReturn(employee);
        
        String name = "employee";
        EmployeeObject found = employeeService.getEmployeeByName(name);
        assertEquals("employee name is not employee", found.getName(), employee.getName());
        assertEquals("employee mananger name is not manager", found.getManagerName(), "manager");
     }
    
    @Test
    public void testAddEmployeeWithNoManager() {
    	Mockito.when(employeeRepository.findEmployeeByName("")).thenReturn(null);
    	EmployeeObject employee = new EmployeeObject("employee", "");
        
        employeeService.add(employee);
        
     }
    
    @Test
    public void testAddEmployeeWithManagerExists() {
    	Employee manager = new Employee("manager", null);
    	Mockito.when(employeeRepository.findEmployeeByName("manager")).thenReturn(manager);
    	EmployeeObject employee = new EmployeeObject("employee", "manager");
        employeeService.add(employee);
     }
    
    @Test
    public void testAddEmployeeWithManagerNotExists() {
    	Mockito.when(employeeRepository.findEmployeeByName("manager")).thenReturn(null);
    	EmployeeObject employee = new EmployeeObject("employee", "manager");
    	try {
    		employeeService.add(employee);
    		fail("illegalArgumentException is expected to be thrown here");
    	} catch(IllegalArgumentException e) {
    		
    	}
     }
    
    @Test
    public void testAddEmployeeAlreadyExists() {
    	Employee existing = new Employee("employee", null);
    	Mockito.when(employeeRepository.findEmployeeByName("employee")).thenReturn(existing);
    	EmployeeObject employee = new EmployeeObject("employee", "");
    	try {
    		employeeService.add(employee);
    		fail("illegalArgumentException is expected to be thrown here");
    	} catch(IllegalArgumentException e) {
    		
    	}
     }
}