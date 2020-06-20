package com.mhamp.weatherapp.service;

import com.mhamp.weatherapp.repository.CityRepository;
import com.mhamp.weatherapp.repository.OwmCityRepository;
import com.mhamp.weatherapp.model.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityService {

	@Autowired
	private CityRepository cityRepository;
	@Autowired
	private OwmCityRepository owmCityRepository;

	public List<City> getCitiesByUser(String user) {
		return cityRepository.findByUserName(user);
	}

	public Optional<City> getCityById(long id) {
		return cityRepository.findById(id);
	}

	public void addCity(String user, String name) {
			cityRepository.save(new City(user, name));
	}
	
	public void deleteCity(long id) {
		Optional<City> city = cityRepository.findById(id);
		if (city.isPresent()) {
			cityRepository.delete(city.get());
		}
	}

	public void saveCity(City city) {
		cityRepository.save(city);
	}


}