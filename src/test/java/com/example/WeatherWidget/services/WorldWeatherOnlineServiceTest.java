package com.example.WeatherWidget.services;

import org.junit.Test;

import static org.junit.Assert.*;

public class WorldWeatherOnlineServiceTest {

    @Test
    public void fetchWeather() {
        WeatherService service = new WorldWeatherOnlineService();
        assertNotNull(service.fetchWeather("Chelyabinsk"));
        assertNull(service.fetchWeather(""));
    }
}