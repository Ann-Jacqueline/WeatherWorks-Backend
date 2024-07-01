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
            int statusCode = Integer.parseInt(status.toString());  // Effizienter mit parseInt

            // Handle specific status codes
            return switch (statusCode) {
                case 404 -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resource not found");
                case 500 -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
                default -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error occurred");
            };
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error occurred");
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
