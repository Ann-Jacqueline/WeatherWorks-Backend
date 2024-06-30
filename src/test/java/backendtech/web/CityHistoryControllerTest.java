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

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CityHistoryController.class)
public class CityHistoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CityHistoryService cityHistoryService;

    @BeforeEach
    void setUp() {
        CityHistory cityHistory = new CityHistory(true, "Berlin", 22, "12:00 PM", false, null);
        cityHistory.setId(1L);
        when(cityHistoryService.getCityHistories()).thenReturn(List.of(cityHistory));
        when(cityHistoryService.getCityHistory(1L)).thenReturn(Optional.of(cityHistory));
        when(cityHistoryService.addCityHistory(any(CityHistory.class))).thenReturn(cityHistory);
    }

    @Test
    void testGetCityHistories() throws Exception {
        this.mockMvc.perform(get("/history"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].cityName").value("Berlin"));
    }

    @Test
    void testGetCityHistoryById() throws Exception {
        this.mockMvc.perform(get("/history/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.cityName").value("Berlin"));
    }

    @Test
    void testAddCityHistory() throws Exception {
        String jsonBody = "{\"setAsDefault\":true,\"cityName\":\"Berlin\",\"temperature\":22,\"localTime\":\"12:00 PM\",\"deleted\":false}";
        this.mockMvc.perform(post("/history")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.cityName").value("Berlin"));
    }

    @Test
    void testDeleteCityHistory() throws Exception {
        when(cityHistoryService.removeCityHistory(1L)).thenReturn(true);
        this.mockMvc.perform(delete("/history/1"))
                .andExpect(status().isNoContent());
        verify(cityHistoryService).removeCityHistory(1L);
    }
}

