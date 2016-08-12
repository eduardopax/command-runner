package com.commandrunner.bean;

import java.util.List;

public class CommandGroup {

	private String name;

	private List<Command> commands;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Command> getCommands() {
		return commands;
	}

	public void setCommands(List<Command> commands) {
		this.commands = commands;
	}

}
