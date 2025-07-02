package com.skycast.controller;

import com.skycast.dtos.WeatherResponse;
import com.skycast.services.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;

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
      try{
          WeatherResponse weather = weatherService.getWeather(city);
          model.addAttribute("weather", weather);
          return "weather";
      }catch (HttpClientErrorException e){
          System.out.println(e.getMessage());
          model.addAttribute("msg", "City Not found. Please try to write correct city name.");
          return "index";
      }
    }
}