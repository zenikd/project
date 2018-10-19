package org.ez.vk.vk.ui.command.find.repost;


import org.ez.vk.task.RepostTask;
import org.ez.vk.vk.ui.command.RootCommand;

public class FindRepostCommand extends RootCommand
{
	protected void execute() {
		try {
			RepostTask repostTask;
			repostTask = (RepostTask) context.getBean("repostTask");
			repostTask.findPostToRepost("москва", 5);
			print("OK");

		} catch (Exception e) {
			print(e.getMessage());
		}
	}

	@Override
	protected String getCommandName() {
		return "repost";
	}

}
