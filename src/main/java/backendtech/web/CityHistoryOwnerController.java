package backendtech.web;

import backendtech.model.CityHistoryOwner;
import backendtech.service.CityHistoryOwnerService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping("/users")
public class CityHistoryOwnerController {
    private final CityHistoryOwnerService cityHistoryOwnerService;
    private static final Logger logger = LoggerFactory.getLogger(CityHistoryOwnerController.class);

    @PostMapping("/login")
    public ResponseEntity<Void> loginUser(@RequestBody Map<String, String> userData, HttpServletRequest request) {
        String userName = userData.get("userName");
        if (userName != null && !userName.isEmpty()) {
            HttpSession session = request.getSession(true);
            session.setAttribute("userName", userName);
            logger.info("New session started, Session ID: {}", session.getId());
            logger.info("Username '{}' added to session.", userName);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            logger.warn("Login attempt with invalid or empty user data.");
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<CityHistoryOwner>> getCityHistoryOwners(HttpServletRequest request) {
        if (request.getSession(false) == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(cityHistoryOwnerService.getCityHistoryOwners());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CityHistoryOwner> addCityHistoryOwner(@Valid @RequestBody CityHistoryOwner body, HttpServletRequest request) {
        if (request.getSession(false) != null) {
            final CityHistoryOwner newCityHistoryOwner = new CityHistoryOwner(body.getUserName());
            final CityHistoryOwner addedCityHistoryOwner = cityHistoryOwnerService.addCityHistoryOwner(newCityHistoryOwner);
            return new ResponseEntity<>(addedCityHistoryOwner, HttpStatus.CREATED);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @GetMapping("/")
    public ResponseEntity<List<CityHistoryOwner>> getAllUsers() {
        List<CityHistoryOwner> users = (List<CityHistoryOwner>) cityHistoryOwnerService.getCityHistoryOwners();
        if (users.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users);
    }
}
