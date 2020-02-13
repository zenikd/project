package org.ez.vk.ui.command.task;

import org.ez.vk.task.FinderPopularPost;
import org.ez.vk.ui.command.RootCommand;
import org.ez.vk.ui.command.common.ConsoleHelper;

public class FindPopularPost extends RootCommand
{
	protected void execute() {
		try {
			FinderPopularPost nakrutCommentImpl;
			nakrutCommentImpl = (FinderPopularPost) context.getBean("finderPopularPostImpl");
			print("Write tag");
			String tag = ConsoleHelper.writeText();
			nakrutCommentImpl.getListPost(tag);
			print("OK");
			} catch (Exception e) {
				print(e.getMessage());
			}
		
		
		
		
	}

	@Override
	protected String getCommandName() {
		return "find popular post";
	}

}
