package com.mhamp.weatherapp.repository;

import com.mhamp.weatherapp.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Long>{
	List<City> findByUserName(String user);
}

