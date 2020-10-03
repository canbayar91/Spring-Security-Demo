package com.spring.security.config;

import java.beans.PropertyVetoException;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.spring.security")
@PropertySource("classpath:postgresql.properties")
public class ApplicationConfiguration {
	
	@Autowired
	private Environment environment;
	
	private Logger logger = Logger.getLogger(getClass().getName());
	
	@Bean
	public ViewResolver viewResolver() {
		
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jsp");
		
		return viewResolver;
	}

	@Bean
	public DataSource dataSource() {
		
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		
		try {
			dataSource.setDriverClass(environment.getProperty("jdbc.driver"));
		} catch (PropertyVetoException e) {
			logger.severe("JDBC Driver could not be loaded");
			throw new RuntimeException(e);
		}
		
		logger.info("Connection on URL: " + environment.getProperty("jdbc.url"));
		logger.info("Using user: " + environment.getProperty("jdbc.user"));
		
		dataSource.setJdbcUrl(environment.getProperty("jdbc.url"));
		dataSource.setUser(environment.getProperty("jdbc.user"));
		dataSource.setPassword(environment.getProperty("jdbc.password"));
		
		dataSource.setInitialPoolSize(getIntegerProperty("connection.pool.initialPoolSize"));
		dataSource.setMinPoolSize(getIntegerProperty("connection.pool.minPoolSize"));
		dataSource.setMaxPoolSize(getIntegerProperty("connection.pool.maxPoolSize"));
		dataSource.setMaxIdleTime(getIntegerProperty("connection.pool.maxIdleTime"));
		
		return dataSource;
	}
	
	private int getIntegerProperty(String propertyName) {
		String propertyValue = environment.getProperty(propertyName);
		return Integer.parseInt(propertyValue);
	}
}
