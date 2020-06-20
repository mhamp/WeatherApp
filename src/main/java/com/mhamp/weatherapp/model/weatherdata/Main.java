package com.mhamp.weatherapp.model.weatherdata;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Main {

    public Double temp;
    public Double feelsLike;
    @JsonProperty("temp_min")
    public Double tempMin;
    @JsonProperty("temp_max")
    public Double tempMax;
    public Integer pressure;
    public Integer humidity;

}
