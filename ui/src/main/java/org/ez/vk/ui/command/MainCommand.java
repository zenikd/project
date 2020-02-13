package org.ez.vk.ui.command;

import org.ez.vk.ui.command.account.AccountCommand;
import org.ez.vk.ui.command.task.*;

public class MainCommand extends RootCommand {

	public MainCommand(){
		listCommands.add(new AccountCommand());
		listCommands.add(new AddComment());
		listCommands.add(new EarnPoint());
		listCommands.add(new AdminGetterCommand());
		listCommands.add(new FindGroupByProduct());
		listCommands.add(new FindPopularGroup());
		listCommands.add(new FindPopularPost());
		listCommands.add(new SearchGroupCover());
		listCommands.add(new AbadonedGroupGetterCommand());
	}
	@Override
	protected String getCommandName() {
		return "main";
	}

}
