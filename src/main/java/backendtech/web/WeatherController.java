package backendtech.web;
import backendtech.model.CityEntry;
import backendtech.model.CityEntryWithId;
import backendtech.service.CityEntryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@AllArgsConstructor
@RequestMapping("/greeting")

public class WeatherController{
        private final CityEntryService cityEntryService;
        @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<List<CityEntryWithId>> getCityEntry() {
                return ResponseEntity.ok(cityEntryService.getCityEntrys());
        }
        @GetMapping("/{id}")
        public ResponseEntity<CityEntryWithId> getCityEntry(@PathVariable("id") final Long id) {
                final CityEntryWithId found = cityEntryService.getCityEntry(id);
                return found != null ? ResponseEntity.ok(found) : ResponseEntity.notFound().build();
        }
        @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<CityEntry> addCityEntry(@Valid @RequestBody CityEntry body) {
                final CityEntryWithId addedCityEntry = cityEntryService.addCityEntry(body);
                return new ResponseEntity<>(addedCityEntry, HttpStatus.CREATED);
        }

        @DeleteMapping(path = "/{id}")
        public ResponseEntity<Void> deleteCityEntry(@PathVariable("id") final Long id) {
                return cityEntryService.removeCityEntry(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
        }
        }





