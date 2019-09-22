/**
 * 
 */
package com.example.microservice.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.microservice.model.Employee;
import com.google.gson.Gson;

import lombok.extern.slf4j.Slf4j;

/**
 * @author govindarajuv
 *
 */
//@RunWith(SpringRunner.class)
//@EnableWebMvc
//@AutoConfigureMockMvc
//@TestPropertySource("classpath:test-application-context.properties")
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = {TestContext.class, WebAppContext.class})
//@WebAppConfiguration

@Slf4j
//@WebMvcTest(controllers = EmployeeController.class,useDefaultFilters = false)
public class EmployeeControllerTest extends AbstractRestControllerTest{

	private static final String STATEMENT_BASE_URL = "/api/v1/employees";

	
	@InjectMocks
	EmployeeController employeeController;
	//@InjectMocks
	//ApiResponseEntityExceptionHandler exceptionHandlerController;
	
	@Test
	public void getEmployeeURLNotFound() throws Exception {
		mockMvc.perform(
				MockMvcRequestBuilders.get(STATEMENT_BASE_URL + "/notfound"))
				.andDo(print()).andExpect(status().isNotFound());
	}
	
	@Test
	public void getEmployeeOneMatchName() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get(STATEMENT_BASE_URL+"/testOk").accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("emp1"))).andDo(print())
				.andExpect(status().isOk());	
	}
	
	
	@Test
	public void getEmployeeException() throws Exception {
		Employee emp = new Employee();
		emp.setName("emp1");
		emp.setDesignation("manager");
		emp.setEmpId("1");
		emp.setSalary(3000);
		
		mockMvc.perform(MockMvcRequestBuilders.get(STATEMENT_BASE_URL+"/exception").accept(MediaType.APPLICATION_JSON_VALUE))
				.andDo(print())
				.andExpect(status().isInternalServerError());
	}
	
	@Test
	public void getEmployeeCustException() throws Exception {
		Employee emp = new Employee();
		emp.setName("emp1");
		emp.setDesignation("manager");
		emp.setEmpId("1");
		emp.setSalary(3000);
		
		mockMvc.perform(MockMvcRequestBuilders.get(STATEMENT_BASE_URL+"/t24customException"))
				.andDo(print())
				.andExpect(status().isBadRequest());
	}
	
	
	
	@Test
	public void getEmployeeOneMatchBean() throws Exception {
		Employee emp = new Employee();
		emp.setName("emp1");
		emp.setDesignation("manager");
		emp.setEmpId("1");
		emp.setSalary(3000);
		
		mockMvc.perform(MockMvcRequestBuilders.get(STATEMENT_BASE_URL+"/testOk").accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("emp1"))).andDo(print())
				.andExpect(status().isOk());
	}
	
	@Test
	public void getAllEmployees() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get(STATEMENT_BASE_URL).accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(MockMvcResultMatchers.jsonPath("$.length()", Matchers.is(1))).andDo(print())
				.andExpect(status().isOk());
	}
	
	@Test
	public void updateEmployeeMediaTypeNotFoundTest() throws Exception {
		Employee emp = new Employee();
		emp.setName("emp1");
		emp.setDesignation("manager");
		emp.setEmpId("1");
		emp.setSalary(3000);
		
		 String jsonReqBody = asJsonString(emp);
		 mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/employees/putJson")
					.contentType(MediaType.APPLICATION_XML_VALUE).content(jsonReqBody))
					.andDo(print())
					.andExpect(status().isBadRequest());
		
	}
	@Test
	public void updateEmployeeOk() throws Exception {
		log.info("-----------------updateEmployeeOk-------------------------\n\n");
		
		Employee emp = new Employee();
		emp.setName("emp1");
		emp.setDesignation("manager");
		emp.setEmpId("1");
		emp.setSalary(3000);
		
	    String jsonReqBody = asJsonString(emp);
	    		
		mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/employees/putJson")
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(jsonReqBody))
				.andDo(print())
				.andExpect(status().isOk());
	}
	@Test
	public void updateEmployeeBadRequestParseError() throws Exception {
		Employee emp = new Employee();
		emp.setName("emp1");
		emp.setDesignation("manager");
		emp.setEmpId("1");
		emp.setSalary(3000);
		
		mockMvc.perform(MockMvcRequestBuilders.put(STATEMENT_BASE_URL+"/putJson").contentType(MediaType.APPLICATION_JSON_VALUE).content("{user:'d'}"))
				.andDo(print())
				.andExpect(status().isBadRequest());
	}
	
	@Override
	public void doBeforeEachTest(Object ... controllerAdvices) {
		mockMvc = MockMvcBuilders.standaloneSetup(employeeController).setControllerAdvice(controllerAdvices).build();
	}
	
	
	//
	public static String asJsonString(final Object obj) {
	    try {
		    return new Gson().toJson(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
	
}
