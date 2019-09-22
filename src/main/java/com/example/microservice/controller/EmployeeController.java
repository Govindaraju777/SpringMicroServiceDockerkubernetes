package com.example.microservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.microservice.exception.EmployeeException;
import com.example.microservice.model.Employee;
import com.example.microservice.model.EmployeeRequestDTO;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/api/v1/employees")
@Slf4j
public class EmployeeController {
	
	
	//test bean util mapper
//	@RequestMapping(value = "/employee-request", method = RequestMethod.POST)
//	public Employee empReqEntityMapTest(@RequestBody EmployeeRequestDTO employeeRequestEntity) {
//		log.info("---------------");
//		Employee empResponse = new Employee();
//		BeanUtils.copyProperties(employeeRequestEntity, empResponse);
//		return empResponse;
//	}
	
	@RequestMapping(value = "/employee", method = RequestMethod.GET)
	public Employee firstPage() {

		Employee emp = new Employee();
		emp.setName("emp1");
		emp.setDesignation("manager");
		emp.setEmpId("1");
		emp.setSalary(3000);

		return emp;
	}
	
	@GetMapping(value="/t24customException")
	public boolean testT24Custom() throws EmployeeException {
		throw new EmployeeException("test");
	}
	@GetMapping(value = "/exception",produces = MediaType.APPLICATION_JSON_VALUE)
	public Employee empException() throws Exception {
		Employee emp = new Employee();
		emp.setName("emp1");
		emp.setDesignation("manager");
		emp.setEmpId("1");
		emp.setSalary(3000);
		throw new Exception();
	}
	
	@GetMapping
	public List<Employee> allEmployees() {
		List<Employee> employees=new ArrayList<>();
		employees.add(new Employee("emp1","manager","1",3000));
		return employees;
	}
	
	@GetMapping(value = "/testOk",produces = MediaType.APPLICATION_JSON_VALUE)
	public Employee oneEMployee() throws Exception {
		Employee emp = new Employee();
		emp.setName("emp1");
		emp.setDesignation("manager");
		emp.setEmpId("1");
		emp.setSalary(3000);
		return emp;
	}
	@PutMapping(value="/putJson",consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> updateEmployee(@RequestBody Employee emp) {
		log.info("---------------");
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	
	
}
