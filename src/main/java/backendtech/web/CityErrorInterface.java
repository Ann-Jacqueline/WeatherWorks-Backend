package backendtech.web;

/**
 * Schnittstelle zur Definition eines Fehlerpfads.
 * Diese Schnittstelle wird verwendet, um den Pfad zur Fehlerseite zu spezifizieren.
 */
public interface CityErrorInterface {
    /**
     * Gibt den Pfad zur Fehlerseite zurück.
     *
     * @return Der Pfad zur Fehlerseite.
     */
    String getErrorPath();
}
