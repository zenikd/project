package org.ez.vk.vk.ui.command.find.repost;

import org.ez.vk.vk.ui.command.RootCommand;

public class PostToRepostCommand extends RootCommand
{
	
	public PostToRepostCommand() {
		listCommands.add(new AddTownCommand());
		listCommands.add(new FindRepostCommand());
	}

	protected String getCommandName() {
		return "post to repost";
	}
}
