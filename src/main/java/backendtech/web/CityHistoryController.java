package backendtech.web;

import backendtech.model.CityHistory;
import backendtech.service.CityHistoryService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Controller für die Verwaltung von CityHistory-Entitäten.
 * Diese Klasse bietet Endpunkte zum Abrufen, Hinzufügen und Löschen von CityHistory-Objekten.
 */
@Controller
@AllArgsConstructor
@RequestMapping("/history")
public class CityHistoryController {
    private final CityHistoryService cityHistoryService;

    /**
     * Ruft alle CityHistory-Einträge ab.
     *
     * @return Eine ResponseEntity mit einem Iterable aller CityHistory-Objekte.
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<CityHistory>> getCityHistories() {
        return ResponseEntity.ok(cityHistoryService.getCityHistories());
    }

    /**
     * Ruft eine spezifische CityHistory anhand der ID ab.
     *
     * @param id Die ID der CityHistory.
     * @return Eine ResponseEntity mit der gefundenen CityHistory oder ein NotFound-Status.
     */
    @GetMapping("/{id}")
    public ResponseEntity<CityHistory> getCityHistory(@PathVariable("id") final Long id) {
        final Optional<CityHistory> found = cityHistoryService.getCityHistory(id);
        return found.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Fügt eine neue CityHistory hinzu.
     *
     * @param body    Die CityHistory-Daten im Request-Body.
     * @param request Das HttpServletRequest-Objekt.
     * @return Eine ResponseEntity mit der hinzugefügten CityHistory und dem Status Created.
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CityHistory> addCityHistory(@Valid @RequestBody CityHistory body, HttpServletRequest request) {
        final CityHistory newCityHistory = new CityHistory(
                body.getCityName(),
                body.getCountry(),
                body.getTemperature(),
                body.getLocalTime(),
                body.isDeleted(),
                body.getOwner(),
                body.isSetAsDefault()
        );
        final CityHistory addedCityHistory = cityHistoryService.addCityHistory(newCityHistory);
        return new ResponseEntity<>(addedCityHistory, HttpStatus.CREATED);
    }

    /**
     * Löscht eine CityHistory anhand der ID.
     *
     * @param id Die ID der zu löschenden CityHistory.
     * @return Eine ResponseEntity mit dem Status NoContent, wenn erfolgreich, oder NotFound, wenn die CityHistory nicht gefunden wurde.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCityHistory(@PathVariable("id") Long id) {
        boolean isRemoved = cityHistoryService.removeCityHistory(id);
        return isRemoved ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
