package org.ez.vk.ui.command.task;

import org.ez.vk.task.PopularGroupFinder;
import org.ez.vk.ui.command.RootCommand;
import org.ez.vk.ui.command.common.ConsoleHelper;

public class PopularGroupFinderCommand extends RootCommand
{
	protected void execute() {
		try {
			PopularGroupFinder popularGroupFinder;
			popularGroupFinder = (PopularGroupFinder) context.getBean("popularGroupFinderImpl");
			print("Write tag");
			String tag = ConsoleHelper.readLine();
			popularGroupFinder.getPopularGroup(tag);
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
