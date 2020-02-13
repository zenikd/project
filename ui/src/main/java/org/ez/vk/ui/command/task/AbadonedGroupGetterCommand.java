package org.ez.vk.ui.command.task;

import org.ez.vk.exception.internal.InternalException;
import org.ez.vk.task.AbandonedGroupGetter;
import org.ez.vk.task.AdminGetter;
import org.ez.vk.task.impl.AbandonedGroupGetterImpl;
import org.ez.vk.task.impl.AdminGetterImpl;
import org.ez.vk.ui.command.RootCommand;
import org.ez.vk.ui.command.common.ConsoleHelper;

public class AbadonedGroupGetterCommand extends RootCommand
{
	protected void execute() {
		AbandonedGroupGetter adminGetter = (AbandonedGroupGetterImpl) context.getBean("abandonedGroupGetterImpl");
		Integer count;
		try {
			print("Write tag");
			String tag = ConsoleHelper.writeText();
			adminGetter.getListGroup(tag);
			print("OK");
		} catch (InternalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	protected String getCommandName() {
		return "Get abandoned groups";
	}

}
