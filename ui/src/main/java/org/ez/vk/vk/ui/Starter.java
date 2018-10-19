package org.ez.vk.vk.ui;

import org.ez.vk.vk.dao.common.exception.internal.InternalException;
import org.ez.vk.vk.ui.command.MainCommand;

;

public class Starter {

	public static void main(String[] args) throws InternalException {
		MainCommand mainCommand = new MainCommand();
		mainCommand.openDirectory();

	}

}
