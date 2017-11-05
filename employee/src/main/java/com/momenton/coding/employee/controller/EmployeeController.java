package com.momenton.coding.employee.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.momenton.coding.employee.model.EmployeeObject;
import com.momenton.coding.employee.service.EmployeeService;
@RestController
public class EmployeeController {
	@Autowired
	EmployeeService service;
	
	@RequestMapping("/getemployees")
	public List<EmployeeObject> getAll() {
		return service.findAll();
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/add")
	public void add(@RequestBody EmployeeObject object) {
		service.add(object);
	}
	


@ExceptionHandler(IllegalArgumentException.class)
void handleBadRequests(HttpServletResponse response) throws IOException {
    response.sendError(HttpStatus.BAD_REQUEST.value());
}
}

