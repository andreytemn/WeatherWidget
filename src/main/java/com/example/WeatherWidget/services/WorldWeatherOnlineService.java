package com.example.WeatherWidget.services;

import com.example.WeatherWidget.model.WeatherCondition;
import com.example.WeatherWidget.utils.Conversions;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;

/**
 * Provides fetching weather data from World Weather Online public API (see
 * https://www.worldweatheronline.com/)
 *
 * @author atemnikov
 */
@Service
public class WorldWeatherOnlineService implements WeatherService {

    private static final String NAME = "World Weather Online Service";
    private static final Logger LOGGER = LoggerFactory.getLogger(NAME);
    private static final String KEY = "89eda2e4a5ee433d822144714180506";

    @Override
    public WeatherCondition fetchWeather(String location) {

        HttpClientBuilder builder = HttpClientBuilder.create();
        try (CloseableHttpClient client = builder.build()) {
            HttpGet getRequest = new HttpGet("http://api.worldweatheronline.com/premium/v1/weather.ashx?q=" + location
                    + "&num_of_days=1&format=json&key=" + KEY);
            LOGGER.debug(getRequest.toString());
            HttpResponse httpResponse = client.execute(getRequest);
            HttpEntity entity = httpResponse.getEntity();

            if (entity != null) {
                String str = EntityUtils.toString(entity);
                LOGGER.info(str);
                JSONObject result = new JSONObject(str);
                JSONObject query = (JSONObject) result.get("data");
                if (query.get("current_condition") == JSONObject.NULL) {
                    return null;
                }
                JSONArray currentCondition = (JSONArray) query.get("current_condition");
                JSONObject currentCondition0 = (JSONObject) currentCondition.get(0);

                JSONArray weatherDesc = (JSONArray) currentCondition0.get("weatherDesc");
                String text = ((JSONObject) weatherDesc.get(0)).getString("value");

                String temp = currentCondition0.getString("temp_C");
                String humidity = currentCondition0.getString("humidity");

                DecimalFormat format = new DecimalFormat("#.#");
                String speed = format.format(Conversions.convertMphToMs(currentCondition0.getString("windspeedMiles")));
                String direction = currentCondition0.getString("winddir16Point");

                WeatherCondition weatherCondition = new WeatherCondition(temp, text, humidity, speed, direction);
                LOGGER.debug(weatherCondition.toString());
                return weatherCondition;
            }
        } catch (Exception e) {
            LOGGER.debug(e.getMessage());
        }
        return null;
    }

    @Override
    public String getName() {
        return NAME;
    }
}
