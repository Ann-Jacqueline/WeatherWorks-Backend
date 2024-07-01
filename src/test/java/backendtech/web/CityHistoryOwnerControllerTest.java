package backendtech.web;

import backendtech.model.CityHistoryOwner;
import backendtech.service.CityHistoryOwnerService;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Testklasse für den CityHistoryOwnerController.
 * Diese Klasse testet die verschiedenen Endpunkte des CityHistoryOwnerController.
 */
@WebMvcTest(CityHistoryOwnerController.class)
public class CityHistoryOwnerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CityHistoryOwnerService cityHistoryOwnerService;

    @MockBean
    private HttpSession session;

    @BeforeEach
    void setUp() {
        CityHistoryOwner cityHistoryOwner = new CityHistoryOwner("Ann-Jacqueline");
        cityHistoryOwner.setId(1L);

        when(cityHistoryOwnerService.getCityHistoryOwners()).thenReturn(List.of(cityHistoryOwner));
        when(cityHistoryOwnerService.getCityHistoryOwner(1L)).thenReturn(Optional.of(cityHistoryOwner));
        when(cityHistoryOwnerService.addCityHistoryOwner(any(CityHistoryOwner.class))).thenReturn(cityHistoryOwner);
        when(cityHistoryOwnerService.getCurrentUser("Ann-Jacqueline")).thenReturn(Optional.of(cityHistoryOwner));
    }

    /**
     * Testet den Endpunkt für das Abrufen aller CityHistoryOwner.
     */
    @Test
    void testGetCityHistoryOwners() throws Exception {
        when(session.getAttribute("userName")).thenReturn("Ann-Jacqueline");
        when(session.getId()).thenReturn("session123");

        this.mockMvc.perform(get("/users").sessionAttr("userName", "Ann-Jacqueline"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].userName").value("Ann-Jacqueline"));
    }

    /**
     * Testet den Endpunkt für das Hinzufügen eines neuen CityHistoryOwner.
     */
    @Test
    void testAddCityHistoryOwner() throws Exception {
        String cityHistoryOwnerJson = "{\"userName\":\"Ann-Jacqueline\"}";

        this.mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(cityHistoryOwnerJson)
                        .sessionAttr("userName", "Ann-Jacqueline"))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.userName").value("Ann-Jacqueline"));
    }

    /**
     * Testet den Endpunkt für das Anmelden eines Benutzers.
     */
    @Test
    void testLoginUser() throws Exception {
        String loginJson = "{\"userName\":\"Ann-Jacqueline\"}";

        this.mockMvc.perform(post("/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(loginJson))
                .andExpect(status().isCreated());
    }

    /**
     * Testet den Endpunkt für das Abrufen aller CityHistoryOwner ohne Authentifizierung.
     */
    @Test
    void testUnauthorizedGetCityHistoryOwners() throws Exception {
        this.mockMvc.perform(get("/users"))
                .andExpect(status().isUnauthorized());
    }

    /**
     * Testet den Endpunkt für das Hinzufügen eines neuen CityHistoryOwner ohne Authentifizierung.
     */
    @Test
    void testUnauthorizedAddCityHistoryOwner() throws Exception {
        String cityHistoryOwnerJson = "{\"userName\":\"Ann-Jacqueline\"}";

        this.mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(cityHistoryOwnerJson))
                .andExpect(status().isUnauthorized());
    }

    /**
     * Testet den Endpunkt für das Abrufen aller Benutzer.
     */
    @Test
    void testGetAllUsers() throws Exception {
        when(cityHistoryOwnerService.getAllUsers()).thenReturn(List.of(new CityHistoryOwner("Ann-Jacqueline")));

        this.mockMvc.perform(get("/users/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].userName").value("Ann-Jacqueline"));
    }
}
