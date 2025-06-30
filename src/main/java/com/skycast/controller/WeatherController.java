package com.skycast.controller;

import com.skycast.dtos.WeatherResponse;
import com.skycast.services.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/weather")
    public String getWeather(@RequestParam String city, Model model) {
        WeatherResponse weather = weatherService.getWeather(city);
        model.addAttribute("weather", weather);
        return "weather";
    }
}