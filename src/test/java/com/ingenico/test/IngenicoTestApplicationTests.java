package com.ingenico.test;

import com.ingenico.test.config.DataSourceConfig;
import com.ingenico.test.controller.AccountController;
import com.ingenico.test.service.AccountService;
import com.ingenico.test.vo.Account;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = IngenicoTestApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IngenicoTestApplicationTests {



	/*@Autowired
	private MockMvc mockMvc;


	@MockBean
	AccountService service;

	@MockBean
	DataSourceConfig config;*/

	@LocalServerPort
	private int port;

	TestRestTemplate restTemplate = new TestRestTemplate();

	HttpHeaders headers = new HttpHeaders();

	/**
	 * Testing default get account service
	 * @throws Exception
	 */
	@Test
	public void getAllAccountsTest () throws Exception
	{

		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/account/get"),
				HttpMethod.GET, entity, String.class);

		String expected = "[{\"name\":\"ACC1\",\"balance\":123.40}]";
		JSONAssert.assertEquals(expected, response.getBody(), true);
	}

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}

	/**
	 * Testing create account functionality
	 * @throws JSONException
	 */
	@Test
	public void createAccountTest() throws JSONException {
		Account account = new Account("ACC1",new BigDecimal("234.7"));
		Account account1 = new Account("ACC2", new BigDecimal("1234"));
		HttpEntity<Account> entity = new HttpEntity<Account>(account, headers);
		HttpEntity<Account> entity1 = new HttpEntity<Account>(account1, headers);
		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/account/create"),
				HttpMethod.POST, entity, String.class);
		ResponseEntity<String> response1 = restTemplate.exchange(
				createURLWithPort("/account/create"),
				HttpMethod.POST, entity1, String.class);
		String expected ="{\"status\":\"ERROR\",\"message\":\"Account already available\"}";
		String expected1="{\"status\":\"SUCCESS\",\"message\":\"Account created successfully!\"}";
		JSONAssert.assertEquals(expected,response.getBody(),true);
		JSONAssert.assertEquals(expected1,response1.getBody(),true);

	}




}
