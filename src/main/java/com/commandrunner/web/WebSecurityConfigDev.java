package com.commandrunner.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.commandrunner.service.ConfigurationService;

//@Configuration
//@EnableWebSecurity
public class WebSecurityConfigDev extends WebSecurityConfigurerAdapter {

	@Autowired
	private ConfigurationService configurationService;

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
		System.out.println(configurationService.getConfiguration().getUsers().size());
	}
}
