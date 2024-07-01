package backendtech.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * Diese Klasse repräsentiert die Historie einer Stadt in der Datenbank.
 * Sie enthält Informationen über die Stadt, das Land, die Temperatur, die lokale Zeit,
 * den Löschstatus und ob sie als Standard gesetzt ist.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class CityHistory {
    /**
     * Die eindeutige ID der Stadtgeschichte.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Der Name der Stadt.
     */
    private String cityName;

    /**
     * Das Land, in dem sich die Stadt befindet.
     */
    private String country;

    /**
     * Die Temperatur in der Stadt.
     */
    private int temperature;

    /**
     * Die lokale Zeit in der Stadt.
     */
    private String localTime;

    /**
     * Gibt an, ob der Eintrag gelöscht wurde.
     */
    private boolean deleted;

    /**
     * Gibt an, ob der Eintrag als Standard gesetzt ist.
     */
    private boolean setAsDefault;

    /**
     * Der Besitzer der Stadtgeschichte, verlinkt über die owner_id Spalte in der Datenbank.
     */
    @JoinColumn(name = "owner_id")
    private String owner;

    /**
     * Konstruktor zum Erstellen einer neuen Instanz von CityHistory mit den angegebenen Parametern.
     *
     * @param cityName    Der Name der Stadt.
     * @param country     Das Land, in dem sich die Stadt befindet.
     * @param temperature Die Temperatur in der Stadt.
     * @param localTime   Die lokale Zeit in der Stadt.
     * @param deleted     Der Löschstatus des Eintrags.
     * @param owner       Der Besitzer der Stadtgeschichte.
     * @param setAsDefault Gibt an, ob der Eintrag als Standard gesetzt ist.
     */
    public CityHistory(String cityName, String country, int temperature, String localTime, boolean deleted, String owner, boolean setAsDefault) {
        this.cityName = cityName;
        this.country = country;
        this.temperature = temperature;
        this.localTime = localTime;
        this.deleted = deleted;
        this.owner = owner;
        this.setAsDefault = setAsDefault;
    }
}
