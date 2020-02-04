package org.ez.vk.ui.command.account.add;

import org.ez.vk.ui.command.account.add.printing.AdderAnalaiserAccount;
import org.ez.vk.ui.command.RootCommand;

public class AddCommand extends RootCommand{
	public AddCommand() {
		listCommands.add(new AdderAnalaiserAccount());
	}

	@Override
	protected String getCommandName() {
		return "add account";
	}
}
