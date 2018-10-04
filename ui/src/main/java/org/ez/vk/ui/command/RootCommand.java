package org.ez.vk.ui.command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.ez.vk.ui.command.common.ConsoleHelper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public abstract class RootCommand {
	protected final static ApplicationContext context = new ClassPathXmlApplicationContext("ui-context.xml");
	protected List<RootCommand> listCommands = new ArrayList();

	private final static String COMMAND_NOT_FOUND = "command not found or not unique";
	private final static String EXIT = "exit";

	public void openDirectory() throws IOException {
		if (listCommands.size() > 0) {
			showCommandNameChilds();
			chooseChildCommand();
		} else {
			execute();
		}
	}

	private void chooseChildCommand() throws IOException {
		boolean isFound = false;
		String command = ConsoleHelper.writeText();
		if (EXIT.startsWith(command)) {
			return;
		}

		for (RootCommand childCommand : listCommands) {
			if (childCommand.getCommandName().startsWith(command)) {
				if (isCommandUnic(command)) {
					isFound = true;
					childCommand.openDirectory();
					openDirectory();
				}
				break;
			}
		}
		if (!isFound) {
			System.out.println(COMMAND_NOT_FOUND);
			openDirectory();
		}
	}

	private boolean isCommandUnic(String command) {
		int countRepeat = 0;
		for (RootCommand childCommand : listCommands) {
			if (childCommand.getCommandName().startsWith(command)) {
				countRepeat++;
				if (countRepeat > 1) {
					return false;
				}
			}
		}
		return true;

	}

	protected void execute() {
		throw new NullPointerException("Not implemented");
	}

	protected abstract String getCommandName();

	private void showCommandName() {
		System.out.println(getCommandName());
	}

	protected void print(String message) {
		System.out.println(message);
	}

	private void showCommandNameChilds() {
		for (RootCommand command : listCommands) {
			command.showCommandName();
		}
		System.out.println(EXIT);
	}

}
