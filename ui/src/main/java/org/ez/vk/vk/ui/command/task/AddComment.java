package org.ez.vk.vk.ui.command.task;

import java.io.IOException;

import org.ez.vk.task.RepostTask;
import org.ez.vk.task.impl.NakrutCommentImpl;
import org.ez.vk.vk.ui.command.RootCommand;
import org.ez.vk.vk.ui.command.common.ConsoleHelper;

public class AddComment extends RootCommand
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
