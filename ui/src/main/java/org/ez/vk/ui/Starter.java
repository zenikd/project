package org.ez.vk.ui;

import org.ez.vk.dao.common.exception.internal.InternalException;
import org.ez.vk.ui.command.MainCommand;

;

public class Starter {

	public static void main(String[] args) throws InternalException {
		MainCommand mainCommand = new MainCommand();
		mainCommand.openDirectory();

	}

}
