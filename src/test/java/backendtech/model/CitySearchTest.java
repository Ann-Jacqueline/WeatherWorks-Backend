package backendtech.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CitySearchTest {
    @Test
    void testCitySearchToString() {
        // Erstellen einer Instanz von CitySearch
        CitySearch citySearch = new CitySearch("Berlin", 24, 23, "Clear sky", 5.5, 65, 10, "Germany", "12:00");

        // Erwarteter String, der das Ergebnis von toString() darstellt
        String expected = "CitySearch(id=null, name=Berlin, temperature=24, feelsLike=23, description=Clear sky, windSpeed=5.5, humidity=65, cloudiness=10, country=Germany, localTime=12:00)";

        // Ausführen der toString() Methode und Vergleich des Ergebnisses mit dem erwarteten String
        String result = citySearch.toString();

        // Assertion, dass der tatsächliche String gleich dem erwarteten String ist
        assertEquals(expected, result);
    }
}

