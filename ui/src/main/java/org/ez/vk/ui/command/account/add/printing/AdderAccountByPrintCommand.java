package org.ez.vk.ui.command.account.add.printing;

import java.util.ArrayList;
import java.util.List;

import org.ez.vk.entity.AccountServiceDTO;
import org.ez.vk.service.AccountService;
import org.ez.vk.ui.command.RootCommand;
import org.ez.vk.ui.command.common.ConsoleHelper;

public class AdderAccountByPrintCommand extends RootCommand{
	private final static String WRITE_LOGIN = "write login";
	private final static String WRITE_PASS = "write pass";
	private final static String WRITE_TYPE = "write type";
	
	private List<String> listType = new ArrayList();	
	
	public AdderAccountByPrintCommand(){
		listType.add("admin");
		listType.add("wo");
		
		
	}
	
	
	protected void execute() {
		try {
		AccountService accountService = (AccountService) context.getBean("accountServiceImpl");
		print(WRITE_LOGIN);
		String login = ConsoleHelper.writeText();
		print(WRITE_PASS);
		String pass = ConsoleHelper.writeText();
		print(WRITE_TYPE);
		String type = ConsoleHelper.chooseOneFromList(listType);
		AccountServiceDTO accountSearchDTO = new AccountServiceDTO(login, pass, type);		
		accountService.addAccount(accountSearchDTO);
		print("OK");
		} catch (Exception e) {
			print(e.getMessage());
		}
	}
	

	@Override
	protected String getCommandName() {
		return "print";
	}
}
