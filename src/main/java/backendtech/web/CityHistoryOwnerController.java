package backendtech.web;

import backendtech.model.CityHistoryOwner;
import backendtech.service.CityHistoryOwnerService;
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

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<CityHistoryOwner>> getCityHistoryOwners(HttpServletRequest request) {
        if (request.getSession(false) == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(cityHistoryOwnerService.getCityHistoryOwners());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CityHistoryOwner> getCityHistoryOwner(@PathVariable("id") final Long id, HttpServletRequest request) {
        if (request.getSession(false) == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        final Optional<CityHistoryOwner> found = cityHistoryOwnerService.getCityHistoryOwner(id);
        return found.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/current")
    public ResponseEntity<?> getCurrentUser(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            String currentUserName = (String) session.getAttribute("userName");
            if (currentUserName != null) {
                logger.info("Aktuelle Session-ID: {}, Benutzername: {}", session.getId(), currentUserName);
                return ResponseEntity.ok(Map.of("userName", currentUserName));
            } else {
                logger.warn("Kein Benutzername in der aktuellen Session (ID: {}) gefunden.", session.getId());
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Kein Benutzername in der Session gefunden.");
            }
        } else {
            logger.warn("Keine aktive Session vorhanden für /current Anfrage.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Keine aktive Session vorhanden.");
        }
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

    @PostMapping("/login")
    public ResponseEntity<Void> loginUser(@RequestBody Map<String, String> userData, HttpServletRequest request) {
        String userName = userData.get("userName");
        if (userName != null && !userName.isEmpty()) {
            // Erstellen einer neuen Session und Setzen des Benutzernamens
            HttpSession session = request.getSession(true);
            session.setAttribute("userName", userName);

            // Logging der Erstellung der neuen Session und des gesetzten Benutzernamens
            logger.info("Neue Session gestartet, Session ID: {}", session.getId());
            logger.info("Benutzername '{}' zur Session hinzugefügt.", userName);

            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            logger.warn("Login-Versuch mit ungültigen oder leeren Benutzerdaten.");
            return ResponseEntity.badRequest().build();
        }
    }

    //last current username schreiben
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request) {
        if (request.getSession(false) != null) {
            request.getSession().invalidate();
            return ResponseEntity.ok().build();
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
