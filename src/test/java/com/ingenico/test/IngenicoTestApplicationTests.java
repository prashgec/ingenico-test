package com.ingenico.test;

import com.ingenico.test.controller.AccountController;
import com.ingenico.test.service.AccountService;
import com.ingenico.test.vo.Account;
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
import org.springframework.http.MediaType;
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

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class IngenicoTestApplicationTests {

	@Mock
	AccountService accountService;

	@InjectMocks
	AccountController controller = new AccountController(accountService);

	private MockMvc mockMvc;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}



	@Test
	public void getAllAccounts () throws Exception
	{
		Account account= new Account();
		account.setName("ACC1");
		account.setBalance(new BigDecimal("123.4"));
		List<Account> accList= new ArrayList<>();
		accList.add(account);

		Assert.assertEquals(account, controller.getAllAccounts().iterator().next());
	}

}
