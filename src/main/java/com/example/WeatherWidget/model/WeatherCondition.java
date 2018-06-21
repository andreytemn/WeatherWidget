package com.example.WeatherWidget.model;

/**
 * Model for current weather condition
 * 
 * @author atemnikov
 *
 */
public class WeatherCondition {
	public final String temp;
	public final String description;
	public final String humidity;
	public final String windSpeed;
	public final String windDirection;

	/**
	 * Constructor for WeatherCondition
	 * 
	 * @param temp
	 *            - air temperature
	 * @param description
	 *            - text description for weather condition
	 * @param humidity
	 *            - humidity in per cents
	 * @param windSpeed
	 *            - wind speed (expected to be in m/s)
	 * @param windDirection
	 *            - wind direction (expected to 16-point based)
	 */
	public WeatherCondition(String temp, String description, String humidity, String windSpeed, String windDirection) {
		this.temp = temp;
		this.description = description;
		this.humidity = humidity;
		this.windSpeed = windSpeed;
		this.windDirection = windDirection;
	}

	@Override
	public String toString() {
		return "WeatherCondition [temp=" + temp + ", description=" + description + ", humidity=" + humidity
				+ ", windSpeed=" + windSpeed + ", windDirection=" + windDirection + "]";
	}
}
