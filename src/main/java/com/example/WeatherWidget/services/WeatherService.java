package com.example.WeatherWidget.services;

import com.example.WeatherWidget.model.WeatherCondition;

/**
 * Provides weather fetching feature from web service API.
 * 
 * @author atemnikov
 *
 */
public interface WeatherService {
	/**
	 * Fetches current weather from web service
	 * 
	 * @param location
	 *            - city or place name which weather should be viewed.
	 * @return WeatherCondition instance for specified location or null if any
	 *         troubles occurred
	 */
	WeatherCondition fetchWeather(String location);

	/**
	 * Get viewable service name
	 * 
	 * @return name
	 */
	String getName();
}
