package com.commandrunner.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;

import com.commandrunner.bean.User;
import com.commandrunner.bean.UserDetailsImpl;
import com.commandrunner.service.ConfigurationService;

@Controller
public class UserController {

	@Autowired
	private ConfigurationService configurationService;

	public List<UserDetails> getUserDetails() {
		List<UserDetails> users = new ArrayList<>();
		for (User user : this.configurationService.getConfiguration().getUsers()) {
			UserDetailsImpl userDetails = new UserDetailsImpl(user);
			users.add(userDetails);
		}
		return users;
	}

}
