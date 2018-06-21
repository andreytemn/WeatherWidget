package com.example.WeatherWidget.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Index web controller
 * 
 * @author atemnikov
 *
 */
@Controller
public class WebController {

	private static final Logger LOGGER = LoggerFactory.getLogger("WebController");
	/**
	 * Get widget home page
	 * 
	 * @return index page
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String homepage() {
		LOGGER.debug("return index for \"/\" request");
		return "index";
	}
}