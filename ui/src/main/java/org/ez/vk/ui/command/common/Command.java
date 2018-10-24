package org.ez.vk.ui.command.common;

public class Command {
	private String command;
	private boolean isUnique;
	private boolean isExists;

	public Command(String command, boolean isUnique, boolean isExists) {
		super();
		this.command = command;
		this.isUnique = isUnique;
		this.isExists = isExists;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public boolean isUnique() {
		return isUnique;
	}

	public void setUnique(boolean isUnique) {
		this.isUnique = isUnique;
	}

	public boolean isExists() {
		return isExists;
	}

	public void setExists(boolean isExists) {
		this.isExists = isExists;
	}

}
