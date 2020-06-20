package com.mhamp.weatherapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mhamp.weatherapp.model.weatherdata.*;
import lombok.Data;

import javax.persistence.Embedded;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherData {

    @Embedded
    private Main main;
    @Embedded
    private List<Weather> weather;
    private Integer visibility;
    @Embedded
    private Wind wind;
    @Embedded
    private Clouds clouds;
    private String name;


}

