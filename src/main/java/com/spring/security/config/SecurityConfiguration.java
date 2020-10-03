package com.spring.security.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	private static final String EMPLOYEE = "EMPLOYEE";
	private static final String MANAGER = "MANAGER";
	private static final String ADMIN = "ADMIN";
	
	@Autowired
	private DataSource dataSource;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/").hasRole(EMPLOYEE)
								.antMatchers("/leaders/**").hasRole(MANAGER)
								.antMatchers("/systems/**").hasRole(ADMIN)
								.and().formLogin().permitAll()
								.and().logout().permitAll()
								.and().exceptionHandling().accessDeniedPage("/access-denied");
	}
}
