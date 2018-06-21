package com.example.WeatherWidget.utils;

/**
 * This class provides conversion utilities for weather values, i.e. wind speed
 * and direction.
 * 
 * @author atemnikov
 *
 */
public class Conversions {

	/**
	 * Convert 360-degrees based direction to 16-point direction.
	 * 
	 * @param degrees
	 *            - direction (0 - 360 clockwise)
	 * @return 16-point direction
	 */
	public static String convertDegreesToDirection(String degrees) {
		int intDegrees = Integer.parseInt(degrees);
		if (intDegrees > 12 && intDegrees <= 34) {
			return "NNE";
		}
		if (intDegrees > 34 && intDegrees <= 56) {
			return "NE";
		}
		if (intDegrees > 56 && intDegrees <= 78) {
			return "ENE";
		}
		if (intDegrees > 78 && intDegrees <= 101) {
			return "E";
		}
		if (intDegrees > 102 && intDegrees <= 123) {
			return "ESE";
		}
		if (intDegrees > 123 && intDegrees <= 146) {
			return "SE";
		}
		if (intDegrees > 146 && intDegrees <= 168) {
			return "SSE";
		}
		if (intDegrees > 168 && intDegrees <= 191) {
			return "S";
		}
		if (intDegrees > 191 && intDegrees <= 213) {
			return "SSW";
		}
		if (intDegrees > 213 && intDegrees <= 236) {
			return "SW";
		}
		if (intDegrees > 236 && intDegrees <= 258) {
			return "WSW";
		}
		if (intDegrees > 25 && intDegrees <= 281) {
			return "W";
		}
		if (intDegrees > 281 && intDegrees <= 303) {
			return "WNW";
		}
		if (intDegrees > 303 && intDegrees <= 326) {
			return "NW";
		}
		if (intDegrees > 326 && intDegrees <= 348) {
			return "NNW";
		}
		return "N";
	}

	/**
	 * Convert miles per hour to meters per second speed.
	 * 
	 * @param speedMph
	 *            - mph speed
	 * @return m/s speed
	 */
	public static Double convertMphToMs(String speedMph) {
		return Double.parseDouble(speedMph) * 0.447;
	}
}
