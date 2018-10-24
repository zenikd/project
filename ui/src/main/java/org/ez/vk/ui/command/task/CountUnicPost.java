package org.ez.vk.ui.command.task;

import org.ez.vk.task.RepostTask;
import org.ez.vk.task.impl.NakrutCommentImpl;
import org.ez.vk.ui.command.RootCommand;

public class CountUnicPost extends RootCommand
{
	protected void execute() {
		NakrutCommentImpl nakrutCommentImpl;
		nakrutCommentImpl = (NakrutCommentImpl) context.getBean("nakrutCommentImpl");
		nakrutCommentImpl.executeTask();
		
	}

	@Override
	protected String getCommandName() {
		return "comment add";
	}

}
