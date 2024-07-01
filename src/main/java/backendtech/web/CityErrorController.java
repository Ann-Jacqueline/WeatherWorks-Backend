package backendtech.web;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CityErrorController implements ErrorController, CityErrorInterface {
    @RequestMapping("/error")
    public ResponseEntity<?> handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());

            // Handle specific status codes
            return switch (statusCode) {
                case 404 -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Resource not found, please try another Request\nWeatherWorks - your companion even on rainy days");
                case 500 -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Server isn't activated yet, use the available Rest Mappings and routes\nWeatherWorks - your companion even on rainy days ");
                default -> ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Error occurred, please try another Request\nWeatherWorks - your companion even on rainy days");
            };
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unknown error, please restart the Application");
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
