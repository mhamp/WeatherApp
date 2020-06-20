package com.mhamp.weatherapp.repository;

import com.mhamp.weatherapp.model.OwmCity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwmCityRepository extends CrudRepository<OwmCity, Long> {
    OwmCity findByName(String name);
}