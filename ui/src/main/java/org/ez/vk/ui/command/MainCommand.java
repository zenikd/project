package org.ez.vk.ui.command;

import org.ez.vk.ui.command.account.AccountCommand;
import org.ez.vk.ui.command.task.*;

public class MainCommand extends RootCommand {

	public MainCommand(){
		listCommands.add(new AccountCommand());
		listCommands.add(new AdminGetterCommand());
		listCommands.add(new FindGroupByProduct());
		listCommands.add(new FindPopularPost());
		listCommands.add(new SearchGroupCover());
		listCommands.add(new AbadonedGroupGetterCommand());
		listCommands.add(new PopularGroupFinderCommand());
		listCommands.add(new AddGroupFromFile());
	}
	@Override
	protected String getCommandName() {
		return "main";
	}

}
