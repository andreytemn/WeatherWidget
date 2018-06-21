package com.example.WeatherWidget.controllers;

import org.springframework.beans.factory.config.ServiceLocatorFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Java configuration for weather service locator.
 * 
 * @author atemnikov
 *
 */
@Configuration
@ComponentScan("com.example.WeatherWidget.services")
public class Config {

	/**
	 * Configures service locator for weather services.
	 * 
	 * @return
	 */
	@Bean
	public ServiceLocatorFactoryBean serviceLocatorBean() {
		ServiceLocatorFactoryBean bean = new ServiceLocatorFactoryBean();
		bean.setServiceLocatorInterface(ServiceLocator.class);
		return bean;
	}
}