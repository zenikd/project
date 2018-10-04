package org.ez.vk.ui.command.account;

import org.ez.vk.ui.command.RootCommand;
import org.ez.vk.ui.command.account.add.AddCommand;


public class AccountCommand extends RootCommand{
	
	public AccountCommand() {
		listCommands.add(new AddCommand());
	}

	@Override
	protected String getCommandName() {
		return "account";
	}

}
