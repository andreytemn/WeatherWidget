package com.example.WeatherWidget.services;

import org.junit.Test;

import static org.junit.Assert.*;

public class YahooServiceTest {

    @Test
    public void fetchWeather() {
        WeatherService service = new YahooService();
        assertNotNull(service.fetchWeather("Chelyabinsk"));
        assertNull(service.fetchWeather(""));
    }
}