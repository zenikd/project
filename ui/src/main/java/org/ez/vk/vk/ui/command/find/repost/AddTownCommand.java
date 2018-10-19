package org.ez.vk.vk.ui.command.find.repost;

import java.io.IOException;

<<<<<<< HEAD:ui/src/main/java/org/ez/vk/ui/command/find/repost/AddTownCommand.java
import org.ez.vk.service.api.IRepostTask;
import org.ez.vk.ui.command.RootCommand;
import org.ez.vk.ui.command.common.ConsoleHelper;
=======

import org.ez.vk.vk.ui.command.RootCommand;
import org.ez.vk.vk.ui.command.common.ConsoleHelper;
>>>>>>> origin/master:ui/src/main/java/org/ez/vk/vk/ui/command/find/repost/AddTownCommand.java

public class AddTownCommand extends RootCommand
{
	private final static String WRITE_TOWN ="write town";
	protected void execute() {
		IRepostTask repostTask;
		repostTask = (IRepostTask) context.getBean("repostTask");
		print(WRITE_TOWN);
		String town;
		try {
			town = ConsoleHelper.writeText();
			repostTask.addNewGroupToFound(town);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	protected String getCommandName() {
		return "add town";
	}

}
