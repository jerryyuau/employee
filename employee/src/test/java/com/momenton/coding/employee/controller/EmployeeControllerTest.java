package com.momenton.coding.employee.controller;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.momenton.coding.employee.model.Employee;
import com.momenton.coding.employee.model.EmployeeObject;
import com.momenton.coding.employee.service.EmployeeService;

@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {
 
    @Autowired
    private MockMvc mockMvc;
 
    @MockBean
    private EmployeeService service;
 
    @Test
    public void testGetEmployee() throws Exception {
         
        EmployeeObject employee = new EmployeeObject("employee", "manager");
     
        List<EmployeeObject> allEmployees = Arrays.asList(employee);
     
        Mockito.when(service.findAll()).thenReturn(allEmployees);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/getemployees").accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		String expected = "[{name:employee,managerName:manager}]";

		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
		
    }
}
