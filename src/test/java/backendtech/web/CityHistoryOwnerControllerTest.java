package backendtech.web;

import backendtech.model.CityHistoryOwner;
import backendtech.service.CityHistoryOwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;


import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CityHistoryOwnerController.class)
public class CityHistoryOwnerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CityHistoryOwnerService cityHistoryOwnerService;

    private MockHttpSession session;

    @BeforeEach
    void setUp() {
        session = new MockHttpSession();
        session.setAttribute("userName", "user");

        CityHistoryOwner owner = new CityHistoryOwner(1L, "user");
        when(cityHistoryOwnerService.getCityHistoryOwners()).thenReturn(List.of(owner));
        when(cityHistoryOwnerService.getCityHistoryOwner(1L)).thenReturn(Optional.of(owner));
        when(cityHistoryOwnerService.addCityHistoryOwner(any(CityHistoryOwner.class))).thenReturn(owner);
    }

    @Test
    void testGetCityHistoryOwners() throws Exception {
        mockMvc.perform(get("/users").session(session))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testGetCurrentUser() throws Exception {
        mockMvc.perform(get("/users/current").session(session))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"userName\":\"user\"}"));
    }

    @Test
    void testLoginUser() throws Exception {
        mockMvc.perform(post("/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userName\":\"user\"}"))
                .andExpect(status().isCreated());
    }

    @Test
    void testLogout() throws Exception {
        mockMvc.perform(post("/users/logout").session(session))
                .andExpect(status().isOk());
    }

    @Test
    void testAddCityHistoryOwnerUnauthorizedWithoutSession() throws Exception {
        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userName\":\"newUser\"}"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void testAddCityHistoryOwnerWithSession() throws Exception {
        mockMvc.perform(post("/users")
                        .session(session)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userName\":\"newUser\"}"))
                .andExpect(status().isCreated());
    }

    @Test
    void testGetAllUsers() throws Exception {
        mockMvc.perform(get("/users/").session(session))
                .andExpect(status().isOk());
    }
}

