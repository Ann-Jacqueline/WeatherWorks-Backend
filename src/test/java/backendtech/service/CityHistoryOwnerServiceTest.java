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

    @Test
    void testGetCityHistoryOwner() {
        Optional<CityHistoryOwner> result = service.getCityHistoryOwner(1L);
        assertTrue(result.isPresent(), "Der Besitzer sollte gefunden werden");
        assertEquals("Ann-Jacqueline", result.get().getUserName(), "Der Benutzername sollte Ann-Jacqueline sein");
    }

    @Test
    void testGetCityHistoryOwners() {
        Iterable<CityHistoryOwner> result = service.getCityHistoryOwners();
        assertTrue(result.iterator().hasNext(), "Es sollte mindestens einen Besitzer geben");
        assertEquals("Ann-Jacqueline", result.iterator().next().getUserName(), "Der Benutzername sollte Ann-Jacqueline sein");
    }

    @Test
    void testGetAllUsers() {
        List<CityHistoryOwner> result = service.getAllUsers();
        assertSame(1, result.size(), "Die Anzahl der Benutzer sollte eins sein");
        assertEquals("Ann-Jacqueline", result.getFirst().getUserName(), "Der Benutzername sollte Ann-Jacqueline sein");
    }
}

