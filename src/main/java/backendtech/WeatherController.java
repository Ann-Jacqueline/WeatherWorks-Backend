package backendtech;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {


        @GetMapping("/greeting")
        public CityEntry greeting () {
            return new CityEntry();
        }

}
