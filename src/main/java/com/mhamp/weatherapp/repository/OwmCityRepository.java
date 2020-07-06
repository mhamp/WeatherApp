package com.mhamp.weatherapp.repository;

import com.mhamp.weatherapp.model.OwmCity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OwmCityRepository extends JpaRepository<OwmCity, Long> {

    List<OwmCity> findByName(String name);

}