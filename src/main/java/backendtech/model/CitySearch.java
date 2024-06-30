package backendtech.model;

import jakarta.persistence.*;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class CitySearch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int temperature;
    private int feelsLike;
    private String description;
    private double windSpeed;
    private int humidity;
    private int cloudiness;
    private String country;
    private String localTime;


    public CitySearch(String name, int temperature, int feelsLike, String description,double windSpeed, int humidity, int cloudiness, String country, String localTime) {
        this.name = name;
        this.temperature = temperature;
        this.feelsLike = feelsLike;
        this.description = description;
        this.windSpeed = windSpeed;
        this.humidity = humidity;
        this.cloudiness = cloudiness;
        this.country = country;
        this.localTime = localTime;
    }
    }
