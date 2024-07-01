package backendtech.web;

import backendtech.model.CitySearch;
import backendtech.service.CitySearchService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.Optional;

/**
 * Der Controller für die Verwaltung der CitySearch-Einträge.
 * Dieser Controller bietet Endpunkte für das Abrufen, Hinzufügen und Löschen von CitySearch-Einträgen.
 */
@Controller
@AllArgsConstructor
@RequestMapping("/city")
public class CitySearchController {

        private final CitySearchService citySearchService;

        /**
         * Gibt eine Liste aller CitySearch-Einträge zurück.
         * @return Eine ResponseEntity mit der Liste der CitySearch-Einträge.
         */
        @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<Iterable<CitySearch>> getCityEntry() {
                return ResponseEntity.ok(citySearchService.getCityEntries());
        }

        /**
         * Gibt einen CitySearch-Eintrag anhand der angegebenen ID zurück.
         * @param id Die ID des CitySearch-Eintrags.
         * @return Eine ResponseEntity mit dem CitySearch-Eintrag oder ein 404-Status, wenn nicht gefunden.
         */
        @GetMapping("/{id}")
        public ResponseEntity<CitySearch> getCityEntry(@PathVariable("id") final Long id) {
                final Optional<CitySearch> found = citySearchService.getCityEntry(id);
                return found.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        }

        /**
         * Fügt einen neuen CitySearch-Eintrag hinzu.
         * @param body Die Daten des neuen CitySearch-Eintrags.
         * @return Eine ResponseEntity mit dem hinzugefügten CitySearch-Eintrag.
         */
        @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<CitySearch> addCityEntry(@Valid @RequestBody CitySearch body) {
                final CitySearch newCitySearch = new CitySearch(
                        body.getName(),
                        body.getTemperature(),
                        body.getFeelsLike(),
                        body.getDescription(),
                        body.getWindSpeed(),
                        body.getHumidity(),
                        body.getCloudiness(),
                        body.getCountry(),
                        body.getLocalTime()
                );
                final CitySearch addedCitySearch = citySearchService.addCityEntry(newCitySearch);
                return new ResponseEntity<>(addedCitySearch, HttpStatus.CREATED);
        }

        /**
         * Löscht einen CitySearch-Eintrag anhand der angegebenen ID.
         * @param id Die ID des zu löschenden CitySearch-Eintrags.
         * @return Eine ResponseEntity mit dem Status der Löschanfrage.
         */
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteCityEntry(@PathVariable("id") Long id) {
                boolean isRemoved = citySearchService.removeCityEntry(id);
                return isRemoved ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
        }
}
