package org.ez.vk.ui.command.find.repost;

import org.ez.vk.service.api.IRepostTask;
import org.ez.vk.ui.command.RootCommand;

public class FindRepostCommand extends RootCommand {
	protected void execute() {
		IRepostTask repostTask;
		repostTask = (IRepostTask) context.getBean("repostTask");
		repostTask.findPostToRepost("москва", 5);
	}

	@Override
	protected String getCommandName() {
		return "repost";
	}

}
