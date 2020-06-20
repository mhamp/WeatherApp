package com.mhamp.weatherapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Indexed;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OwmCity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long uid;
    private Long id;
    private String name;
    private String state;
    private String country;
    @Embedded
    private OwmCityCoordinates coord;

}
