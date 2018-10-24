package org.ez.vk.ui.command;

import org.ez.vk.ui.command.account.AccountCommand;
import org.ez.vk.ui.command.find.FindCommand;
import org.ez.vk.ui.command.task.AddComment;
import org.ez.vk.ui.command.task.CountUnicPost;
import org.ez.vk.ui.command.task.EarnPoint;

public class MainCommand extends RootCommand {

	public MainCommand(){
		listCommands.add(new AccountCommand());
		listCommands.add(new FindCommand());
		listCommands.add(new AddComment());
		listCommands.add(new CountUnicPost());
		listCommands.add(new EarnPoint());
	}
	@Override
	protected String getCommandName() {
		return "main";
	}

}
