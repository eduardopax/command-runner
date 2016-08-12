package com.commandrunner.bean;

public class Command {

	private Long id;

	private String name;

	private String script;

	private String color;

	public Command() {
		this.id = CommandIdGenerator.nextId();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Long getId() {
		return id;
	}

	public String getScript() {
		return script;
	}

	public void setScript(String script) {
		this.script = script;
	}

}
