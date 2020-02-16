package org.ez.vk.ui.command.task;

import org.ez.vk.exception.internal.InternalException;
import org.ez.vk.task.AdminGetter;
import org.ez.vk.task.impl.AdminGetterImpl;
import org.ez.vk.ui.command.RootCommand;
import org.ez.vk.ui.command.common.ConsoleHelper;

public class AdminGetterCommand extends RootCommand
{
	protected void execute() {
		AdminGetter adminGetter = (AdminGetterImpl) context.getBean("adminGetterImpl");
		Integer count;
		try {
			print("Write tag");
			String tag = ConsoleHelper.readLine();
			adminGetter.getListAdmins(tag);
			print("OK");
		} catch (InternalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	protected String getCommandName() {
		return "Get admin";
	}

}
