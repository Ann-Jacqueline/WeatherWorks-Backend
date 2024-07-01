package backendtech.web;

import backendtech.model.CitySearch;
import backendtech.service.CitySearchService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Testklasse für den CitySearchController.
 * Diese Klasse testet die verschiedenen Endpunkte des CitySearchController.
 */
@WebMvcTest(CitySearchController.class)
public class CitySearchControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CitySearchService citySearchService;

    @BeforeEach
    void setUp() {
        CitySearch citySearch = new CitySearch("Berlin", 20, 18, "Sunny", 5.0, 65, 10, "DE", "12:00");
        citySearch.setId(1L);
        when(citySearchService.getCityEntries()).thenReturn(List.of(citySearch));
        when(citySearchService.getCityEntry(1L)).thenReturn(Optional.of(citySearch));
        when(citySearchService.addCityEntry(any(CitySearch.class))).thenReturn(citySearch);
    }

    /**
     * Testet den Endpunkt für das Abrufen aller CitySearch-Einträge.
     */
    @Test
    void testGetCityEntries() throws Exception {
        this.mockMvc.perform(get("/city"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value("Berlin"));
    }

    /**
     * Testet den Endpunkt für das Abrufen eines CitySearch-Eintrags anhand der ID.
     */
    @Test
    void testGetCityEntryById() throws Exception {
        this.mockMvc.perform(get("/city/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Berlin"));
    }

    /**
     * Testet den Endpunkt für das Hinzufügen eines neuen CitySearch-Eintrags.
     */
    @Test
    void testAddCityEntry() throws Exception {
        String cityJson = "{\"name\":\"Berlin\",\"temperature\":20,\"feelsLike\":18,\"description\":\"Sunny\",\"windSpeed\":5.0,\"humidity\":65,\"cloudiness\":10,\"country\":\"DE\",\"localTime\":\"12:00\"}";

        this.mockMvc.perform(post("/city")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(cityJson))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Berlin"));
    }

    /**
     * Testet den Endpunkt für das Löschen eines CitySearch-Eintrags anhand der ID.
     */
    @Test
    void testDeleteCityEntry() throws Exception {
        when(citySearchService.removeCityEntry(1L)).thenReturn(true);

        this.mockMvc.perform(delete("/city/1"))
                .andExpect(status().isNoContent());

        verify(citySearchService).removeCityEntry(1L);
    }
}
