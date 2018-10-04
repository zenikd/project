package org.ez.vk.ui;

import java.io.IOException;

import org.ez.vk.ui.command.MainCommand;

public class Starter {

	public static void main(String[] args) throws IOException {
		MainCommand mainCommand = new MainCommand();
		mainCommand.openDirectory();

	}

}
