package org.ez.vk.ui.command.find;

import org.ez.vk.ui.command.RootCommand;
import org.ez.vk.ui.command.find.repost.PostToRepostCommand;

public class FindCommand extends RootCommand {
	
	public FindCommand(){
		listCommands.add(new PostToRepostCommand());
	}

	@Override
	protected String getCommandName() {
		return "find";
	}

}
