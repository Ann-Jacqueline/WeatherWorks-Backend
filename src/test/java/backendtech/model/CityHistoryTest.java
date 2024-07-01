package backendtech.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CityHistoryTest {

    @Test
    void testCityHistoryToString() {
        // Erstellen einer Instanz von CityHistoryOwner, die als Abhängigkeit benötigt wird
        CityHistoryOwner owner = new CityHistoryOwner();
        owner.setId(1L); // Setzen einer ID für den Besitzer
        owner.setUserName("Ann-Jacqueline");

        // Erstellen einer Instanz von CityHistory
        CityHistory cityHistory = new CityHistory("Berlin", "DE", 22, "14:00", false, "Ann-Jacqueline", true);

        // Erwarteter String, der das Ergebnis von toString() darstellt
        cityHistory.setId(1L); // Setzen der ID für CityHistory
        String expected = "CityHistory(id=1, cityName=Berlin, country=DE, temperature=22, localTime=14:00, deleted=false, setAsDefault=true, owner=Ann-Jacqueline)";

        // Ausführen der toString() Methode und Vergleich des Ergebnisses mit dem erwarteten String
        String result = cityHistory.toString();

        // Assertion, dass der tatsächliche String gleich dem erwarteten String ist
        assertEquals(expected, result);
    }
}
