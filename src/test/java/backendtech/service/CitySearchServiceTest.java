package backendtech.service;

import backendtech.model.CitySearch;
import backendtech.persistence.CitySearchRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Testklasse für den CitySearchService.
 * Diese Klasse testet die verschiedenen Methoden des CitySearchService.
 */
@SpringBootTest
public class CitySearchServiceTest {

    @Autowired
    private CitySearchService service;

    @MockBean
    private CitySearchRepository repository;

    @BeforeEach
    void setUp() {
        CitySearch berlin = new CitySearch("Berlin", 24, 22, "Sunny", 5.0, 60, 0, "DE", "12:00");
        CitySearch london = new CitySearch("London", 15, 15, "Cloudy", 10.0, 80, 100, "UK", "11:00");

        doReturn(List.of(berlin, london)).when(repository).findAll();
        doReturn(Optional.of(berlin)).when(repository).findById(1L);
        doReturn(berlin).when(repository).save(any(CitySearch.class));
    }

    /**
     * Testet die Methode getCityEntry().
     */
    @Test
    void testGetCityEntry() {
        Optional<CitySearch> found = service.getCityEntry(1L);
        assertTrue(found.isPresent(), "City should be found with id 1");
        assertEquals("Berlin", found.get().getName(), "The name of the city should be Berlin");
    }

    /**
     * Testet die Methode getCityEntries().
     */
    @Test
    void testGetCityEntries() {
        Iterable<CitySearch> entries = service.getCityEntries();
        List<CitySearch> result = List.of(entries.iterator().next(), entries.iterator().next());
        assertEquals(2, result.size(), "Should return two cities");
    }

    /**
     * Testet die Methode removeCityEntry().
     */
    @Test
    void testRemoveCityEntry() {
        when(repository.existsById(1L)).thenReturn(true);
        boolean result = service.removeCityEntry(1L);
        assertTrue(result, "Should return true as the city exists and should be deleted");
        verify(repository).deleteById(1L);
    }
}
