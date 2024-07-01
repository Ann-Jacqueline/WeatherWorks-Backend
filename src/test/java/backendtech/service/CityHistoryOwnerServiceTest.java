package backendtech.service;

import backendtech.model.CityHistoryOwner;
import backendtech.persistence.CityHistoryOwnerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Testklasse für den CityHistoryOwnerService.
 * Diese Klasse testet die verschiedenen Methoden des CityHistoryOwnerService.
 */
@SpringBootTest
public class CityHistoryOwnerServiceTest {

    @Autowired
    private CityHistoryOwnerService service;

    @MockBean
    private CityHistoryOwnerRepository repository;

    @BeforeEach
    void setUp() {
        // Erstellen eines Beispiel-Objekts für die Tests
        CityHistoryOwner exampleOwner = new CityHistoryOwner();
        exampleOwner.setId(1L);
        exampleOwner.setUserName("Ann-Jacqueline");

        // Konfiguration der Mock-Antworten
        when(repository.findById(1L)).thenReturn(Optional.of(exampleOwner));
        when(repository.findAll()).thenReturn(List.of(exampleOwner));
        when(repository.save(any(CityHistoryOwner.class))).thenReturn(exampleOwner);
    }

    /**
     * Testet die Methode getCityHistoryOwner().
     */
    @Test
    void testGetCityHistoryOwner() {
        Optional<CityHistoryOwner> result = service.getCityHistoryOwner(1L);
        assertTrue(result.isPresent(), "Der Besitzer sollte gefunden werden");
        assertEquals("Ann-Jacqueline", result.get().getUserName(), "Der Benutzername sollte Ann-Jacqueline sein");
    }

    /**
     * Testet die Methode getCityHistoryOwners().
     */
    @Test
    void testGetCityHistoryOwners() {
        Iterable<CityHistoryOwner> result = service.getCityHistoryOwners();
        assertTrue(result.iterator().hasNext(), "Es sollte mindestens einen Besitzer geben");
        assertEquals("Ann-Jacqueline", result.iterator().next().getUserName(), "Der Benutzername sollte Ann-Jacqueline sein");
    }

    /**
     * Testet die Methode getAllUsers().
     */
    @Test
    void testGetAllUsers() {
        List<CityHistoryOwner> result = service.getAllUsers();
        assertEquals(1, result.size(), "Die Anzahl der Benutzer sollte eins sein");
        assertEquals("Ann-Jacqueline", result.get(0).getUserName(), "Der Benutzername sollte Ann-Jacqueline sein");
    }
}
