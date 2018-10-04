package org.ez.vk.ui;

import java.io.IOException;

import org.ez.vk.dao.common.api.vk.dao.IAccountDao;
import org.ez.vk.service.entity.AccountServiceDTO;
import org.ez.vk.service.impl.accountservice.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;


@ContextConfiguration(locations = "classpath:service-context.xml")
public class Runner {
	@Autowired
	private static IAccountDao accountDao;

	public static void main(String[] args) throws JsonGenerationException, JsonMappingException, IOException, ApiException, ClientException {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("ui-context.xml");
		AccountService accountService = (AccountService) context.getBean("accountService");
		AccountServiceDTO accountServiceDTO = new AccountServiceDTO("+375297844586","rjkjrjkf19977", "woking");
	
		String responce = accountService.addAccount(accountServiceDTO);
		
		System.out.println(responce);
 		int a = 2;
		a++;
	
	}

}
