package org.ez.vk.ui.command.task;

import org.ez.vk.task.RepostTask;
import org.ez.vk.task.FinderGroupByProduct;
import org.ez.vk.ui.command.RootCommand;
import org.ez.vk.ui.command.common.ConsoleHelper;

public class FindGroupByProduct extends RootCommand
{
	protected void execute() {
		try {
			FinderGroupByProduct nakrutCommentImpl;
			nakrutCommentImpl = (FinderGroupByProduct) context.getBean("finderGroupByProductImpl");
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
		return "find group by product";
	}

}
