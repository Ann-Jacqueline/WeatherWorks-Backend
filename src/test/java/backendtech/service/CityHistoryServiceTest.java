package backendtech.service;

import backendtech.model.CityHistory;
import backendtech.persistence.CityHistoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Testklasse für den CityHistoryService.
 * Diese Klasse testet die verschiedenen Methoden des CityHistoryService.
 */
public class CityHistoryServiceTest {

    @Mock
    private CityHistoryRepository cityHistoryRepository;

    @InjectMocks
    private CityHistoryService cityHistoryService;

    private CityHistory cityHistory;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        cityHistory = new CityHistory("Berlin", "DE", 20, "12:00", false, "Ann-Jacqueline", true);
        cityHistory.setId(1L);
    }

    /**
     * Testet die Methode getCityHistory().
     */
    @Test
    void testGetCityHistory() {
        when(cityHistoryRepository.findById(1L)).thenReturn(Optional.of(cityHistory));

        Optional<CityHistory> foundCityHistory = cityHistoryService.getCityHistory(1L);

        assertTrue(foundCityHistory.isPresent());
        assertEquals("Berlin", foundCityHistory.get().getCityName());
    }

    /**
     * Testet die Methode getCityHistories().
     */
    @Test
    void testGetCityHistories() {
        when(cityHistoryRepository.findAll()).thenReturn(List.of(cityHistory));

        Iterable<CityHistory> cityHistories = cityHistoryService.getCityHistories();

        assertNotNull(cityHistories);
        assertEquals(1, ((List<CityHistory>) cityHistories).size());
        assertEquals("Berlin", ((List<CityHistory>) cityHistories).get(0).getCityName());
    }

    /**
     * Testet die Methode addCityHistory().
     */
    @Test
    void testAddCityHistory() {
        when(cityHistoryRepository.save(any(CityHistory.class))).thenReturn(cityHistory);

        CityHistory newCityHistory = cityHistoryService.addCityHistory(cityHistory);

        assertNotNull(newCityHistory);
        assertEquals("Berlin", newCityHistory.getCityName());
    }

    /**
     * Testet die Methode removeCityHistory().
     */
    @Test
    void testRemoveCityHistory() {
        when(cityHistoryRepository.existsById(1L)).thenReturn(true);
        doNothing().when(cityHistoryRepository).deleteById(1L);

        boolean isRemoved = cityHistoryService.removeCityHistory(1L);

        assertTrue(isRemoved);
        verify(cityHistoryRepository, times(1)).deleteById(1L);
    }

    /**
     * Testet die Methode removeCityHistory() für den Fall, dass die CityHistory nicht gefunden wird.
     */
    @Test
    void testRemoveCityHistoryNotFound() {
        when(cityHistoryRepository.existsById(1L)).thenReturn(false);

        boolean isRemoved = cityHistoryService.removeCityHistory(1L);

        assertFalse(isRemoved);
        verify(cityHistoryRepository, never()).deleteById(1L);
    }
}
