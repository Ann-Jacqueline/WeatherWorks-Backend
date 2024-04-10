package backendtech;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {

        @GetMapping("/greeting")
        public CityEntry greeting (@PathVariable String city, @PathVariable String country, @PathVariable String description, @PathVariable String temperature) {
                CityEntry weather = new CityEntry ("Berlin", "Germany " , "Sunny", 25);
                return greeting(city,country,description,temperature);
        }

}
