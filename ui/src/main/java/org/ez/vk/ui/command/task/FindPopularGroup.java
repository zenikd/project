package org.ez.vk.ui.command.task;

import org.ez.vk.task.FinderPopularGroup;
import org.ez.vk.ui.command.RootCommand;
import org.ez.vk.ui.command.common.ConsoleHelper;

public class FindPopularGroup extends RootCommand
{
	protected void execute() {
		try {
			FinderPopularGroup finderPopularGroup;
			finderPopularGroup = (FinderPopularGroup) context.getBean("finderPopularGroupImpl");
			print("Write tag");
			String tag = ConsoleHelper.readLine();
			finderPopularGroup.getListGroup(tag);
			print("OK");
			} catch (Exception e) {
				print(e.getMessage());
			}
		
	}

	@Override
	protected String getCommandName() {
		return "find popular group";
	}

}
