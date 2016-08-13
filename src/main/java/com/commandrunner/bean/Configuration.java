package com.commandrunner.bean;

import java.util.List;

public class Configuration {

	private List<User> users;

	private List<Group> groups;

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}



}
