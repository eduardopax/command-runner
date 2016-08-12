package com.commandrunner.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.commandrunner.controller.ConfigController;

//@Configuration
//@EnableWebSecurity
public class WebSecurityConfigDev extends WebSecurityConfigurerAdapter {

	@Autowired
	private ConfigController configController;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//@formatter:off
		 http
         .authorizeRequests()
             .anyRequest().permitAll();
		 //@formatter:on
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		System.out.println(configController.getConfig().getUsers().size());
	}
}
