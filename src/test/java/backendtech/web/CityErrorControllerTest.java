package backendtech.web;

import jakarta.servlet.RequestDispatcher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CityErrorController.class)
public class CityErrorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MockHttpServletRequest request;

    @Test
    void testHandleErrorNotFound() throws Exception {
        when(request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE)).thenReturn(404);

        this.mockMvc.perform(get("/error").requestAttr(RequestDispatcher.ERROR_STATUS_CODE, 404))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Resource not found, please try another Request\nWeatherWorks - your companion even on rainy days"));
    }

    @Test
    void testHandleErrorInternalServerError() throws Exception {
        when(request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE)).thenReturn(500);

        this.mockMvc.perform(get("/error").requestAttr(RequestDispatcher.ERROR_STATUS_CODE, 500))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Server isn't activated yet, use the available Rest Mappings and routes\nWeatherWorks - your companion even on rainy days "));
    }

    @Test
    void testHandleErrorBadRequest() throws Exception {
        when(request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE)).thenReturn(400);

        this.mockMvc.perform(get("/error").requestAttr(RequestDispatcher.ERROR_STATUS_CODE, 400))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Error occurred, please try another Request\nWeatherWorks - your companion even on rainy days"));
    }

    @Test
    void testHandleUnknownError() throws Exception {
        when(request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE)).thenReturn(null);

        this.mockMvc.perform(get("/error"))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Unknown error, please restart the Application"));
    }
}
