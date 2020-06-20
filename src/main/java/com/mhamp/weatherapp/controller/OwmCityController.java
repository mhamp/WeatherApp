package com.mhamp.weatherapp.controller;

import com.mhamp.weatherapp.model.OwmCity;
import com.mhamp.weatherapp.service.OwmCityService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/owmcities")
public class OwmCityController {

    private OwmCityService owmCityService;

    public OwmCityController(OwmCityService owmCityService) {
        this.owmCityService = owmCityService;
    }

    @GetMapping("/list")
    public Iterable<OwmCity> list(){
        return owmCityService.list();
    }
}
