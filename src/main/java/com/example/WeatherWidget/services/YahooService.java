package com.example.WeatherWidget.services;

import com.example.WeatherWidget.model.WeatherCondition;
import com.example.WeatherWidget.utils.Conversions;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.text.DecimalFormat;

/**
 * Provides fetching weather data from Yahoo Weather public API (see
 * https://developer.yahoo.com/weather/?guccounter=1
 *
 * @author atemnikov
 */
@Service
public class YahooService implements WeatherService {

    private static final String NAME = "Yahoo Weather Service";
    private static final Logger LOGGER = LoggerFactory.getLogger(NAME);

    @Override
    public WeatherCondition fetchWeather(String location) {
        HttpClientBuilder builder = HttpClientBuilder.create();
        try (CloseableHttpClient client = builder.build()) {
            String searchText = "select item.condition.text, item.condition.temp, wind, atmosphere.humidity from weather.forecast where woeid in (select woeid from geo.places(1) where text='"
                    + location + "') and u='с'";
            HttpGet getRequest = new HttpGet("https://query.yahooapis.com/v1/public/yql?q="
                    + URLEncoder.encode(searchText, "UTF-8") + "&format=json");
            LOGGER.debug(getRequest.toString());
            HttpResponse httpResponse = client.execute(getRequest);
            HttpEntity entity = httpResponse.getEntity();

            if (entity != null) {
                String str = EntityUtils.toString(entity);
                LOGGER.info(str);
                JSONObject result = new JSONObject(str);
                JSONObject query = (JSONObject) result.get("query");
                if (query.get("results") == JSONObject.NULL) {
                    return null;
                }
                JSONObject results = (JSONObject) query.get("results");
                JSONObject channel = (JSONObject) results.get("channel");

                JSONObject atmosphere = (JSONObject) channel.get("atmosphere");
                String humidity = atmosphere.getString("humidity");

                JSONObject item = (JSONObject) channel.get("item");
                JSONObject condition = (JSONObject) item.get("condition");
                String temp = condition.getString("temp");
                String text = condition.getString("text");

                JSONObject wind = (JSONObject) channel.get("wind");
                DecimalFormat format = new DecimalFormat("#.#");
                String speed = format.format(Conversions.convertMphToMs(wind.getString("speed"))).toString();
                String direction = Conversions.convertDegreesToDirection(wind.getString("direction"));

                WeatherCondition weatherCondition = new WeatherCondition(temp, text, humidity, speed, direction);
                LOGGER.debug(weatherCondition.toString());
                return weatherCondition;
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    @Override
    public String getName() {
        return NAME;
    }
}
