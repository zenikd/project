package org.ez.vk.ui.command;

import org.ez.vk.ui.command.account.AccountCommand;
import org.ez.vk.ui.command.find.FindCommand;
import org.ez.vk.ui.command.find.repost.FindRepostCommand;

public class MainCommand extends RootCommand {

	public MainCommand(){
		listCommands.add(new AccountCommand());
		listCommands.add(new FindCommand());
	}
	@Override
	protected String getCommandName() {
		return "main";
	}

}
