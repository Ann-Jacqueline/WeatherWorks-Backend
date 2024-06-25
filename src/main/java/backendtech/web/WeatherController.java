package backendtech.web;

import backendtech.model.CityEntry;
import backendtech.service.CityEntryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping("/city")
public class WeatherController {

        private final CityEntryService cityEntryService;

        @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<Iterable<CityEntry>> getCityEntry() {
                return ResponseEntity.ok(cityEntryService.getCityEntries());
        }

        @GetMapping("/{id}")
        public ResponseEntity<CityEntry> getCityEntry(@PathVariable("id") final Long id) {
                final Optional<CityEntry> found = cityEntryService.getCityEntry(id);
                return found.isPresent() ? ResponseEntity.ok(found.get()) : ResponseEntity.notFound().build();
        }

        @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<CityEntry> addCityEntry(@Valid @RequestBody CityEntry body) {
                final CityEntry newCityEntry = new CityEntry(
                        body.getName(),
                        body.getTemperature(),
                        body.getMinTemperature(),
                        body.getMaxTemperature(),
                        body.getFeelsLike(),
                        body.getDescription(),
                        body.getIcon(),
                        body.getWindSpeed(),
                        body.getHumidity(),
                        body.getCloudiness(),
                        body.getCountry(),
                        body.getPressure(),
                        body.getVisibility(),
                        body.getTimezone(),
                        body.getSunrise(),
                        body.getSunset(),
                        body.getLocalDate(),
                        body.getLocalTime()
                );
                final CityEntry addedCityEntry = cityEntryService.addCityEntry(newCityEntry);
                return new ResponseEntity<>(addedCityEntry, HttpStatus.CREATED);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteCityEntry(@PathVariable("id") Long id) {
                boolean isRemoved = cityEntryService.removeCityEntry(id);
                return isRemoved ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
        }
}





