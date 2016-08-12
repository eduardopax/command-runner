package com.commandrunner.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.commandrunner.controller.UserController;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserController userController;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//@formatter:off
		 http
         .authorizeRequests()
             .antMatchers(
            		 "/public/**",
            		 "/css/**",
            		 "/fonts/**",
            		 "/img/**",
            		 "/js/**").permitAll()
             .anyRequest().authenticated()
             .and()
         .formLogin()
             .loginPage("/login")
             .permitAll()
             .and()
         .logout()
             .permitAll();
		 //@formatter:on
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(inMemoryUserDetailsManager());
	}

	@Bean
	public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
		// final Properties users = new Properties();
		// users.put("admin","admin,ROLE_USER,enabled"); //add whatever other
		// user you need
		return new InMemoryUserDetailsManager(this.userController.getUserDetails());
	}
}
