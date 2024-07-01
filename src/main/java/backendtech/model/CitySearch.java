package backendtech.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * Diese Klasse repräsentiert eine Stadt-Suche in der Datenbank.
 * Sie enthält Informationen über die Stadt, einschließlich Temperatur, Wetterbeschreibung,
 * Windgeschwindigkeit, Luftfeuchtigkeit, Bewölkungsgrad, Land und lokale Zeit.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class CitySearch {
    /**
     * Die eindeutige ID der Stadt-Suche.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Der Name der Stadt.
     */
    private String name;

    /**
     * Die Temperatur in der Stadt.
     */
    private int temperature;

    /**
     * Die gefühlte Temperatur in der Stadt.
     */
    private int feelsLike;

    /**
     * Die Wetterbeschreibung.
     */
    private String description;

    /**
     * Die Windgeschwindigkeit in der Stadt.
     */
    private double windSpeed;

    /**
     * Die Luftfeuchtigkeit in der Stadt.
     */
    private int humidity;

    /**
     * Der Bewölkungsgrad in der Stadt.
     */
    private int cloudiness;

    /**
     * Das Land, in dem sich die Stadt befindet.
     */
    private String country;

    /**
     * Die lokale Zeit in der Stadt.
     */
    private String localTime;

    /**
     * Konstruktor zum Erstellen einer neuen Instanz von CitySearch mit den angegebenen Parametern.
     *
     * @param name         Der Name der Stadt.
     * @param temperature  Die Temperatur in der Stadt.
     * @param feelsLike    Die gefühlte Temperatur in der Stadt.
     * @param description  Die Wetterbeschreibung.
     * @param windSpeed    Die Windgeschwindigkeit in der Stadt.
     * @param humidity     Die Luftfeuchtigkeit in der Stadt.
     * @param cloudiness   Der Bewölkungsgrad in der Stadt.
     * @param country      Das Land, in dem sich die Stadt befindet.
     * @param localTime    Die lokale Zeit in der Stadt.
     */
    public CitySearch(String name, int temperature, int feelsLike, String description, double windSpeed, int humidity, int cloudiness, String country, String localTime) {
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
