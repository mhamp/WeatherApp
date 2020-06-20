package com.mhamp.weatherapp.model.weatherdata;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Weather {

    public Integer id;
    public String main;
    public String description;
    public String icon;

}
