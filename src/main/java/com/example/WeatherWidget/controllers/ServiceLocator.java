package com.example.WeatherWidget.controllers;

import com.example.WeatherWidget.services.WeatherService;

/**
 * Service locator for weather service instances
 * 
 * @author atemnikov
 *
 */
public interface ServiceLocator {

	/**
	 * Return weather service by specified name.
	 * 
	 * @param name
	 *            - weather service name
	 * @return service instance
	 * @throws NoSuchBeanDefinitionException
	 *             if no service found
	 */
	WeatherService getService(String name);
}
