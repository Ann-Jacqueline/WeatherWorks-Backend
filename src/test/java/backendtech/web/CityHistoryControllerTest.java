package backendtech.web;

import backendtech.model.CityHistory;
import backendtech.service.CityHistoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Testklasse für den CityHistoryController.
 * Diese Klasse testet die verschiedenen Endpunkte des CityHistoryController.
 */
@WebMvcTest(CityHistoryController.class)
public class CityHistoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CityHistoryService cityHistoryService;

    @BeforeEach
    void setUp() {
        CityHistory cityHistory = new CityHistory("Berlin", "DE", 20, "12:00", false, "Ann-Jacqueline", true);
        cityHistory.setId(1L);

        when(cityHistoryService.getCityHistories()).thenReturn(List.of(cityHistory));
        when(cityHistoryService.getCityHistory(1L)).thenReturn(Optional.of(cityHistory));
        when(cityHistoryService.addCityHistory(any(CityHistory.class))).thenReturn(cityHistory);
    }

    /**
     * Testet den Endpunkt für das Abrufen aller CityHistories.
     */
    @Test
    void testGetCityHistories() throws Exception {
        this.mockMvc.perform(get("/history"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].cityName").value("Berlin"));
    }

    /**
     * Testet den Endpunkt für das Abrufen einer CityHistory anhand der ID.
     */
    @Test
    void testGetCityHistoryById() throws Exception {
        this.mockMvc.perform(get("/history/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.cityName").value("Berlin"));
    }

    /**
     * Testet den Endpunkt für das Hinzufügen einer neuen CityHistory.
     */
    @Test
    void testAddCityHistory() throws Exception {
        String cityHistoryJson = "{\"cityName\":\"Berlin\",\"country\":\"DE\",\"temperature\":20,\"localTime\":\"12:00\",\"deleted\":false,\"owner\":\"Ann-Jacqueline\",\"setAsDefault\":true}";

        this.mockMvc.perform(post("/history")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(cityHistoryJson))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.cityName").value("Berlin"));
    }

    /**
     * Testet den Endpunkt für das Löschen einer CityHistory anhand der ID.
     */
    @Test
    void testDeleteCityHistory() throws Exception {
        when(cityHistoryService.removeCityHistory(1L)).thenReturn(true);

        this.mockMvc.perform(delete("/history/1"))
                .andExpect(status().isNoContent());

        verify(cityHistoryService).removeCityHistory(1L);
    }

    /**
     * Testet den Endpunkt für das Löschen einer CityHistory, wenn diese nicht gefunden wird.
     */
    @Test
    void testDeleteCityHistoryNotFound() throws Exception {
        when(cityHistoryService.removeCityHistory(1L)).thenReturn(false);

        this.mockMvc.perform(delete("/history/1"))
                .andExpect(status().isNotFound());
    }
}
