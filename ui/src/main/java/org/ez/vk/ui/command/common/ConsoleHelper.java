package org.ez.vk.ui.command.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.ez.vk.exception.internal.InternalException;

public class ConsoleHelper {
	private final static String COMMAND_NO_UNIQUE = "command no unique";
	private final static String COMMAND_NO_EXIST = "command no exist";

	public static String chooseOneFromList(List<String> listCommands) {
		for (String command : listCommands) {
			System.out.println(command);
		}
		try {
			String typedCommand = readLine();
			Command command = setCommand(typedCommand, listCommands);
			if (!command.isUnique()) {
				System.out.println(COMMAND_NO_UNIQUE);
				return chooseOneFromList(listCommands);
			} else if (command.isExists()) {
				return command.getCommand();
			}
			System.out.println(COMMAND_NO_EXIST);
			return chooseOneFromList(listCommands);
		} catch (Exception e) {
			e.printStackTrace();
			return chooseOneFromList(listCommands);
		}

	}

	private static Command setCommand(String typedCommand, List<String> listCommands) {
		int countRepeat = 0;
		Command command = new Command(typedCommand, true, false);
		for (String curCommand : listCommands) {
			if (curCommand.startsWith(typedCommand)) {
				countRepeat++;
				command.setExists(true);
				command.setCommand(curCommand);
				if (countRepeat > 1) {
					command.setUnique(false);
					return command;
				}
			}
		}
		return command;
	}

	public static String readLine() throws InternalException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		try {
			return in.readLine();
		} catch (IOException e) {
			throw new InternalException();
		}
		
	}

	public static List<String> readMultipleLine() throws InternalException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		List<String> readLines = new ArrayList<String>();
		try {
			String line;

			while ((line = in.readLine()) != null && line.length()!= 0) {
				readLines.add(line);
			}
		} catch (IOException e) {
			throw new InternalException();
		}
		return readLines;
	}
}
