package org.ez.vk.vk.ui.command.find.repost;

import java.io.IOException;

import org.ez.vk.task.RepostTask;
import org.ez.vk.vk.ui.command.RootCommand;
import org.ez.vk.vk.ui.command.common.ConsoleHelper;

public class AddTownCommand extends RootCommand
{
	private final static String WRITE_TOWN ="write town";
	protected void execute() {
		RepostTask repostTask;
		repostTask = (RepostTask) context.getBean("repostTaskImpl");
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
