package com.mhamp.weatherapp.service;

import com.mhamp.weatherapp.model.WeatherData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    @Value("${application.owmapiurl}")
    private String url;

    @Value("${application.owmapikey}")
    private String key;

    @Autowired
    private RestTemplate restTemplate;

    public List<WeatherData> getWeatherByIds(List<Long> owmIds){
        return owmIds.stream()
                //.peek(id -> System.out.println(url + id + key))
                .map(id ->
                {
                    WeatherData weatherData = restTemplate.getForObject(url + id + key, WeatherData.class);
                    return weatherData;
                })
                .collect(Collectors.toList());
    }
}
