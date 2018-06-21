package com.example.WeatherWidget.controllers;

import com.example.WeatherWidget.model.WeatherCondition;
import com.example.WeatherWidget.services.WeatherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * This controller provides weather fetching API. To fetch weather you should
 * specify two parameters:
 * 1. city (city name which weather should be viewed)
 * 2. service (service name should be used)
 * Returns ResponseEntity with WeatherCondition instance or error description.
 * Usage example:
 * (host)/weather?city=Moscow&service=yahooService
 *
 * @author atemnikov
 */
@RestController
public class WeatherController {

    private static final Logger LOGGER = LoggerFactory.getLogger("WeatherController");
    @Autowired
    private ServiceLocator serviceLocator;

    /**
     * Fetch weather from specified service for specified city. Returns 400 error if
     * city name is empty or such service not found.
     *
     * @param city        - city name which weather should be viewed
     * @param serviceName - service name should be used
     * @return ResponseEntity with WeatherCondition or error description
     */
    @RequestMapping(value = "/weather", method = RequestMethod.GET)
    public ResponseEntity weather(@RequestParam(value = "city") String city,
                                  @RequestParam(value = "service") String serviceName) {
        try {
            WeatherService service = serviceLocator.getService(serviceName);
            WeatherCondition weatherCondition = service.fetchWeather(city);
            if (weatherCondition == null) {
                String body = "No such city found or " + service.getName() + " does not work";
                LOGGER.info(body);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(body);
            }
            LOGGER.debug("return " + weatherCondition.toString() + " from " + serviceName + " for " + city);
            return ResponseEntity.ok(weatherCondition);
        } catch (IllegalArgumentException | NoSuchBeanDefinitionException e) {
            LOGGER.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No such service found");
        }
    }
}