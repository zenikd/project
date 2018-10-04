package org.ez.vk.ui.command.account.add;

import org.ez.vk.ui.command.RootCommand;
import org.ez.vk.ui.command.account.add.printing.AdderAccountByPrintCommand;

public class AddCommand extends RootCommand{
	public AddCommand() {
		listCommands.add(new AdderAccountByPrintCommand());
	}

	@Override
	protected String getCommandName() {
		return "add account";
	}
}
