package backendtech.web;
import backendtech.model.CityEntry;
import backendtech.model.CityEntryWithId;
import backendtech.service.CityEntryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/greeting")
public class WeatherController {

        private final CityEntryService cityEntryService;

        @Autowired
        public WeatherController(CityEntryService cityEntryService) {
                this.cityEntryService = cityEntryService;
        }

        @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<List<CityEntryWithId>> getCityEntry() {
                return ResponseEntity.ok(cityEntryService.getCityEntrys());
        }

        @GetMapping("/{id}")
        public ResponseEntity<CityEntryWithId> getCityEntry(@PathVariable("id") Long id) {
                CityEntryWithId found = cityEntryService.getCityEntry(id);
                return found != null ? ResponseEntity.ok(found) : ResponseEntity.notFound().build();
        }

        @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<CityEntryWithId> addCityEntry(@Valid @RequestBody CityEntry body) {
                CityEntryWithId addedCityEntry = cityEntryService.addCityEntry(body);
                return new ResponseEntity<>(addedCityEntry, HttpStatus.CREATED);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteCityEntry(@PathVariable("id") Long id) {
                boolean isRemoved = cityEntryService.removeCityEntry(id);
                return isRemoved ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
        }
}





