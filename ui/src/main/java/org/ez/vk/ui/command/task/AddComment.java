package org.ez.vk.ui.command.task;

import org.ez.vk.exception.internal.InternalException;
import org.ez.vk.task.impl.CountUnicPostTaskImpl;
import org.ez.vk.ui.command.RootCommand;

public class AddComment extends RootCommand
{
	protected void execute() {
		CountUnicPostTaskImpl countUnicPostTaskImpl = (CountUnicPostTaskImpl) context.getBean("countUnicPostTaskImpl");
		Integer count;
		try {
			count = countUnicPostTaskImpl.consider();
			System.out.println(count);
		} catch (InternalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	protected String getCommandName() {
		return "count unic post";
	}

}
