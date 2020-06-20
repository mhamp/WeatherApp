package com.mhamp.weatherapp.service;

import com.mhamp.weatherapp.model.OwmCity;
import com.mhamp.weatherapp.repository.OwmCityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OwmCityService {

    @Autowired
    private OwmCityRepository owmCityRepository;

    public OwmCityService(OwmCityRepository owmCityRepository) {
        this.owmCityRepository = owmCityRepository;
    }

    public Iterable<OwmCity> list() {
        return owmCityRepository.findAll();
    }

    public OwmCity save(OwmCity city){
        return owmCityRepository.save(city);
    }

    public Iterable<OwmCity> save(List<OwmCity> cities) {
        return owmCityRepository.saveAll(cities);
    }

    public OwmCity getCitiesByName(String name) {
        return owmCityRepository.findByName(name);
    }

}
