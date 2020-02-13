package org.ez.vk.ui.command.task;

import org.ez.vk.task.SearcherGroupCover;
import org.ez.vk.ui.command.RootCommand;
import org.ez.vk.ui.command.common.ConsoleHelper;

public class SearchGroupCover extends RootCommand
{
	protected void execute() {
		try {
			SearcherGroupCover nakrutCommentImpl;
			nakrutCommentImpl = (SearcherGroupCover) context.getBean("searcherGroupCoverImpl");
			print("Write tag");
			String tag = ConsoleHelper.writeText();
			nakrutCommentImpl.getListGroup(tag);
			print("OK");
			} catch (Exception e) {
				print(e.getMessage());
			}
		
	}

	@Override
	protected String getCommandName() {
		return "search group cover";
	}

}
