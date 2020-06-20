package com.mhamp.weatherapp.controller;


import com.mhamp.weatherapp.repository.CityRepository;
import com.mhamp.weatherapp.repository.OwmCityRepository;
import com.mhamp.weatherapp.model.City;
import com.mhamp.weatherapp.model.OwmCity;
import com.mhamp.weatherapp.model.WeatherData;
import com.mhamp.weatherapp.service.CityService;
import com.mhamp.weatherapp.service.OwmCityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {

	@Autowired
	private CityService cityService;

	@Autowired
	private OwmCityService owmCityService;

	@Autowired
	private OwmCityRepository owmCityRepository;

	@Autowired
	private CityRepository cityRepository;

	@Autowired
	private RestTemplate restTemplate;

	@Value("${application.owmapiurl}")
	private String url;

	@Value("${application.owmapikey}")
	private String key;

	@RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
	public String showWelcomePage(ModelMap model) {
		model.put("name", getLoggedinUserName());
		String name = getLoggedInUserName(model);
		List<City> cities = cityService.getCitiesByUser(name);
		model.put("cities", cities);
		List<Long> owmIds = getOwmCityIds(cities);
		List<WeatherData> weatherData = getWeatherByIds(owmIds);
		model.put("weatherdata", weatherData);
		return "home";
	}

	private List<Long> getOwmCityIds(List<City> cities){
		List<Long> owmCityIds = new ArrayList<>();
		for(City city : cities){
			OwmCity owmCity = owmCityRepository.findByName(city.getName());
			owmCityIds.add(owmCity.getId());
		}
		return owmCityIds;
	}

	private List<WeatherData> getWeatherByIds(List<Long> owmIds){
		return owmIds.stream()
			//.peek(id -> System.out.println(url + id + key))
			.map(id ->
				{
					WeatherData weatherData = restTemplate.getForObject(url + id + key, WeatherData.class);
					return weatherData;
				})
			.collect(Collectors.toList());
	}

	private String getLoggedinUserName() {
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			return ((UserDetails) principal).getUsername();
		}

		return principal.toString();
	}

	private String getLoggedInUserName(ModelMap model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			return ((UserDetails) principal).getUsername();
		}

		return principal.toString();
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
	public String addCity(ModelMap model, City city, BindingResult result) {

		if (result.hasErrors()) {
			return "city";
		}
		OwmCity owmCity = owmCityRepository.findByName(city.getName());
		//System.out.println("ID of registered city:"+owmCity.getUid());
		if (owmCity != null) {
			city.setUserName(getLoggedInUserName(model));
			cityService.saveCity(city);
		}
		return "redirect:/home";
	}

}
