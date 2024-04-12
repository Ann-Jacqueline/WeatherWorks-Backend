package backendtech;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController{
        @GetMapping("/greeting")
        public String greeting() {
                return "Application works!";
        }
}
