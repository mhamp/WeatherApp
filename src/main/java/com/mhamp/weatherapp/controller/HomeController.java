package com.mhamp.weatherapp.controller;


import com.mhamp.weatherapp.repository.CityRepository;
import com.mhamp.weatherapp.repository.OwmCityRepository;
import com.mhamp.weatherapp.model.City;
import com.mhamp.weatherapp.model.OwmCity;
import com.mhamp.weatherapp.model.WeatherData;
import com.mhamp.weatherapp.service.CityService;
import com.mhamp.weatherapp.service.OwmCityService;
import com.mhamp.weatherapp.service.UserService;
import com.mhamp.weatherapp.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {

	@Autowired
	private CityService cityService;

	@Autowired
	private OwmCityService owmCityService;

	@Autowired
	private UserService userService;

	@Autowired
	private WeatherService weatherService;

	@Autowired
	private OwmCityRepository owmCityRepository;

	@Autowired
	private CityRepository cityRepository;

	@RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
	public String showWelcomePage(ModelMap model) {
		String name = userService.getLoggedInUserName();
		model.put("name", name);
		List<City> cities = cityService.getCitiesByUser(name);
		model.put("cities", cities);

		List<Long> owmIds = cities.stream()
				.map(i -> owmCityService.getCityByName(i.getName()))
				.map(c -> c.getId())
				.collect(Collectors.toList());
		List<WeatherData> weatherData = weatherService.getWeatherByIds(owmIds);
		model.put("weatherdata", weatherData);
		return "home";
	}

	@RequestMapping(value = "/add-city", method = RequestMethod.GET)
	public String showAddCityPage(ModelMap model) {
		model.addAttribute("city", new City());
		return "city";
	}

	@RequestMapping(value = "/delete-city", method = RequestMethod.GET)
	public String deleteCity(@RequestParam long id) {
		cityService.deleteCity(id);
		// service.deleteCity(id);
		return "redirect:/home";
	}

	@RequestMapping(value = "/add-city", method = RequestMethod.POST)
	public String addCity(ModelMap model, City searchedCity, BindingResult result) {

		if (result.hasErrors()) {
			return "city";
		}

		boolean valid = cityService.validateCity(model, searchedCity);

		if(valid){
			searchedCity.setUserName(userService.getLoggedInUserName(model));
			cityService.saveCity(searchedCity);
		}

		return "redirect:/home";
	}
}
