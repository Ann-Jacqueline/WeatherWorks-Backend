package backendtech.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AllArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class CityEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double temperature; // Verwendung von double für Temperatur zur präzisen Speicherung
    private double minTemperature;
    private double maxTemperature;
    private double feelsLike;
    private String description; // Wetterbeschreibung (z.B. "leicht bewölkt")
    private String icon; // Wettericon-ID
    private double windSpeed;
    private int humidity;
    private int cloudiness; // Wolkenbedeckung in Prozent
    private String country;
    private int pressure;
    private int visibility;
    private int timezone; // Zeitzone als Offset in Sekunden von UTC
    private Date sunrise; // Datum/Uhrzeit des Sonnenaufgangs
    private Date sunset; // Datum/Uhrzeit des Sonnenuntergangs
    private String localDate; // Datum als String gespeichert
    private String localTime; // Lokale Zeit als String gespeichert

    public CityEntry(String name, double temperature, double minTemperature, double maxTemperature, double feelsLike,
                     String description, String icon, double windSpeed, int humidity, int cloudiness, String country,
                     int pressure, int visibility, int timezone, Date sunrise, Date sunset, String localDate, String localTime) {
        this.name = name;
        this.temperature = temperature;
        this.minTemperature = minTemperature;
        this.maxTemperature = maxTemperature;
        this.feelsLike = feelsLike;
        this.description = description;
        this.icon = icon;
        this.windSpeed = windSpeed;
        this.humidity = humidity;
        this.cloudiness = cloudiness;
        this.country = country;
        this.pressure = pressure;
        this.visibility = visibility;
        this.timezone = timezone;
        this.sunrise = sunrise;
        this.sunset = sunset;
        this.localDate = localDate;
        this.localTime = localTime;
    }
}

