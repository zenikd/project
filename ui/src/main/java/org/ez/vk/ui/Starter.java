package org.ez.vk.ui;

import org.ez.vk.exception.internal.InternalException;
import org.ez.vk.ui.command.MainCommand;

public class Starter {

	public static void main(String[] args) throws InternalException {
		MainCommand mainCommand = new MainCommand();
		mainCommand.openDirectory();
	}

}
