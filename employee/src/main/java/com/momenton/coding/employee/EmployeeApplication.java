package com.momenton.coding.employee;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.momenton.coding.employee.model.Employee;
import com.momenton.coding.employee.repository.EmployeeRepository;

@SpringBootApplication
public class EmployeeApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(EmployeeRepository repository) {
		return (args) -> {
			// save a couple of employees
			Employee director = new Employee("Director", null);
			Employee manager1 = new Employee("Manager1", director);
			Employee manager2 = new Employee("Manager2", director);
			Employee employee1 = new Employee("Employee1", manager1);
			Employee employee2 = new Employee("Employee2", manager2);
			Employee employee3 = new Employee("Employee1", null);

			repository.save(director);
			repository.save(manager1);
			repository.save(manager2);
			repository.save(employee1);
			repository.save(employee2);
			repository.save(employee3);
		};
	}
}
