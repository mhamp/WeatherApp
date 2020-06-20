package com.mhamp.weatherapp;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mhamp.weatherapp.model.OwmCity;
import com.mhamp.weatherapp.service.OwmCityService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


@SpringBootApplication
public class WeatherApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(OwmCityService owmCityService){
		return args -> {
			// read json and write to db
			ObjectMapper mapper = new ObjectMapper();
			TypeReference<List<OwmCity>> typeReference = new TypeReference<List<OwmCity>>(){};
			InputStream inputStream = TypeReference.class.getResourceAsStream("/city.list.min.json");
			try{
				List<OwmCity> cities = mapper.readValue(inputStream,typeReference);
				owmCityService.save(cities);
				System.out.println("Cities saved!");
			} catch(IOException e) {
				System.out.println("Unable to save cities:" + e.getMessage());
			}
		};
	}

	@Bean
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}

}
