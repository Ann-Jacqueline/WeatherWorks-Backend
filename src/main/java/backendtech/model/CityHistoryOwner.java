package backendtech.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * Diese Klasse repräsentiert den Besitzer einer spezifischen CityHistory und defaultSearch in der Datenbank.
 * Sie enthält Informationen über den Benutzer, einschließlich eines eindeutigen Benutzernamens.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Setter
@Entity
public class CityHistoryOwner {
    /**
     * Die eindeutige ID des Besitzers.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Der Benutzername des Besitzers.
     */
    private String userName;

    /**
     * Konstruktor zum Erstellen einer neuen Instanz von CityHistoryOwner mit dem angegebenen Benutzernamen.
     *
     * @param userName Der Benutzername des Besitzers.
     */
    public CityHistoryOwner(String userName) {
        this.userName = userName;
    }
}
