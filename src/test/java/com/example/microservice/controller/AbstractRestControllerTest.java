package com.example.microservice.controller;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;


/**
 * The AbstractDocTest class is the parent of all JUnit test classes which create Spring REST API.
 * This class configures the test ApplicationContext and test runner environment to facilitate the creation of REST API
 */
@RunWith(SpringRunner.class)
@WebAppConfiguration
public abstract class  AbstractRestControllerTest {
	
	/**
     * A MockMvc instance configured with Spring REST Docs configuration.
     */
    protected transient MockMvc mockMvc;

    @InjectMocks
	ApiResponseEntityExceptionHandler exceptionHandlerController;
    
    /**
     * Perform set up activities before each unit test. Invoked by the JUnit framework.
     */
    @Before
    public void before() {
        doBeforeEachTest(exceptionHandlerController);
    }

    /**
     * Perform initialization tasks before the execution of each test method.
     */
    public abstract void doBeforeEachTest(Object ... controllerAdvices);
    
}
