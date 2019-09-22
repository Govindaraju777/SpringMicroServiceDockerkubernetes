package com.example.microservice;


//@RunWith(PowerMockRunner.class)
//@SpringBootTest
//@RunWith(SpringJUnit4ClassRunner.class)


//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = EmployeeProducerApplication.class)
//@RunWith(SpringRunner.class)

//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)

//@AutoConfigureMockMvc
//@SpringBootTest(properties = "spring.main.web-application-type=reactive")
//@SpringApplicationConfiguration(classes = EmployeeProducerApplication.class)

//@RunWith(PowerMockRunner.class)
//@PrepareForTest({SpringApplication.class, EmployeeProducerApplication.class})
public class EmployeeProducerApplicationTests {

	//@Test
	public void contextLoads() throws Exception {
//		SpringApplication springApplication_Tested = PowerMockito.mock(SpringApplication.class);
//		ConfigurableApplicationContext mockedResult = null;
		String input[] = new String [] { "Hello", "World" };
//		Mockito.when(springApplication_Tested.run(EmployeeProducerApplication.class, input)).thenReturn(mockedResult);
//		EmployeeProducerApplication.main(input);
//		Assert.assertTrue(true);
		
		
		/*
		 * ConfigurableApplicationContext configurableApplicationContextMock =
		 * Mockito.mock(ConfigurableApplicationContext.class);
		 * Mockito.when(SpringApplication.run(EmployeeProducerApplication.class,
		 * input)).thenReturn(configurableApplicationContextMock);
		 * PowerMockito.doNothing().when(EmployeeProducerApplication.class);
		 * PowerMockito.mockStatic(EmployeeProducerApplication.class);
		 * EmployeeProducerApplication.main(input);
		 * 
		 * Mockito.verify(configurableApplicationContextMock).close();
		 */
		
	}

//	private MockMvc mockMvc;
//	 
//	 @Before
//	 public void setup() {
//		 this.mockMvc = MockMvcBuilders.standaloneSetup(new HomeController()).build();
//	 }
//	 
//	@Test
//	public void testSayHelloWorld() throws Exception {
//		this.mockMvc.perform(get("/"))
//				.andExpect(status().isOk());
//		// .andExpect(content().contentType("application/json"));
//	}
	
}
