package org.ez.vk.ui.command.task;

import org.ez.vk.service.GroupService;
import org.ez.vk.service.impl.GroupServiceImpl;
import org.ez.vk.task.PopularGroupFinder;
import org.ez.vk.task.SearcherGroupCover;
import org.ez.vk.ui.command.RootCommand;
import org.ez.vk.ui.command.common.ConsoleHelper;

public class AddGroupFromFile extends RootCommand
{
	protected void execute() {
		try {
			GroupService groupService;
			groupService = (GroupService) context.getBean("groupServiceImpl");
			print("Write tag");
			String tag = ConsoleHelper.readLine();
			groupService.addGroupsFromFile(tag);
			print("OK");
			} catch (Exception e) {
				print(e.getMessage());
			}
		
	}

	@Override
	protected String getCommandName() {
		return "add group from file";
	}

}
