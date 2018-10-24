package org.ez.vk.ui.command.account.add;

import org.ez.vk.ui.command.account.add.printing.AdderAccountByPrintCommand;
import org.ez.vk.ui.command.RootCommand;

public class AddCommand extends RootCommand{
	public AddCommand() {
		listCommands.add(new AdderAccountByPrintCommand());
	}

	@Override
	protected String getCommandName() {
		return "add account";
	}
}
