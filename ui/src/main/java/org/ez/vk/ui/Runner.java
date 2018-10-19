package org.ez.vk.ui;

import java.io.IOException;

import org.ez.db.api.dao.IAccountDao;
import org.ez.vk.dao.common.entity.vk.search.reserved.AccountSearchDTO;
import org.ez.db.impl.reservable.AccountDaoImpl;
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
		AccountDaoImpl accountDao = (AccountDaoImpl) context.getBean("accountDao");

	
		accountDao.select(new AccountSearchDTO());
		
	
 		int a = 2;
		a++;
	
	}

}
