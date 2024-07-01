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

@Controller
@AllArgsConstructor
@RequestMapping("/history")
public class CityHistoryController {
    private final CityHistoryService cityHistoryService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<CityHistory>> getCityHistories() {
        return ResponseEntity.ok(cityHistoryService.getCityHistories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CityHistory> getCityHistory(@PathVariable("id") final Long id) {
        final Optional<CityHistory> found = cityHistoryService.getCityHistory(id);
        return found.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CityHistory> addCityHistory(@Valid @RequestBody CityHistory body, HttpServletRequest request) {
        final CityHistory newCityHistory = new CityHistory(
                body.getCityName(),
                body.getCountry(),
                body.getTemperature(),
                body.getLocalTime(),
                body.isDeleted(),
                body.getOwner(),  // Setze den Owner
                body.isSetAsDefault()
        );
        final CityHistory addedCityHistory = cityHistoryService.addCityHistory(newCityHistory);
        return new ResponseEntity<>(addedCityHistory, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCityHistory(@PathVariable("id") Long id) {
        boolean isRemoved = cityHistoryService.removeCityHistory(id);
        return isRemoved ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
