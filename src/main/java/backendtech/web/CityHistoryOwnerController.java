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

/**
 * Der Controller für die Verwaltung der CityHistoryOwner.
 * Dieser Controller bietet Endpunkte für das Anmelden von Benutzern,
 * das Abrufen von CityHistoryOwner-Informationen und das Hinzufügen neuer CityHistoryOwner.
 */
@Controller
@AllArgsConstructor
@RequestMapping("/users")
public class CityHistoryOwnerController {
    private final CityHistoryOwnerService cityHistoryOwnerService;
    private static final Logger logger = LoggerFactory.getLogger(CityHistoryOwnerController.class);

    /**
     * Meldet einen Benutzer an und startet eine neue Sitzung.
     * @param userData Die Daten des Benutzers, enthalten den Benutzernamen.
     * @param request Das HttpServletRequest-Objekt.
     * @return Eine ResponseEntity, die den Status der Anfrage zurückgibt.
     */
    @PostMapping("/login")
    public ResponseEntity<Void> loginUser(@RequestBody Map<String, String> userData, HttpServletRequest request) {
        String userName = userData.get("userName");
        if (userName != null && !userName.isEmpty()) {
            HttpSession session = request.getSession(true);
            session.setAttribute("userName", userName);
            logger.info("Neue Sitzung gestartet, Sitzungs-ID: {}", session.getId());
            logger.info("Benutzername '{}' zur Sitzung hinzugefügt.", userName);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            logger.warn("Anmeldeversuch mit ungültigen oder leeren Benutzerdaten.");
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Gibt eine Liste aller CityHistoryOwner zurück.
     * @param request Das HttpServletRequest-Objekt.
     * @return Eine ResponseEntity mit der Liste der CityHistoryOwner.
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<CityHistoryOwner>> getCityHistoryOwners(HttpServletRequest request) {
        if (request.getSession(false) == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(cityHistoryOwnerService.getCityHistoryOwners());
    }

    /**
     * Fügt einen neuen CityHistoryOwner hinzu.
     * @param body Die Daten des neuen CityHistoryOwner.
     * @param request Das HttpServletRequest-Objekt.
     * @return Eine ResponseEntity mit dem hinzugefügten CityHistoryOwner.
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CityHistoryOwner> addCityHistoryOwner(@Valid @RequestBody CityHistoryOwner body, HttpServletRequest request) {
        if (request.getSession(false) != null) {
            final CityHistoryOwner newCityHistoryOwner = new CityHistoryOwner(body.getUserName());
            final CityHistoryOwner addedCityHistoryOwner = cityHistoryOwnerService.addCityHistoryOwner(newCityHistoryOwner);
            return new ResponseEntity<>(addedCityHistoryOwner, HttpStatus.CREATED);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    /**
     * Gibt eine Liste aller Benutzer zurück.
     * @return Eine ResponseEntity mit der Liste aller Benutzer.
     */
    @GetMapping("/")
    public ResponseEntity<List<CityHistoryOwner>> getAllUsers() {
        List<CityHistoryOwner> users = (List<CityHistoryOwner>) cityHistoryOwnerService.getCityHistoryOwners();
        if (users.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users);
    }
}
