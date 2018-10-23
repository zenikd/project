package org.ez.vk.vk.ui.command.task;

import org.ez.vk.exception.internal.InternalException;
import org.ez.vk.task.impl.CountUnicPostTaskImpl;
import org.ez.vk.task.impl.EarnPointTask;
import org.ez.vk.vk.ui.command.RootCommand;

public class EarnPoint extends RootCommand {
	protected void execute() {
		EarnPointTask earnPointTask = (EarnPointTask) context.getBean("earnPointTask");
		try {
			earnPointTask.earn();
		} catch (InternalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	protected String getCommandName() {
		return "earn point";
	}

}
