package org.ez.vk.vk.ui.command;

import org.ez.vk.vk.ui.command.account.AccountCommand;
import org.ez.vk.vk.ui.command.find.FindCommand;
import org.ez.vk.vk.ui.command.task.AddComment;

public class MainCommand extends RootCommand {

	public MainCommand(){
		listCommands.add(new AccountCommand());
		listCommands.add(new FindCommand());
		listCommands.add(new AddComment());
	}
	@Override
	protected String getCommandName() {
		return "main";
	}

}
