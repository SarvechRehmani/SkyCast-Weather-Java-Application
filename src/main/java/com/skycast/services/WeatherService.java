package com.skycast.services;


import com.skycast.dtos.WeatherResponse;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@Service
public class WeatherService {

    @Value("${weather.api.key}")
    private String apiKey;

    @Value("${weather.api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public WeatherResponse getWeather(String city) {
        String url = String.format("%s?q=%s&appid=%s&units=metric", apiUrl, city, apiKey);
        String responseStr = restTemplate.getForObject(url, String.class);

        JSONObject json = new JSONObject(responseStr);

        WeatherResponse weatherResponse = new WeatherResponse();

        weatherResponse.setCity(json.getString("name"));

        JSONObject sys = json.getJSONObject("sys");
        weatherResponse.setCountry(sys.getString("country"));

        JSONObject main = json.getJSONObject("main");
        weatherResponse.setTemperature(main.getDouble("temp"));
        weatherResponse.setFeelsLike(main.getDouble("feels_like"));
        weatherResponse.setHumidity(main.getInt("humidity"));
        weatherResponse.setPressure(main.getInt("pressure"));

        JSONObject wind = json.getJSONObject("wind");
        weatherResponse.setWindSpeed(wind.has("speed") ? wind.getDouble("speed") : 0.0);

        if (wind.has("deg")) {
            weatherResponse.setWindDirection(degreesToCompass(wind.getInt("deg")));
        } else {
            weatherResponse.setWindDirection("N/A");
        }

        JSONObject weather = json.getJSONArray("weather").getJSONObject(0);
        weatherResponse.setDescription(weather.getString("description"));
        weatherResponse.setIcon(weather.getString("icon"));

        long sunriseTimestamp = sys.getLong("sunrise");
        long sunsetTimestamp = sys.getLong("sunset");

        String timezoneOffset = json.has("timezone") ? json.get("timezone").toString() : "0";
        int offsetSeconds = Integer.parseInt(timezoneOffset);
        weatherResponse.setSunrise(formatUnixTime(sunriseTimestamp, offsetSeconds));
        weatherResponse.setSunset(formatUnixTime(sunsetTimestamp, offsetSeconds));

        return weatherResponse;
    }

    private String formatUnixTime(long timestamp, int offsetSeconds) {
        Date date = new Date((timestamp + offsetSeconds) * 1000);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        return sdf.format(date);
    }

    private String degreesToCompass(int degrees) {
        String[] directions = {
                "N", "NNE", "NE", "ENE", "E", "ESE", "SE", "SSE",
                "S", "SSW", "SW", "WSW", "W", "WNW", "NW", "NNW"
        };
        int index = (int) ((degrees / 22.5) + 0.5) % 16;
        return directions[index];
    }
}