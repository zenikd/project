package org.ez.vk.ui.command.account.add.printing;

import org.ez.vk.enums.UserTypeEnum;
import org.ez.vk.service.AccountService;
import org.ez.vk.ui.command.RootCommand;
import org.ez.vk.ui.command.common.ConsoleHelper;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public class AdderMultipleAccount extends RootCommand{
	private final static String WRITE_LOGIN = "write login pass array";
	private final static String WRITE_TYPE = "write type";

	private List<String> listType = new ArrayList();

	public AdderMultipleAccount(){
		listType.add(UserTypeEnum.SEARCHER.toString());
		listType.add(UserTypeEnum.WORKING.toString());
	}
	
	
	protected void execute() {
		try {
		AccountService accountService = (AccountService) context.getBean("accountServiceImpl");
		print(WRITE_LOGIN);
		List<String> loginPassList = ConsoleHelper.readMultipleLine();
		print(WRITE_TYPE);
		String type = ConsoleHelper.chooseOneFromList(listType);
		accountService.addListAccount(loginPassList, type);
		print("OK");
		} catch (Exception e) {
			print(e.getMessage());
		}
	}
	

	@Override
	protected String getCommandName() {
		return "multiple";
	}
}
