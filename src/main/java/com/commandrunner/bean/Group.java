package com.commandrunner.bean;

import java.util.List;

public class Group {

	private String name;

	private List<CommandGroup> commandGroup;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<CommandGroup> getCommandGroup() {
		return commandGroup;
	}

	public void setCommandGroup(List<CommandGroup> commandGroup) {
		this.commandGroup = commandGroup;
	}

}
